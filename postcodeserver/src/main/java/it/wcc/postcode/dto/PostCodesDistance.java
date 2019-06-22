package it.wcc.postcode.dto;

import java.io.Serializable;

public class PostCodesDistance implements Serializable{
	
	 
	private static final long serialVersionUID = 7117482591088276769L;

	private PostCode postCodeA;
	
	private PostCode postCodeB;
	
	/**
	 * distance between p postCodeA and postCodeB
	 */
	private Double distance;
	
	/**
	 * unit of measurement
	 */
	private String uom;

	public PostCode getPostCodeA() {
		return postCodeA;
	}

	public void setPostCodeA(PostCode postCodeA) {
		this.postCodeA = postCodeA;
	}

	public PostCode getPostCodeB() {
		return postCodeB;
	}

	public void setPostCodeB(PostCode postCodeB) {
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
