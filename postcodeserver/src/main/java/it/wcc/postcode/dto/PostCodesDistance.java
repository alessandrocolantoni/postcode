package it.wcc.postcode.dto;

import java.io.Serializable;

public class PostCodesDistance implements Serializable{
	
	 
	private static final long serialVersionUID = 7117482591088276769L;

	private PostCodeDTO postCodeA;
	
	private PostCodeDTO postCodeB;
	
	/**
	 * distance between p postCodeA and postCodeB
	 */
	private Double distance;
	
	/**
	 * unit of measurement
	 */
	private String uom;

	public PostCodeDTO getPostCodeA() {
		return postCodeA;
	}

	public void setPostCodeA(PostCodeDTO postCodeA) {
		this.postCodeA = postCodeA;
	}

	public PostCodeDTO getPostCodeB() {
		return postCodeB;
	}

	public void setPostCodeB(PostCodeDTO postCodeB) {
		this.postCodeB = postCodeB;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	
	
	
	
	
	
	

}
