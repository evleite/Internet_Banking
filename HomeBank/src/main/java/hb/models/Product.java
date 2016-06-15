package hb.models;

public class Product {
	
	private Account account;
	private Card card;
	
	public Product(Account account){
		this.account = account;
		this.card = null;
	}
	
	public Product(Account account, Card card){
		this.account = account;
		this.card = card;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
