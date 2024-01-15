package com.course.microservice.googlecloud.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dummy {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(length = 50)
	private String fieldOne;

	private long fieldTwo;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFieldOne() {
		return fieldOne;
	}

	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}

	public long getFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(long fieldTwo) {
		this.fieldTwo = fieldTwo;
	}

}
