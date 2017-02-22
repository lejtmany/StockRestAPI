package com.lejtman.joseph.repositories.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.lejtman.joseph.model.domain.Stock;

public class StockQuoteRepositoryImplTests {

	private StockQuoteRepositoryImpl repo = new StockQuoteRepositoryImpl("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=");
	

	@Test
	public void testRepoWorks() {
		Stock s = repo.getQuote("msft");
		assertThat(s.getPriceInCents()).isGreaterThan(0);
		assertEquals(s.getSymbol().toLowerCase(), "msft");
		assertThat(s.getTimestamp().until(Instant.now(), ChronoUnit.HOURS) < 72);
	}

}
