package com.lejtman.joseph.model.domain;

import java.time.Instant;

public class Stock {
	private final String symbol;
	private final long priceInCents;
	private final Instant timestamp;
	/**
	 * @param symbol
	 * @param priceInCents
	 */
	public Stock(String symbol, long priceInCents, Instant timestamp) {
		super();
		this.symbol = symbol;
		this.priceInCents = priceInCents;
		this.timestamp = timestamp;
	}
	
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
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
	
	
}
