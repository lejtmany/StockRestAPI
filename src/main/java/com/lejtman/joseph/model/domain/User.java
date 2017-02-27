package com.lejtman.joseph.model.domain;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private Instant createdTimestamp;
	
	@OneToMany
	private Set<Portfolio> portfolios;
	
	

	/**
	 * @param firstName
	 * @param lastName
	 */
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdTimestamp = Instant.now();
		this.portfolios = new HashSet<>();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Instant getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @return the portfolios
	 */
	public Set<Portfolio> getPortfolios() {
		return new HashSet<>(portfolios);
	}
	
	public boolean addPortfolio(Portfolio p){
		return portfolios.add(p);
	}
	
	public boolean removePortfolio(Portfolio p){
		return portfolios.remove(p);
	}
	
}
