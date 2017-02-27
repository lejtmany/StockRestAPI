package com.lejtman.joseph.model.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Portfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany
	private List<Stock> stocks;
	
	@OneToMany 
	private List<Transaction> transactionHistory;
	@ManyToOne
	private User user;
	private Instant createdTimestamp;
	/**
	 * @param stocks
	 * @param user
	 * @param createdTimestamp
	 */
	public Portfolio(User user, Instant createdTimestamp) {
		super();
		this.stocks = new ArrayList<>();
		this.transactionHistory = new ArrayList<>();
		this.user = user;
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the stocks
	 */
	public List<Stock> getStocks() {
		return new ArrayList<>(stocks);
	}
	
	public List<Transaction> getTransactionHistory(){
		return new ArrayList<>(transactionHistory);
	}
	
	public boolean addStock(Stock s){
		return stocks.add(s);
	}
	
	public boolean removeStock(Stock s){
		return stocks.remove(s);
	}
	
	public boolean addTranscation(Transaction t){
		return transactionHistory.add(t);
	}
	
	public boolean removeTranscation(Transaction t){
		return transactionHistory.remove(t);
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @return the createdTimestamp
	 */
	public Instant getCreatedTimestamp() {
		return createdTimestamp;
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
		Portfolio other = (Portfolio) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", stocks=" + stocks + ", user=" + user + ", createdTimestamp="
				+ createdTimestamp + "]";
	}
	
	
}
