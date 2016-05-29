package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="accounts")
public class Account extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 3944846479256785516L;
	
	@NotNull
	@Size(min = 24, max = 34)
	private String IBAN;
	@NotNull
	private String acc_type;
	@NotNull
	private String currency;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private Commision comm_admin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private Rate rate;
	
	public Account() {
		super();
		IBAN = null;
		this.acc_type = null;
		this.currency = null;
		this.comm_admin = null;
		this.rate = null;
	}
	public Account(String iBAN, String acc_type, String currency, Commision comm_admin) {
		super();
		IBAN = iBAN;
		this.acc_type = acc_type;
		this.currency = currency;
		this.comm_admin = comm_admin;
		this.rate = new Rate("N/A", 0.0);
	}
	public Account(String iBAN, String acc_type, String currency, Commision comm_admin, Rate rate) {
		super();
		IBAN = iBAN;
		this.acc_type = acc_type;
		this.currency = currency;
		this.comm_admin = comm_admin;
		this.rate = rate;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Commision getId_comm_admin() {
		return comm_admin;
	}
	public void setId_comm_admin(Commision comm_admin) {
		this.comm_admin = comm_admin;
	}
	public Rate getId_rate() {
		return rate;
	}
	public void setId_rate(Rate rate) {
		this.rate = rate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((IBAN == null) ? 0 : IBAN.hashCode());
		result = prime * result + ((acc_type == null) ? 0 : acc_type.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((comm_admin == null) ? 0 : comm_admin.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		Account other = (Account) obj;
		if (IBAN == null) {
			if (other.IBAN != null)
				return false;
		} else if (!IBAN.equals(other.IBAN))
			return false;
		if (acc_type == null) {
			if (other.acc_type != null)
				return false;
		} else if (!acc_type.equals(other.acc_type))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (comm_admin == null) {
			if (other.comm_admin != null)
				return false;
		} else if (!comm_admin.equals(other.comm_admin))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}
}
