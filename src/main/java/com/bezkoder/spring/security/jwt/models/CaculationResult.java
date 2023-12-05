package com.bezkoder.spring.security.jwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "caculation_result")
public class CaculationResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float valueOnTrain;
	
	private Float valueOnTest;
	
	@ManyToOne
    @JoinColumn(name="calculation_id", nullable=false)
	private Caculation caculation;
	
	@ManyToOne
    @JoinColumn(name="model_id", nullable=false)
	private Model model;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValueOnTrain() {
		return valueOnTrain;
	}

	public void setValueOnTrain(Float valueOnTrain) {
		this.valueOnTrain = valueOnTrain;
	}

	public Float getValueOnTest() {
		return valueOnTest;
	}

	public void setValueOnTest(Float valueOnTest) {
		this.valueOnTest = valueOnTest;
	}

	public Caculation getCaculation() {
		return caculation;
	}

	public void setCaculation(Caculation caculation) {
		this.caculation = caculation;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public CaculationResult(Float valueOnTrain, Float valueOnTest) {
		super();
		this.valueOnTrain = valueOnTrain;
		this.valueOnTest = valueOnTest;
	}

	public CaculationResult() {
		super();
	}
	
	
}
