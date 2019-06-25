package it.wcc.postcode.service.postcode;

import java.io.Serializable;

import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;


public interface PostCodeService extends Serializable {

	public PostCodeDTO findPostCode(String postCode);

	public PostCodeDTO updateCoordinates(String postCode, Coordinates coordinates);
    
    public void addPostCode(PostCodeDTO postCodeDTO);
    
    public boolean verifyExistence(String postCode);



     
 
}
