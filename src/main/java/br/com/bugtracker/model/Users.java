package br.com.bugtracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * 
 * @author Beatriz
 * @version 1.0.0
 * @created 09/11/2020
 *
 */

@Entity
@Table(name = "users")
@NamedNativeQueries({
	@NamedNativeQuery(name = "USER_LIST", query = "SELECT * FROM users", resultClass = Users.class),
	@NamedNativeQuery(name = "USER_BY_ID", query = ""
			+ "SELECT * FROM users WHERE id = :id", resultClass = Users.class),
	@NamedNativeQuery(name = "INSERT_USER", query = ""
			+ "INSERT INTO users (name, email, companyPosition, role) "
			+ "VALUES (:name, :email, :companyPosition, :role)"),
	@NamedNativeQuery(name = "UPDATE_USER", query = ""
			+ "UPDATE users set name = :name, email = :email, "
			+ "companyPosition = :companyPosition, role = :role "
			+ "WHERE id = :id"),
	@NamedNativeQuery(name = "DELETE_USER", query = ""
			+ "DELETE users WHERE id = :id")
})
public class Users extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 250, nullable = false)
	private String name;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "companyPosition", length = 100, nullable = true)
	private String companyPosition;
	
	@Column(name = "role", length = 5, nullable = false)
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
