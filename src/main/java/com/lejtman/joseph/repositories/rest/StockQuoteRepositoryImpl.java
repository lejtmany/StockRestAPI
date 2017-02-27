package com.lejtman.joseph.repositories.rest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lejtman.joseph.model.domain.Exchange;
import com.lejtman.joseph.model.domain.Stock;
import com.lejtman.joseph.model.domain.StockQuote;
import com.lejtman.joseph.repositories.StockQuoteRepository;

@Repository
public class StockQuoteRepositoryImpl implements StockQuoteRepository {

	private final String quoteUrl;
	private final String exchangeLookupUrl;
	private final RestTemplate  restTemplate;
	
	
	/**
	 * @param restTemplate
	 */
	public StockQuoteRepositoryImpl(@Value("http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=")String exchangeLookupUrl, @Value("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=")String quoteUrl) {
		super();
		this.quoteUrl = quoteUrl;
		this.exchangeLookupUrl = exchangeLookupUrl;
		this.restTemplate = new RestTemplate();
	}
	
	private Exchange getExchange(String symbol){
		List<MarkitOnDemandLookup> symbolLookups = restTemplate.exchange(exchangeLookupUrl + symbol, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MarkitOnDemandLookup>>() {
				}).getBody();
		if(!symbolLookups.isEmpty() && symbolLookups.get(0).getSymbol().equalsIgnoreCase(symbol)){
			return symbolLookups.get(0).getExchange();
		}
		return null;
	}


	@Override
	public StockQuote getQuote(String symbol) {
		MarkitOnDemandQuote quote =  restTemplate.getForObject(quoteUrl + symbol, MarkitOnDemandQuote.class);
		if(quote.getMessage() != null && quote.getMessage().startsWith("No symbol matches found for ")){
			throw new IllegalArgumentException(quote.getMessage());
		}
		if(quote.getSymbol() == null){
			throw new RestClientException("rest call failed " + quote);
		}
		quote.setExchange(getExchange(symbol));
		return quote.toStock();
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class MarkitOnDemandLookup{
		private String symbol;
		private String name;
		private String exchange;

		
		/**
		 * @return the symbol
		 */
		public String getSymbol() {
			return symbol;
		}
		/**
		 * @param symbol the symbol to set
		 */
		@JsonProperty("Symbol")
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		@JsonProperty("Name")
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the exchange
		 */
		public Exchange getExchange() {
			Exchange e;
			switch(exchange){
			case "NASDAQ":
				e = Exchange.NASDAQ;
				break;
			case "NYSE":
				e = Exchange.NYSE;
				break;
			default:
				e = null;
			}
			return e;
		}
		/**
		 * @param exchange the exchange to set
		 */
		@JsonProperty("Exchange")
		public void setExchange(String exchange) {
			this.exchange = exchange;
		}
		
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class MarkitOnDemandQuote{
		private String message;
	
		private Exchange exchange;
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
		
		/**
		 * @param exchange the exchange to set
		 */
		public void setExchange(Exchange exchange) {
			this.exchange = exchange;
		}

		public StockQuote toStock(){
			return new StockQuote(new Stock(exchange, symbol), priceInCents, OffsetDateTime.parse(timeStamp, dtFormatter).toInstant());
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
