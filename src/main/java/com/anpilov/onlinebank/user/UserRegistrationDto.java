package com.anpilov.onlinebank.user;



/**
 * The UserRegistrationDto class represents a data transfer object for registering a new user in the online bank system.
 *
 * @author Anpilov Kirill
 * @version 1.0
 */

public class UserRegistrationDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dataBirth;




	public UserRegistrationDto() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDataBirth() {
		return dataBirth;
	}

	public void setDataBirth(String dataBirth) {
		this.dataBirth = dataBirth;
	}
}
