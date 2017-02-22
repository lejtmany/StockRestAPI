package com.lejtman.joseph.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lejtman.joseph.model.domain.Stock;
import com.lejtman.joseph.repositories.StockQuoteRepository;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

	
	private StockQuoteRepository stockQuoteRepo;
	
	
	@Override
	public Stock getQuote(String symbol) {
		return stockQuoteRepo.getQuote(symbol);
	}


	/**
	 * @param stockQuoteRepo the stockQuoteRepo to set
	 */
	@Autowired
	public void setStockQuoteRepo(StockQuoteRepository stockQuoteRepo) {
		this.stockQuoteRepo = stockQuoteRepo;
	}


	
	
	

}
