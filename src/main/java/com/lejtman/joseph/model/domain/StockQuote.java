package com.lejtman.joseph.model.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class StockQuote {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private final Stock stock;
	private final long priceInCents;
	private final Instant timestamp;
	
	@ManyToOne
	private Portfolio portfolio;
	/**
	 * @param stock
	 * @param priceInCents
	 */
	public StockQuote(Stock stock, long priceInCents, Instant timestamp) {
		super();
		this.stock = stock;
		this.priceInCents = priceInCents;
		this.timestamp = timestamp;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the symbol
	 */
	public Stock getStock() {
		return stock;
	}
	
	/**
	 * @return the priceInCents
	 */
	public long getPriceInCents() {
		return priceInCents;
	}

	/**
	 * @return the timestamp
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [id=" + id + ", symbol=" + stock + ", priceInCents=" + priceInCents + ", timestamp=" + timestamp
				+ ", portfolio=" + portfolio + "]";
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
		StockQuote other = (StockQuote) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
