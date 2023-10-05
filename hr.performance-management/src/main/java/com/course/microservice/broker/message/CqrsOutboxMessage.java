package com.course.microservice.broker.message;

public class CqrsOutboxMessage {
	public class CqrsPayload {
		private String eventType;
		// json string, with dynamic json structure depends on changed data
		private String payload;
		private long id;

		public String getEventType() {
			return eventType;
		}

		public void setEventType(String eventType) {
			this.eventType = eventType;
		}

		public String getPayload() {
			return payload;
		}

		public void setPayload(String payload) {
			this.payload = payload;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}

	private CqrsPayload payload;

	public CqrsPayload getPayload() {
		return payload;
	}

	public void setPayload(CqrsPayload payload) {
		this.payload = payload;
	}
}
