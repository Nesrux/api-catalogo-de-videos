package com.nesrux.catalogo.infrastructure.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.nesrux.catalogo.infrastructure.configuration.properties.kafkaProperties;
@Configuration
@EnableKafkaRetryTopic
@Profile({"production", "sandbox", "test-e2e"})
public class KafkaConfig {
	private final kafkaProperties properties;


	public KafkaConfig(final kafkaProperties properties) {
		this.properties = properties;
	}
	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> KafkaListenerFactory(){
		final var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setPollTimeout(properties.poolTimeout());


		return factory;
	}

	private ConsumerFactory<? super String, ? super String > consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}

	private Map<String, Object> consumerConfig() {
		final var props = new HashMap<String, Object>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.bootsTrapServers());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, properties.autoCreateTopics());
		
		return props;
	}


}
