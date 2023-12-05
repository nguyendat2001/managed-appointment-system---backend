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
@Table(	name = "caculations", 
	uniqueConstraints = { 
		@UniqueConstraint(columnNames = "nameCalCulation")
	})
public class Caculation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String nameCalCulation;
	
	@NotBlank
//	@Size(max = 500)
	private String describeCalCulation;
	
	@OneToMany(mappedBy="caculation",fetch = FetchType.LAZY)
    private List<CaculationResult> caculationResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nameCalCulation;
	}

	public void setName(String name) {
		this.nameCalCulation = name;
	}

	public String getDescribe() {
		return describeCalCulation;
	}

	public void setDescribe(String describe) {
		this.describeCalCulation = describe;
	}

	public Caculation(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 100) String describe) {
		super();
		this.nameCalCulation = name;
		this.describeCalCulation = describe;
	}

	public Caculation() {
		super();
	}
	
}
