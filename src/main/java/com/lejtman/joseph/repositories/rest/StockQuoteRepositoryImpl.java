package com.lejtman.joseph.repositories.rest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lejtman.joseph.model.domain.Stock;
import com.lejtman.joseph.repositories.StockQuoteRepository;

@Repository
public class StockQuoteRepositoryImpl implements StockQuoteRepository {

	private final String restUrl;
	private final RestTemplate  restTemplate;
	
	
	/**
	 * @param restTemplate
	 */
	public StockQuoteRepositoryImpl(@Value("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=")String restUrl) {
		super();
		this.restUrl = restUrl;
		this.restTemplate = new RestTemplate();
	}


	@Override
	public Stock getQuote(String symbol) {
		MarkitOnDemandQuote quote =  restTemplate.getForObject(restUrl + symbol, MarkitOnDemandQuote.class);
		if(quote.getMessage() != null && quote.getMessage().startsWith("No symbol matches found for ")){
			throw new IllegalArgumentException(quote.getMessage());
		}
		if(quote.getSymbol() == null){
			throw new RestClientException("rest call failed " + quote);
		}
		return quote.toStock();
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class MarkitOnDemandQuote{
		private String message;
	

		private String symbol;
		private long priceInCents;
		private String timeStamp;
		private static final DateTimeFormatter dtFormatter =  DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss 'UTC'XXX uuuu");
		
		/**
		 * @param symbol the symbol to set
		 */
		@JsonProperty("Symbol")
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		
		/**
		 * @param lastPriceInCents the lastPriceInCents to set
		 */
		@JsonProperty("LastPrice")
		public void setLastPriceInCents(double lastPriceInCents) {
			this.priceInCents = Math.round(lastPriceInCents * 100);
		}
		
		/**
		 * @param timeStamp the timeStamp to set
		 */
		@JsonProperty("Timestamp")
		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}
		
		/**
		 * @param message the message to set
		 */
		@JsonProperty("Message")
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getMessage(){
			return message;
		}
		
		public String getSymbol(){
			return symbol;
		}
		
		public Stock toStock(){
			return new Stock(symbol, priceInCents, OffsetDateTime.parse(timeStamp, dtFormatter).toInstant());
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MarkitOnDemandQuote [message=" + message + ", symbol=" + symbol + ", priceInCents=" + priceInCents
					+ ", timeStamp=" + timeStamp + "]";
		}
		
		
		
		
	}
	
	

}
