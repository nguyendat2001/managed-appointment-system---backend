package com.bezkoder.spring.security.jwt.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "medicines", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name"),
		})
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String name;
	
	@NotBlank
	@Size(max = 500)
	private String description;
	
	@NotBlank
	@Size(max = 500)
	private String ingredient;
	
	@NotBlank
	@Size(max = 500)
	private String uses;
	
	private Boolean isActive;
	
	@OneToMany(mappedBy="medicine",fetch = FetchType.LAZY)
    private List<PrescriptionDetails> prescriptionDetail;

	public Medicine() {
		super();
	}

	public Medicine(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 500) String description,
			@NotBlank @Size(max = 500) String ingredient, @NotBlank @Size(max = 500) String uses) {
		super();
		this.name = name;
		this.description = description;
		this.ingredient = ingredient;
		this.uses = uses;
		this.isActive = true;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}
	
	
}
