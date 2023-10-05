package com.course.microservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cqrs_outbox")
public class CqrsOutbox {

	@Column(nullable = false, length = 255, name = "aggregateid")
	private String aggregateId;

	@Column(nullable = false, length = 255, name = "aggregatetype")
	private String aggregateType;

	// need to manually set this to millis (3 precision), as hibernate automatically
	// set precision to 6 (microseconds)
	// since this will be used later in listener, it's easier to convert from millis
	// instead micros
	@CreationTimestamp
	private LocalDateTime createdTimestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cqrs_outbox_seq")
	@SequenceGenerator(name = "cqrs_outbox_seq")
	private long id;

	/**
	 * JSON structure with the actual event contents
	 */
	@Column(nullable = false, length = 4000)
	private String payload;

	@Column(nullable = false, length = 255)
	private String type;

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
