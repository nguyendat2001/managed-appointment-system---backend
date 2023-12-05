package com.bezkoder.spring.security.jwt.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(	name = "prescriptionDetail")
public class PrescriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
	private Integer numberOfMedicine;
	
	@ManyToOne
    @JoinColumn(name="presciption_id", nullable=false)
	private Prescription prescription;
	
	@ManyToOne
    @JoinColumn(name="medicine_id", nullable=false)
	private Medicine medicine;
	
	
	
	public PrescriptionDetails() {
		super();
	}

	public PrescriptionDetails(Integer number) {
		super();
		this.numberOfMedicine = number;
	}

	public Integer getNumber() {
		return numberOfMedicine;
	}

	public void setNumber(Integer number) {
		this.numberOfMedicine = number;
	}

	public Prescription getPresciption() {
		return prescription;
	}

	public void setPresciption(Prescription presciption) {
		this.prescription = presciption;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	
	
}
