package cp.utils;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CardValidityDate implements Serializable{

	private static final long serialVersionUID = -2504618988718857462L;
	
	@NotNull
	@Min(1)
	@Max(12)
	private Integer mounth;
	@NotNull
	@Min(1900)
	@Max(9999)
	private Integer year;
	
	public CardValidityDate(Integer mounth, Integer year) {
		super();
		this.mounth = mounth;
		this.year = year;
	}

	public Integer getMounth() {
		return mounth;
	}

	public void setMounth(Integer mounth) {
		this.mounth = mounth;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mounth == null) ? 0 : mounth.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		CardValidityDate other = (CardValidityDate) obj;
		if (mounth == null) {
			if (other.mounth != null)
				return false;
		} else if (!mounth.equals(other.mounth))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
