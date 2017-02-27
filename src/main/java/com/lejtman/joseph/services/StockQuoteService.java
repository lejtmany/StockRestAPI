package com.lejtman.joseph.services;

import com.lejtman.joseph.model.domain.StockQuote;

public interface StockQuoteService {
	public StockQuote getQuote(String symbol);
}
