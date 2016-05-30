package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import hb.utils.enums.Currencies;

@Entity
@Table(name="exchange_rates")
public class ExchangeRates extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1328749313174938239L;
	
	@NotNull
	private Currencies currency;
	@NotNull
	@Min(0)
	private Double sell;
	@NotNull
	@Min(0)
	private Double buy;
	
	public ExchangeRates() {
		super();
		this.currency = null;
		this.sell = null;
		this.buy = null;
	}
	public ExchangeRates(Currencies currency, Double sell, Double buy) {
		super();
		this.currency = currency;
		this.sell = sell;
		this.buy = buy;
	}
	
	public Currencies getCurrency() {
		return currency;
	}
	public void setCurrency(Currencies currency) {
		this.currency = currency;
	}
	public Double getSell() {
		return sell;
	}
	public void setSell(Double sell) {
		this.sell = sell;
	}
	public Double getBuy() {
		return buy;
	}
	public void setBuy(Double buy) {
		this.buy = buy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((buy == null) ? 0 : buy.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
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
		ExchangeRates other = (ExchangeRates) obj;
		if (buy == null) {
			if (other.buy != null)
				return false;
		} else if (!buy.equals(other.buy))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (sell == null) {
			if (other.sell != null)
				return false;
		} else if (!sell.equals(other.sell))
			return false;
		return true;
	}
}
