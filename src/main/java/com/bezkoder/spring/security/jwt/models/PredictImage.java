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
@Table(	name = "predictimage")
public class PredictImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
	@Size(max = 500)
	private String predictPathImage;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;

	public PredictImage(@Size(max = 500) String predictPathImage) {
		super();
		this.predictPathImage = predictPathImage;
	}

	public PredictImage() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPredictPathImage() {
		return predictPathImage;
	}

	public void setPredictPathImage(String predictPathImage) {
		this.predictPathImage = predictPathImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
