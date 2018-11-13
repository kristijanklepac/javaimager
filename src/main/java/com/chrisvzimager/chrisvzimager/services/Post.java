/**
 * 
 */
package com.chrisvzimager.chrisvzimager.services;

/**
 * @author Kristijan Klepač
 * @email kristijan.klepac@gmail.com
 */
public class Post {
	
	private int Id;
    
    public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}



	public String getTokenName() {
		return TokenName;
	}



	public void setTokenName(String tokenName) {
		TokenName = tokenName;
	}



	public String getTokenValue() {
		return TokenValue;
	}



	public void setTokenValue(String tokenValue) {
		TokenValue = tokenValue;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public String getUserFolder() {
		return UserFolder;
	}



	public void setUserFolder(String userFolder) {
		UserFolder = userFolder;
	}



	private String TokenName;
    private String TokenValue;
    private String Email;
    private String UserFolder;
     
    
 
    public String toString(){
        return this.Id+" | "+this.TokenName+" | "+this.TokenValue+" | "+this.Email+" | "+this.UserFolder; 
    }

}
