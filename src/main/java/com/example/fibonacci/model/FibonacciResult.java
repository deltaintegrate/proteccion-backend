package com.example.fibonacci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FibonacciResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String series;
	private String seed;
	
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	
}