package com.bezkoder.spring.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MedicineRequest {
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
