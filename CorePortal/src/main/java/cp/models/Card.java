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

@Entity
@Table(name="cards")
public class Card extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 4607711123515271882L;
	
	@NotNull
	@Size(min = 16, max = 16)
	private String card_no;
	@NotNull
	private String card_type;
	@NotNull
	private CardValidityDate validity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = false)
	private Rate rate;
	
	public Card() {
		super();
		this.card_no = null;
		this.card_type = null;
		this.validity = null;
		this.rate = null;
	}
	public Card(String card_no, String card_type, CardValidityDate validity) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.validity = validity;
		this.rate = new Rate("N/A", 0.0);
	}
	public Card(String card_no, String card_type, CardValidityDate validity, Rate rate) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.validity = validity;
		this.rate = rate;
	}
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((card_no == null) ? 0 : card_no.hashCode());
		result = prime * result + ((card_type == null) ? 0 : card_type.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((validity == null) ? 0 : validity.hashCode());
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
		if (card_type == null) {
			if (other.card_type != null)
				return false;
		} else if (!card_type.equals(other.card_type))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (validity == null) {
			if (other.validity != null)
				return false;
		} else if (!validity.equals(other.validity))
			return false;
		return true;
	}	
}
