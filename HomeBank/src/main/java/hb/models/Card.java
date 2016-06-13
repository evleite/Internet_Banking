package hb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hb.utils.enums.CardType;

@Entity
@Table(name="cards")
public class Card extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 4607711123515271882L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 16, max = 16)
	private String card_no;
	@NotNull
	private CardType card_type;
	@NotNull
	private String validity;
	@NotNull
	private Double daily_limit;
	
	public Card() {
		super();
		this.card_no = null;
		this.card_type = null;
		this.validity = null;
		this.daily_limit = null;
	}
	public Card(String card_no, CardType card_type, String validity, Double daily_limit) {
		super();
		this.card_no = card_no;
		this.card_type = card_type;
		this.validity = validity;
		this.daily_limit = daily_limit;
	}
	
	public Long getId() {
		return id;
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
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
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
		int result = 1;
		result = prime * result + ((card_no == null) ? 0 : card_no.hashCode());
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
		Card other = (Card) obj;
		if (card_no == null) {
			if (other.card_no != null)
				return false;
		} else if (!card_no.equals(other.card_no))
			return false;
		return true;
	}	
}
