package com.lejtman.joseph.model.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StockId.class)
public class Stock {
	@Id
	@Enumerated(EnumType.STRING)
	private Exchange exchange;
	@Id
	private String symbol;
	/**
	 * @param exchange
	 * @param symbol
	 */
	public Stock(Exchange exchange, String symbol) {
		super();
		this.exchange = exchange;
		this.symbol = symbol;
	}
	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Stock other = (Stock) obj;
		if (exchange != other.exchange)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [exchange=" + exchange + ", symbol=" + symbol + "]";
	}
	

	
	
}
