package com.bezkoder.spring.security.jwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "diagnosesimage")
public class DiagnosesImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
//	@Size(max = 100)
	private String path;
	
	@ManyToOne
    @JoinColumn(name="diagnose_id", nullable=false)
	private Diagnoses diagnoses;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Diagnoses getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(Diagnoses diagnoses) {
		this.diagnoses = diagnoses;
	}

	public DiagnosesImage() {
		super();
	}

	public DiagnosesImage(@NotBlank @Size(max = 100) String path) {
		super();
		this.path = path;
	}
	
	
}
