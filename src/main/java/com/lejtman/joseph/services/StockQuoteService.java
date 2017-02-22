package com.lejtman.joseph.services;

import com.lejtman.joseph.model.domain.Stock;

public interface StockQuoteService {
	public Stock getQuote(String symbol);
}
