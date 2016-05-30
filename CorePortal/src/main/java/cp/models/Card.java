package cp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cp.utils.CardValidityDate;
import cp.utils.enums.CardType;
import cp.utils.enums.RatesType;

@Entity
@Table(name="cards")
public class Card extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 4607711123515271882L;
	
	@NotNull
	@Size(min = 16, max = 16)
	private String card_no;
	@NotNull
	private CardType card_type;
	@NotNull
	private CardValidityDate validity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private Rate rate;
	@NotNull
	private Double daily_limit;
	
	public Card() {
		super();
		this.card_no = null;
		this.card_type = null;
		this.validity = null;
		this.rate = null;
	}
	public Card(String card_no, CardType card_type, CardValidityDate validity, Double daily_limit) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.validity = validity;
		this.rate = new Rate(RatesType.N_A, 0.0);
		this.daily_limit = daily_limit;
	}
	public Card(String card_no, CardType card_type, CardValidityDate validity, Rate rate, Double daily_limit) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.validity = validity;
		this.rate = rate;
		this.daily_limit = daily_limit;
	}
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public CardType getCard_type() {
		return card_type;
	}
	public void setCard_type(CardType card_type) {
		this.card_type = card_type;
	}
	public CardValidityDate getValidity() {
		return validity;
	}
	public void setValidity(CardValidityDate validity) {
		this.validity = validity;
	}
	public Rate getId_rate() {
		return rate;
	}
	public void setId_rate(Rate rate) {
		this.rate = rate;
	}
	public Double getDaily_limit() {
		return daily_limit;
	}
	public void setDaily_limit(Double daily_limit) {
		this.daily_limit = daily_limit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((card_no == null) ? 0 : card_no.hashCode());
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
		Card other = (Card) obj;
		if (card_no == null) {
			if (other.card_no != null)
				return false;
		} else if (!card_no.equals(other.card_no))
			return false;
		return true;
	}	
}
