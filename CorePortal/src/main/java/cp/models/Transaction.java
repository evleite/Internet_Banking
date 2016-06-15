package cp.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cp.utils.enums.TransactionStatus;
import cp.utils.enums.TransactionType;

@Entity
@Table(name="transactions")
public class Transaction extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 4102815835748372862L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 24, max = 34)
	private String payer_IBAN;
	@NotNull
	@Size(min = 24, max = 34)
	private String beneficiary_IBAN;
	@NotNull
	private TransactionType trans_type;
	@NotNull
	@Min(0)
	private Double amount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comm", nullable = false)
	private Commision trans_comm;
	@NotNull
	private String details;
	@NotNull
	private TransactionStatus status;
	@NotNull
	private Date date;
	
	public Transaction() {
		super();
		this.payer_IBAN = null;
		this.beneficiary_IBAN = null;
		this.trans_type = null;
		this.amount = null;
		this.trans_comm = null;
		this.details = null;
		this.date = null;
	}
	public Transaction(String payer_IBAN, String beneficiary_IBAN, TransactionType trans_type, Double amount,
			Commision trans_comm, TransactionStatus status) {
		super();
		this.payer_IBAN = payer_IBAN;
		this.beneficiary_IBAN = beneficiary_IBAN;
		this.trans_type = trans_type;
		this.amount = amount;
		this.trans_comm = trans_comm;
		this.details = "N/A";
		this.status = status;
		this.date = new Date();
	}
	public Transaction(String payer_IBAN, String beneficiary_IBAN, TransactionType trans_type, Double amount,
			Commision trans_comm, String details, TransactionStatus status) {
		super();
		this.payer_IBAN = payer_IBAN;
		this.beneficiary_IBAN = beneficiary_IBAN;
		this.trans_type = trans_type;
		this.amount = amount;
		this.trans_comm = trans_comm;
		this.details = details;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	public String getPayer_IBAN() {
		return payer_IBAN;
	}
	public void setPayer_IBAN(String payer_IBAN) {
		this.payer_IBAN = payer_IBAN;
	}
	public String getBeneficiary_IBAN() {
		return beneficiary_IBAN;
	}
	public void setBeneficiary_IBAN(String beneficiary_IBAN) {
		this.beneficiary_IBAN = beneficiary_IBAN;
	}
	public TransactionType getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(TransactionType trans_type) {
		this.trans_type = trans_type;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Commision getId_trans_comm() {
		return trans_comm;
	}
	public void setId_trans_comm(Commision trans_comm) {
		this.trans_comm = trans_comm;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((beneficiary_IBAN == null) ? 0 : beneficiary_IBAN.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((payer_IBAN == null) ? 0 : payer_IBAN.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((trans_comm == null) ? 0 : trans_comm.hashCode());
		result = prime * result + ((trans_type == null) ? 0 : trans_type.hashCode());
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
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (beneficiary_IBAN == null) {
			if (other.beneficiary_IBAN != null)
				return false;
		} else if (!beneficiary_IBAN.equals(other.beneficiary_IBAN))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (payer_IBAN == null) {
			if (other.payer_IBAN != null)
				return false;
		} else if (!payer_IBAN.equals(other.payer_IBAN))
			return false;
		if (status != other.status)
			return false;
		if (trans_comm == null) {
			if (other.trans_comm != null)
				return false;
		} else if (!trans_comm.equals(other.trans_comm))
			return false;
		if (trans_type != other.trans_type)
			return false;
		return true;
	}
}
