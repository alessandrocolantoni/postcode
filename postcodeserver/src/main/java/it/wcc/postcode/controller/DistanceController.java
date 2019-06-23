package it.wcc.postcode.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCode;
import it.wcc.postcode.dto.PostCodesDistance;

@RestController
//@RequestMapping("/distance")
public class DistanceController {

//	@GetMapping(value = "{id}/list",  produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PostCodesDistance> distance() {
//		
//		
//	}
	
	@GetMapping(value = "/distance/postcodeA/{postcodeA}/postcodeB/{postcodeB}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCodesDistance distance(@PathVariable ("postcodeA") String postcodeA, @PathVariable ("postcodeB") String postcodeB) {
		try {
			PostCodesDistance p = new PostCodesDistance();
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
	//@PreAuthorize("#oauth2.hasScope('resource-server-read')")
	@PreAuthorize("hasRole('ROLE_READ') or hasRole('ROLE_WRITE')")
	@GetMapping(value = "/{postcode}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCode postCode(@PathVariable ("postcode") String postcode) {
		try {
			PostCode p = new PostCode();
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_WRITE')")
	@PutMapping(value = "/modify/{postcode}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCode postCode(@RequestBody  Coordinates coordinates) {
		try {
			PostCode p = new PostCode();
			 
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_WRITE')")
	@PostMapping(value = "/add",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCode postCode(@RequestBody  PostCode postCode) {
		try {
			PostCode p = new PostCode();
			 
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
	@GetMapping(value = "/hola",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String hola() {
		try {
			 
			return "hola";
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
}
