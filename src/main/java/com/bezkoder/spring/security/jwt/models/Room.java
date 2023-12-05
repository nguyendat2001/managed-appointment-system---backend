package com.bezkoder.spring.security.jwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "rooms", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "name") 
		})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;
	
	private Integer capacity;
	
	private Boolean isPresidentRoom;
	
	private boolean  isActive;
	
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	public Boolean getIsPresidentRoom() {
		return isPresidentRoom;
	}

	public void setIsPresidentRoom(Boolean isPresidentRoom) {
		this.isPresidentRoom = isPresidentRoom;
	}

	public Room(@NotBlank @Size(max = 20) String name, @NotBlank Integer capacity, @NotBlank Boolean isPresidentRoom) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.isPresidentRoom = isPresidentRoom;
		this.isActive = true;
	}


	public Room() {
		super();
	}

}
