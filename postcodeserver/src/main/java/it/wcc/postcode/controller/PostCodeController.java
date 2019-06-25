package it.wcc.postcode.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;
import it.wcc.postcode.dto.PostCodesDistance;
import it.wcc.postcode.service.distance.DistanceService;
import it.wcc.postcode.service.postcode.PostCodeService;


@RestController
@Validated
//@RequestMapping("/distance")
public class PostCodeController {


	
	
	private static final Logger log = LoggerFactory.getLogger(PostCodeController.class);
	
	
	
    @Autowired
    private PostCodeService postCodeService;
    
    @Autowired
    private DistanceService distanceService;
	
	@GetMapping(value = "/distance/postcodeA/{postcodeA}/postcodeB/{postcodeB}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCodesDistance distance(@PathVariable ("postcodeA") @Size(max = 8) @NotEmpty String postcodeA, @PathVariable ("postcodeB") @Size(max = 8) @NotEmpty String postcodeB) {
		
		
		log.info("Request distance between post code {} and post code {}",postcodeA,postcodeB);
		
		PostCodesDistance postCodesDistance = distanceService.calculateDistance(postcodeA, postcodeB);
		return postCodesDistance;
		
	}
	
	//@PreAuthorize("#oauth2.hasScope('resource-server-read')")
	@PreAuthorize("hasRole('ROLE_READ') or hasRole('ROLE_WRITE')")
	@GetMapping(value = "/{postcode}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCodeDTO postCode(@PathVariable ("postcode") @Size(max = 8) @NotEmpty String postCode) {
		PostCodeDTO postCodeDTO = postCodeService.findPostCode(postCode);
		if(postCodeDTO==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "postcode "+postCode+" Not Found" );
		}
		return postCodeDTO;
		
	}
	
	@PreAuthorize("hasRole('ROLE_WRITE')")
	@PutMapping(value = "/modify/{postcode}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCodeDTO postCode(@PathVariable ("postcode") @Size(max = 8) @NotEmpty String postCode, @RequestBody  Coordinates coordinates) {
		PostCodeDTO postCodeDTO = postCodeService.updateCoordinates(postCode, coordinates);
		if(postCodeDTO==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "postcode "+postCode+" Not Found" );
		} 
		return postCodeDTO;
		
	}
	
	@PreAuthorize("hasRole('ROLE_WRITE')")
	@PostMapping(value = "/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public void postCode(@RequestBody  PostCodeDTO postCodeDTO) {
		boolean podCodeExists = postCodeService.verifyExistence(postCodeDTO.getPostCode());
		if(podCodeExists) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "postcode "+postCodeDTO.getPostCode()+" already exists" );
		} 
		postCodeService.addPostCode(postCodeDTO);
		
	}
	
	@GetMapping(value = "/hola",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String hola() {
		return "hola";
		
	}
	
}
