package com.course.microservice.googlecloud.config;

import com.google.cloud.logging.LogEntry.Builder;
import com.google.cloud.logging.LoggingEnhancer;

public class GoogleLoggingEnhancer implements LoggingEnhancer {

	@Override
	public void enhanceLogEntry(Builder builder) {
		builder.addLabel("appName", "microservice-google-cloud");
	}

}
