package it.wcc.postcode.dao.postcode;


import it.wcc.postcode.dto.Coordinates;
import it.wcc.postcode.dto.PostCodeDTO;



public interface PostCodeDao {

	public PostCodeDTO findPostCode(String postCode);

	public PostCodeDTO updateCoordinates(String postCode, Coordinates coordinates);
    
    public void addPostCode(PostCodeDTO postCodeDTO);

    public boolean verifyExistence(String postCode);

     
}
