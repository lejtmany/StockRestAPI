package com.lejtman.joseph.model.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private StockQuote stock;
	@Enumerated(EnumType.STRING)
	private TransactionAction action;
	private long quantity;
	/**
	 * @param stock
	 * @param action
	 * @param quantity
	 */
	public Transaction(StockQuote stock, TransactionAction action, long quantity) {
		super();
		this.stock = stock;
		this.action = action;
		this.quantity = quantity;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the stock
	 */
	public StockQuote getStock() {
		return stock;
	}
	/**
	 * @return the action
	 */
	public TransactionAction getAction() {
		return action;
	}
	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", stock=" + stock + ", action=" + action + ", quantity=" + quantity + "]";
	}
	
	
	
}
