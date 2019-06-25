package it.wcc.postcode.service.distance;

import java.io.Serializable;

import it.wcc.postcode.dto.PostCodesDistance;

public interface DistanceService extends Serializable  {
	
	public PostCodesDistance calculateDistance(String postCode, String postCode2);

}
