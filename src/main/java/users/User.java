package users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@KeySpace("user")
public class User {
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private int birthYear;
	private List<String> roles;

	public User() {
		this.roles = new ArrayList<>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "{"
			+ "email:" + email 
			+ ", firstName:" + firstName 
			+ ", lastName:" + lastName 
			+ ", birthYear:" + birthYear
			+ ", roles:" + roles 
			+ "}";
	}
}
