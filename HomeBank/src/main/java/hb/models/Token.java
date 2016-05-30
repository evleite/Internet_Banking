package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import hb.utils.enums.TokenType;

@Entity
@Table(name="tokens")
public class Token extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -4822196739971621567L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Min(100000)
	private Long serial;
	@NotNull
	private TokenType token_type;
	
	public Token() {
		super();
		this.serial = null;
		this.token_type = null;
	}
	public Token(Long serial, TokenType token_type) {
		super();
		this.serial = serial;
		this.token_type = token_type;
	}
	
	public Long getId() {
		return id;
	}
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public TokenType getToken_type() {
		return token_type;
	}
	public void setToken_type(TokenType token_type) {
		this.token_type = token_type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((token_type == null) ? 0 : token_type.hashCode());
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
		Token other = (Token) obj;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (token_type != other.token_type)
			return false;
		return true;
	}
}
