package com.bezkoder.spring.security.jwt.models;

import java.time.YearMonth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "amountusedmodel")
public class AmountUsedModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer amount;
	
	private YearMonth createdAt;
	
	@ManyToOne
    @JoinColumn(name="model_id", nullable=false)
	private Model model;

	public AmountUsedModel() {
		super();
		this.amount = 1;
		this.createdAt = YearMonth.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public YearMonth getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(YearMonth createdAt) {
		this.createdAt = createdAt;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	
}
