package com.nesrux.catalogo.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class kafkaProperties {

   private String bootsTrapServers;
   private int poolTimeout;
   private boolean autoCreateTopics;


	public String bootsTrapServers() {
		return this.bootsTrapServers;
	}

	
	public int poolTimeout() {
		return this.poolTimeout;
	}

	public boolean autoCreateTopics() {
		return this.autoCreateTopics;
	}


	public kafkaProperties bootsTrapServers(final String bootsTrapServers) {
		this.bootsTrapServers = bootsTrapServers;
		return this;
	}

	public kafkaProperties poolTimeout(final int poolTimeout) {
		this.poolTimeout = poolTimeout;
		return this;
	}

	public kafkaProperties autoCreateTopics(final boolean autoCreateTopics) {
		this.autoCreateTopics = autoCreateTopics;
		return this;
	}
   
}
