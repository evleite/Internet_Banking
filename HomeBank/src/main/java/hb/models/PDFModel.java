package hb.models;

import java.util.List;

public class PDFModel {
	
	private Account account;
	private Card card;
	private List<Transaction> transactionList;
	
	public PDFModel(Account account){
		this.account = account;
		this.card = null;
	}
	
	public PDFModel(Account account, Card card){
		this.account = account;
		this.card = card;
	}
	
	public PDFModel(Account account, Card card, List<Transaction> transactionList){
		this.account = account;
		this.card = card;
		this.transactionList = transactionList;
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

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
}
