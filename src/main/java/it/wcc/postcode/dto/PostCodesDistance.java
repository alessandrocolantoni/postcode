package it.wcc.postcode.dto;

public class PostCodesDistance {
	
	private String postCodeA;
	private String postCodeB;
	
	private double latitudeA;
	private double longitudeA;
	
	private double latitudeB;
	private double longitudeB;
	
	/**
	 * distance between p postCodeA and postCodeB
	 */
	private Double distance;
	
	/**
	 * unit of measurement
	 */
	private String uom;

	public String getPostCodeA() {
		return postCodeA;
	}

	public void setPostCodeA(String postCodeA) {
		this.postCodeA = postCodeA;
	}

	public String getPostCodeB() {
		return postCodeB;
	}

	public void setPostCodeB(String postCodeB) {
		this.postCodeB = postCodeB;
	}

	public double getLatitudeA() {
		return latitudeA;
	}

	public void setLatitudeA(double latitudeA) {
		this.latitudeA = latitudeA;
	}

	public double getLongitudeA() {
		return longitudeA;
	}

	public void setLongitudeA(double longitudeA) {
		this.longitudeA = longitudeA;
	}

	public double getLatitudeB() {
		return latitudeB;
	}

	public void setLatitudeB(double latitudeB) {
		this.latitudeB = latitudeB;
	}

	public double getLongitudeB() {
		return longitudeB;
	}

	public void setLongitudeB(double longitudeB) {
		this.longitudeB = longitudeB;
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
