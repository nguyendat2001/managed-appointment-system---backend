package com.bezkoder.spring.security.jwt.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "models")
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String name;

	private String path;
	
	private String labels;

	private Integer inputSize;
	
	private Boolean isSegmentation;
	
	@ManyToOne
    @JoinColumn(name="dataset_id", nullable=false)
	private Dataset dataset;
	
	private Boolean isActive;

	@OneToMany(mappedBy="model",fetch = FetchType.LAZY)
	private List<AmountUsedModel> amountUsedModel;
	
	@OneToMany(mappedBy="model",fetch = FetchType.LAZY)
    private List<HyperParamResult> hyperParamResults;
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getInputSize() {
		return inputSize;
	}

	public void setInputSize(Integer inputSize) {
		this.inputSize = inputSize;
	}

	

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Boolean getIsSegmentation() {
		return isSegmentation;
	}

	public void setIsSegmentation(Boolean isSegmentation) {
		this.isSegmentation = isSegmentation;
	}

	public Model(@NotBlank @Size(max = 50) String name, String path, String labels, Integer inputSize,
			Boolean isSegmentation) {
		super();
		this.name = name;
		this.path = path;
		this.labels = labels;
		this.inputSize = inputSize;
		this.isSegmentation = isSegmentation;
		this.isActive = true;
	}

	public Model() {
		super();
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

}
