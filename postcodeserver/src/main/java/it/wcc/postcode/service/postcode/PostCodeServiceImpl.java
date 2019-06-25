package it.wcc.postcode.service.postcode;


import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.wcc.postcode.dao.postcode.PostCodeDao;
import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;

@Service
public class PostCodeServiceImpl implements PostCodeService {


 
	 
 
	private static final long serialVersionUID = 1L;


	private static final Logger log = LoggerFactory.getLogger(PostCodeServiceImpl.class);
	
	
	@Autowired
	private PostCodeDao postCodeDao;
	



	@Override
	public PostCodeDTO findPostCode(String postCode) {
		try {
			return  postCodeDao.findPostCode(postCode);
		} catch (Throwable e) {
			log.error("Error finding postCode : "+ (postCode == null ?"":postCode) ,e);
			throw new ServiceException("Error finding postCode: "+ (postCode == null ?"":postCode) , e);

		}
	}

	@Override
	public PostCodeDTO updateCoordinates(String postCode, Coordinates coordinates) {
		try {
			return postCodeDao.updateCoordinates(postCode, coordinates);
		} catch (Throwable e) {
			log.error("Error updating coordinates ",e);
			throw new ServiceException("Error updating coordinates " , e);
		}
	}

	@Override
	public void addPostCode(PostCodeDTO postCodeDTO) {
		try {
			postCodeDao.addPostCode(postCodeDTO);
		} catch (Exception e) {
			log.error("Error adding a post code ",e);
			throw new ServiceException("Error adding a post code " , e);
		}
		
	}
	
    public boolean verifyExistence(String postCode) {
    	try {
			return postCodeDao.verifyExistence(postCode);
		} catch (Exception e) {
			log.error("Error verifying postCode existence : "+ (postCode == null ?"":postCode) ,e);
			throw new ServiceException("Error verifying postCode existence : "+ (postCode == null ?"":postCode) , e);
		}
    }

}
