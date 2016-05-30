package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import hb.utils.enums.RatesType;

@Entity
@Table(name="rates")
public class Rate extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 6023593720134642339L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	private RatesType rate_type;
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
	public Rate(RatesType rate_type, Double year_percentage) {
		super();
		this.rate_type = rate_type;
		this.year_percentage = year_percentage;
		this.details = "N/A";
	}
	public Rate(RatesType rate_type, Double year_percentage, String details) {
		super();
		this.rate_type = rate_type;
		this.year_percentage = year_percentage;
		this.details = details;
	}
	
	public Long getId() {
		return id;
	}
	public RatesType getRate_type() {
		return rate_type;
	}
	public void setRate_type(RatesType rate_type) {
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
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((rate_type == null) ? 0 : rate_type.hashCode());
		result = prime * result + ((year_percentage == null) ? 0 : year_percentage.hashCode());
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
		Rate other = (Rate) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (rate_type != other.rate_type)
			return false;
		if (year_percentage == null) {
			if (other.year_percentage != null)
				return false;
		} else if (!year_percentage.equals(other.year_percentage))
			return false;
		return true;
	}
}
