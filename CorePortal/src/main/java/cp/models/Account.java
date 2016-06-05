package cp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cp.utils.DataBase;
import cp.utils.enums.AccountType;
import cp.utils.enums.Currencies;
import cp.utils.enums.RatesType;

@Entity
@Table(name="accounts")
public class Account extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 3944846479256785516L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	@NotNull
	@Size(min = 24, max = 34)
	private String IBAN;
	@NotNull
	private AccountType acc_type;
	@NotNull
	private Currencies currency;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comm", nullable = false)
	private Commision comm_admin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rate", nullable = false)
	private Rate rate;
	@NotNull
	private Double balance;
	
	public Account() {
		super();
		IBAN = null;
		this.acc_type = null;
		this.currency = null;
		this.comm_admin = null;
		this.rate = null;
	}
	public Account(String iBAN, AccountType acc_type, Currencies currency, Commision comm_admin, Double balance) {
		super();
		IBAN = iBAN;
		this.acc_type = acc_type;
		this.currency = currency;
		this.comm_admin = comm_admin;
		this.rate = DataBase.getFalseRate();
		this.balance = balance;
	}
	public Account(String iBAN, AccountType acc_type, Currencies currency, Commision comm_admin, Rate rate, Double balance) {
		super();
		IBAN = iBAN;
		this.acc_type = acc_type;
		this.currency = currency;
		this.comm_admin = comm_admin;
		this.rate = rate;
		this.balance = balance;
	}
	
	public Long getId() {
		return id;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public AccountType getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(AccountType acc_type) {
		this.acc_type = acc_type;
	}
	public Currencies getCurrency() {
		return currency;
	}
	public void setCurrency(Currencies currency) {
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IBAN == null) ? 0 : IBAN.hashCode());
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
		Account other = (Account) obj;
		if (IBAN == null) {
			if (other.IBAN != null)
				return false;
		} else if (!IBAN.equals(other.IBAN))
			return false;
		return true;
	}	
}
