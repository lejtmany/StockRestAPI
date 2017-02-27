package com.lejtman.joseph.repositories.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.lejtman.joseph.model.domain.Exchange;
import com.lejtman.joseph.model.domain.StockQuote;

public class StockQuoteRepositoryImplTests {

	private StockQuoteRepositoryImpl repo = new StockQuoteRepositoryImpl("http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input=", "http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=");
	

	@Test
	public void testRepoWorks() {
		StockQuote s = repo.getQuote("msft");
		assertThat(s.getPriceInCents()).isGreaterThan(0);
		assertEquals(s.getStock().getSymbol().toLowerCase(), "msft");
		assertThat(s.getTimestamp().until(Instant.now(), ChronoUnit.HOURS) < 72);
		assertThat(s.getStock().getExchange().equals(Exchange.NASDAQ));
	}
	
	@Test
	public void testRepoWhenInvalidSymbol() {
		assertThatThrownBy(()-> repo.getQuote("blahblah")).isInstanceOf(IllegalArgumentException.class);
	}

}
