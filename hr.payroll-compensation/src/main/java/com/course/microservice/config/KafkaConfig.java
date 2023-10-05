package com.course.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	private ConsumerFactory<Object, Object> consumerFactory() {
		var properties = kafkaProperties.buildConsumerProperties();
		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(value = "deadLetterContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
			KafkaTemplate<Object, Object> kafkaTemplate) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);

		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(AckMode.RECORD);
		factory.setReplyTemplate(kafkaTemplate);
		factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(3000, 4)));
		return factory;
	}

}
