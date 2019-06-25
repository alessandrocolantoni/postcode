package it.wcc.postcode.dao.postcode;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import it.wcc.postcode.config.AppConfigTest;
import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostCodeDaoIT {

    private static final Logger log = LoggerFactory.getLogger(PostCodeDaoIT.class);

    @Autowired
    private PostCodeDao postCodeDao;

    @Test
    public void testVerifyExistence () {
        log.info("verify the existence of a postcode");
        boolean postCodeExists = postCodeDao.verifyExistence("AAAA BBB");
        assertTrue(postCodeExists, "'AAAA BBB' existence error");
        boolean postCodeNoExists = postCodeDao.verifyExistence("NO EXISTS");
        assertFalse(postCodeNoExists, "'NO EXISTS' no existence error"); 
    }

    @Test
    public void testFindPostCode() {
        log.info("test find postcode");
        
        PostCodeDTO postCodeDTO = postCodeDao.findPostCode("AAAA BBB");
        assertNotNull(postCodeDTO, "postCodeDTO not found");
        assertEquals("postCodeDTO.postCode", "AAAA BBB", postCodeDTO.getPostCode());
        
        assertNotNull(postCodeDTO.getCoordinates(), "Coordinates are null");
        assertEquals("latitude", 57.124273770000000000, postCodeDTO.getCoordinates().getLatitude());
        assertEquals("longitude", -2.127189644000000000, postCodeDTO.getCoordinates().getLongitude());
        
        PostCodeDTO notExistsPostCodeDTO = postCodeDao.findPostCode("NOT EXISTS");
        assertNull(notExistsPostCodeDTO, "postcode NOT EXISTS actually exists");
        
    }

    

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateCoordinates() {
    	
    	log.info("test update coordinates");
    	
    	Coordinates coordinates = new Coordinates();
 
    	
    	coordinates.setLatitude(50);
    	coordinates.setLongitude(10);
    	
    	postCodeDao.updateCoordinates("AAAA BBB", coordinates);
    	
    	PostCodeDTO postCodeDTO = postCodeDao.findPostCode("AAAA BBB");
    	
    	assertEquals("latitude", new Double(50), postCodeDTO.getCoordinates().getLatitude());
        assertEquals("longitude", new Double(10), postCodeDTO.getCoordinates().getLongitude());
            
    }

    
    @Test
    @Transactional
    @Rollback(true)
    public void testAddPostCode() {
    	
    	log.info("test update coordinates");
    	
    	Coordinates coordinates = new Coordinates();
    	coordinates.setLatitude(30);
    	coordinates.setLongitude(15);
    	
    	PostCodeDTO toBeAddedPostCodeDTO = new PostCodeDTO("CCCC DDD", coordinates);
    	
    	postCodeDao.addPostCode(toBeAddedPostCodeDTO);
    	
    	PostCodeDTO postCodeDTO = postCodeDao.findPostCode("CCCC DDD");
        assertNotNull(postCodeDTO, "postCodeDTO not found");
        assertEquals("postCodeDTO.postCode", "CCCC DDD", postCodeDTO.getPostCode());
        
        assertNotNull(postCodeDTO.getCoordinates(), "Coordinates are null");
        assertEquals("latitude", new Double(30), postCodeDTO.getCoordinates().getLatitude());
        assertEquals("longitude",new Double(15), postCodeDTO.getCoordinates().getLongitude());
        
    }

      
}
