package com.lejtman.joseph.repositories;

import com.lejtman.joseph.model.domain.Stock;

public interface StockQuoteRepository {
	public Stock getQuote(String symbol);
}
