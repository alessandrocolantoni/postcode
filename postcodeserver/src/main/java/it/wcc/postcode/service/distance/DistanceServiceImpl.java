package it.wcc.postcode.service.distance;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.wcc.postcode.dao.postcode.PostCodeDao;
import it.wcc.postcode.dto.PostCodeDTO;
import it.wcc.postcode.dto.PostCodesDistance;

@Service
public class DistanceServiceImpl implements DistanceService {
 
	private static final long serialVersionUID = 1L;
	
	private final static double EARTH_RADIUS = 6371; // radius in kilometers
	
	private static final Logger log = LoggerFactory.getLogger(DistanceServiceImpl.class);
	
	
	@Autowired
	private PostCodeDao postCodeDao;
	
	public PostCodesDistance calculateDistance(String postCode, String postCode2) {
		try {
		
			PostCodeDTO postCodeDTO = postCodeDao.findPostCode(postCode);
			PostCodeDTO postCodeDTO2 = postCodeDao.findPostCode(postCode2);
			
			if(postCodeDTO == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "postCode " +postCode+" Not Found" );
			}
			
			if(postCodeDTO2 == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "postCode " +postCode2+" Not Found" );
			}
		
		
		
			PostCodesDistance postCodesDistance = calculateDistance(postCodeDTO, postCodeDTO2 );
		 
			
			return postCodesDistance;
		} catch (Throwable e) {
			log.error("Error calculating distance ",e);
			
			if(e instanceof ResponseStatusException  ) {
				throw (ResponseStatusException) e;
			}
			
			throw new ServiceException("Error calculating distance " , e);
		}
		
		
	}
	
	
	private PostCodesDistance calculateDistance(PostCodeDTO postCodeDTO, PostCodeDTO postCodeDTO2 ) throws Throwable{
		PostCodesDistance postCodesDistance = new PostCodesDistance();
		postCodesDistance.setUom("km");
		postCodesDistance.setPostCodeA(postCodeDTO);
		postCodesDistance.setPostCodeB(postCodeDTO2);
		
		double latitude = postCodeDTO.getCoordinates().getLatitude();
		double longitude= postCodeDTO.getCoordinates().getLongitude();
		double latitude2 = postCodeDTO2.getCoordinates().getLatitude();
		double longitude2 = postCodeDTO2.getCoordinates().getLongitude();
		
		double distance = calculateDistance(latitude, longitude, latitude2, longitude2 );
		postCodesDistance.setDistance(distance);
		
		return postCodesDistance;
	}
	
	private double calculateDistance(double latitude, double longitude, double latitude2, double longitude2)  throws Throwable{
	        // Using Haversine formula! See Wikipedia;
	        double lon1Radians = Math.toRadians(longitude);
	        double lon2Radians = Math.toRadians(longitude2);
	        double lat1Radians = Math.toRadians(latitude);
	        double lat2Radians = Math.toRadians(latitude2);
	        double a = haversine(lat1Radians, lat2Radians)
	          + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        return (EARTH_RADIUS * c);
	    }
	
	
	    private double haversine(double deg1, double deg2) throws Throwable  {
	        return square(Math.sin((deg1 - deg2) / 2.0));
	    }
	    private double square(double x) throws Throwable {
	        return x * x;
	    }


}
