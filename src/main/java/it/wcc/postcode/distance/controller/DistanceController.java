package it.wcc.postcode.distance.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.wcc.postcode.dto.PostCodesDistance;

@RestController
@RequestMapping("distance/")
public class DistanceController {

//	@GetMapping(value = "{id}/list",  produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<PostCodesDistance> distance() {
//		
//		
//	}
	
	@GetMapping(value = "/postcodeA/{postcodeA}/postcodeB/{postcodeB}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public PostCodesDistance distance(@PathVariable ("postcodeA") String postcodeA, @PathVariable ("postcodeB") String postcodeB) {
		try {
			PostCodesDistance p = new PostCodesDistance();
			return p;
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
	@GetMapping(value = "hola",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String hola() {
		try {
			 
			return "hola";
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Foo Not Found", e);
		}
		
	}
	
}
