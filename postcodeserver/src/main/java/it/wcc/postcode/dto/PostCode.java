package it.wcc.postcode.dto;

import java.io.Serializable;

public class PostCode implements Serializable{
	
	
	private static final long serialVersionUID = -2266617779185342912L;

	private String postCode;
	
	private Coordinates coordinates;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	 
	
	
	
	
	
	
	
	
	
	
	

}
