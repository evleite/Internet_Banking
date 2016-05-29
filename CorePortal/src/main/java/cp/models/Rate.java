package cp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="rates")
public class Rate extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 6023593720134642339L;
	
	@NotNull
	private String rate_type;
	@NotNull
	@Min(0)
	@Max(100)
	private Double year_percentage;
	@NotNull
	private String details;
	
	public Rate() {
		super();
		this.rate_type = null;
		this.year_percentage = null;
	}
	public Rate(String rate_type, Double year_percentage) {
		super();
		this.rate_type = rate_type;
		this.year_percentage = year_percentage;
		this.details = "N/A";
	}
	public Rate(String rate_type, Double year_percentage, String details) {
		super();
		this.rate_type = rate_type;
		this.year_percentage = year_percentage;
		this.details = details;
	}
	
	public String getRate_type() {
		return rate_type;
	}
	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}
	public Double getYear_percentage() {
		return year_percentage;
	}
	public void setYear_percentage(Double year_percentage) {
		this.year_percentage = year_percentage;
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
		result = prime * result + ((rate_type == null) ? 0 : rate_type.hashCode());
		result = prime * result + ((year_percentage == null) ? 0 : year_percentage.hashCode());
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
		Rate other = (Rate) obj;
		if (rate_type == null) {
			if (other.rate_type != null)
				return false;
		} else if (!rate_type.equals(other.rate_type))
			return false;
		if (year_percentage == null) {
			if (other.year_percentage != null)
				return false;
		} else if (!year_percentage.equals(other.year_percentage))
			return false;
		return true;
	}
}
