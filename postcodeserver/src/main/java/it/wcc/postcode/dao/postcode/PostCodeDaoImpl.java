package it.wcc.postcode.dao.postcode;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.wcc.postcode.dao.AbstractJpaDAO;
import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;
import it.wcc.postcode.entity.PostCode;

@Repository
public class PostCodeDaoImpl extends AbstractJpaDAO<PostCode> implements PostCodeDao {

    @PersistenceContext
    protected EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Autowired
    private Mapper simpleMapper;

    

    @Override
    public boolean verifyExistence (String postCode) {
    	String queryString = "SELECT postCode.postCode " +
                "FROM PostCode postCode " +
                "WHERE postCode.postCode = :postCode " ;
    	
    	 
        boolean result  = super.findObjectByQueryString(queryString, "postCode", postCode)!=null;
        return result;
    }
    
   
	@Override
	public PostCodeDTO findPostCode(String postCode) {
		PostCodeDTO postCodeDTO = null;
		
		String queryString = "SELECT postCode FROM PostCode postCode WHERE postCode.postCode = :postCode";
		PostCode postCodeEntity = super.findObjectByQueryString(queryString, "postCode",postCode);
		
		
		if(postCodeEntity!=null) {
			/**
			 * fill map coordinates with latitude and longitude of postCodeEntity found
			 */
			Coordinates coordinates = simpleMapper.map(postCodeEntity, Coordinates.class);
			postCodeDTO= new PostCodeDTO(postCode, coordinates);
		}
		return postCodeDTO;
	}

	@Override
	public PostCodeDTO updateCoordinates(String postCode, Coordinates coordinates) {
		PostCodeDTO postCodeDTO = null;
		
		String updateString = " update postcodelatlng "
							+ " set "
								+ " latitude = :latitude, "
								+ " longitude = :longitude "
							+ " where postcode= :postcode ";
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("postcode", postCode);
		parameters.put("latitude", coordinates.getLatitude());
		parameters.put("longitude", coordinates.getLongitude());
		
		
		int updated = super.updateByNativeQueryString(updateString,parameters);
		
		if(updated>0) {
			postCodeDTO=new PostCodeDTO();
			postCodeDTO.setPostCode(postCode);
			postCodeDTO.setCoordinates(coordinates);
		}
		return postCodeDTO;
				
	}

	@Override
	public void addPostCode(PostCodeDTO postCodeDTO) {
		PostCode postCode= simpleMapper.map(postCodeDTO.getCoordinates(), PostCode.class);
		postCode.setPostCode(postCodeDTO.getPostCode());
		
		super.persist(postCode);
 
	}

}