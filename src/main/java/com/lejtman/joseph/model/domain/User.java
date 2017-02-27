package com.lejtman.joseph.model.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private final ZonedDateTime createdTimestamp = ZonedDateTime.now();
	
	@OneToMany
	private Set<Portfolio> portfolios = new HashSet<>();
	
	private User(){}

	/**
	 * @param firstName
	 * @param lastName
	 */
	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public ZonedDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @return the portfolios
	 */
	@JsonIgnore
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
