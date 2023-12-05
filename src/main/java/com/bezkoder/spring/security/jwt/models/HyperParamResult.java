package com.bezkoder.spring.security.jwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "hyperparams_result")
public class HyperParamResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float value;
	
	@ManyToOne
    @JoinColumn(name="hyperParam_id", nullable=false)
	private HyperParam hyperParam;
	
	@ManyToOne
    @JoinColumn(name="model_id", nullable=false)
	private Model model;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public HyperParam getHyperParam() {
		return hyperParam;
	}

	public void setHyperParam(HyperParam hyperParam) {
		this.hyperParam = hyperParam;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public HyperParamResult(Float value) {
		super();
		this.value = value;
	}

	public HyperParamResult() {
		super();
	}
	
	
}
