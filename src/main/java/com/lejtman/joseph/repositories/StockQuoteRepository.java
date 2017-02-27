package com.lejtman.joseph.repositories;

import org.springframework.stereotype.Repository;

import com.lejtman.joseph.model.domain.StockQuote;

@Repository
public interface StockQuoteRepository {
	public StockQuote getQuote(String symbol);
}
