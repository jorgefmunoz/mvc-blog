package com.jmunoz.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jmunoz.blog.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserDto {
	
	@NotNull
    @NotEmpty
    private String username;
     
    @NotNull
    @NotEmpty
    private String fullName;
     
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
    
//  @NotNull
//  @NotEmpty
//  private String email;
   
  // standard getters and setters
    public UserDto() {
    	
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
     
    
}
