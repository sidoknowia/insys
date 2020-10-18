package com.insys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import org.springframework.stereotype.Service;

@Service
public class Producer {

	@Value("${my.kafka.producer.topic}")
	private String topic;
	
	Logger log = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, message);

	    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	      @Override
	      public void onSuccess(SendResult<String, String> result) {
	        log.info(String.format("Sent data     = %s", result.getProducerRecord().value()));
	      }

	      @Override
	      public void onFailure(Throwable ex) {
	        log.error("Unable to send data to Kafka", ex);
	      }
	    });
		//this.kafkaTemplate.send(TOPIC, message);
	}
}
