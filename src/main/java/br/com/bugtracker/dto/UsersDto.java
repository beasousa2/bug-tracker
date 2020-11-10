package br.com.bugtracker.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

public class UsersDto implements Serializable {

	private Long id;

	@NotNull(message = "Your forgot the name")
	@NotBlank(message = "The name cannot be empty")
	@Length(min = 3, max = 250, message = "The name is too short or too long, please try again")
	private String name;

	@NotNull(message = "Your forgot the e-mail")
	@javax.validation.constraints.Email
	@NotBlank(message = "The e-mail cannot be empty")
	@Length(min = 10, max = 100, message = "The e-mail is too short or too long, please try again")
	private String email;

	@NotBlank(message = "The compay position cannot be empty")
	@Length(min = 3, max = 100, message = "The company position is too short or too long, please try again")
	private String companyPosition;

	@NotNull(message = "Your forgot the role")
	@NotBlank(message = "The role cannot be empty")
	@Length(min = 5, message = "The role is too short, please try again")
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}

	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
