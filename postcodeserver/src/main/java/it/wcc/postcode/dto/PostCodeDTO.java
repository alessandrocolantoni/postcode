package it.wcc.postcode.dto;

import java.io.Serializable;

public class PostCodeDTO implements Serializable{
	
	
	private static final long serialVersionUID = -2266617779185342912L;

	
	private String postCode;
	
	private Coordinates coordinates;

	public PostCodeDTO() {
		super();
	}

	public PostCodeDTO(String postCode, Coordinates coordinates) {
		super();
		this.postCode = postCode;
		this.coordinates = coordinates;
	}




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
