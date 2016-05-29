package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tokens")
public class Token extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -4822196739971621567L;
	
	@NotNull
	@Min(100000)
	private Long serial;
	@NotNull
	private String token_type;
	
	public Token() {
		super();
		this.serial = null;
		this.token_type = null;
	}
	public Token(Long serial, String token_type) {
		super();
		this.serial = serial;
		this.token_type = token_type;
	}
	
	public Long getSerial() {
		return serial;
	}
	public void setSerial(Long serial) {
		this.serial = serial;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((token_type == null) ? 0 : token_type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (token_type == null) {
			if (other.token_type != null)
				return false;
		} else if (!token_type.equals(other.token_type))
			return false;
		return true;
	}
}
