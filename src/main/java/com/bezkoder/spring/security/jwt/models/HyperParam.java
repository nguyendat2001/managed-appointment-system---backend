package com.bezkoder.spring.security.jwt.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "hyperparams")
public class HyperParam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String name;
	
	@NotBlank
	private String note;

	@OneToMany(mappedBy="hyperParam",fetch = FetchType.LAZY)
    private List<HyperParamResult> hyperParamResults;
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public HyperParam(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 100) String note) {
		super();
		this.name = name;
		this.note = note;
	}

	public HyperParam() {
		super();
	}
	
	
}
