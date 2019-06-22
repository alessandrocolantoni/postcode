package it.wcc.postcode.dto;

import java.io.Serializable;

public class Coordinates implements Serializable{
	
 
	private static final long serialVersionUID = 5741325682172988941L;
	
	private double latitude;
	private double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
