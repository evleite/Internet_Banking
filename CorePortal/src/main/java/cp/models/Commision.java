package cp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="commisions")
public class Commision extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -1670483841565764140L;
	
	@NotNull
	private String comm_type;
	@NotNull
	@Min(0)
	private Double amount;
	@NotNull
	private String details;
	
	public Commision() {
		super();
		this.comm_type = null;
		this.amount = null;
	}
	public Commision(String comm_type, Double amount) {
		super();
		this.comm_type = comm_type;
		this.amount = amount;
		this.details = "N/A";
	}
	public Commision(String comm_type, Double amount, String details) {
		super();
		this.comm_type = comm_type;
		this.amount = amount;
		this.details = details;
	}
	
	public String getComm_type() {
		return comm_type;
	}
	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((comm_type == null) ? 0 : comm_type.hashCode());
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
		Commision other = (Commision) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (comm_type == null) {
			if (other.comm_type != null)
				return false;
		} else if (!comm_type.equals(other.comm_type))
			return false;
		return true;
	}
}
