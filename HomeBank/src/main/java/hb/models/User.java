package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hb.utils.enums.AuthenticationType;

@Entity
@Table(name="users_hb")
public class User extends AbstractModel implements Serializable{
	
	private static final long serialVersionUID = -7538073709867474311L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 4, max = 30)
	private String username;
	@NotNull
	@Size(min = 4, max = 30)
	private String password;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	@Size(min = 13, max = 13)
	private String CNP;
	@NotNull
	private String email;
	@NotNull
	private String address;
	@NotNull
	private String telephone;
	@NotNull
	private AuthenticationType auth_type;
		
	public User() {
		super();
		this.username = null;
		this.password = null;
		this.firstname = null;
		this.lastname = null;
		this.CNP = null;
		this.email = null;
		this.address = null;
		this.telephone = null;
		this.auth_type = null;
	}
	
	public User(String username, String password, String firstname, String lastname, String cNP, String email,
			String address, String telephone, AuthenticationType auth_type) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.CNP = cNP;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.auth_type = auth_type;
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCNP() {
		return CNP;
	}
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public AuthenticationType getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(AuthenticationType auth_type) {
		this.auth_type = auth_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
