package com.insys.service;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.influxdb.InfluxDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.insys.config.InfluxDbConfig;

@Configuration
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	InfluxDbConfig influxDbConfig;
	
	 @KafkaListener(topics = "${my.kafka.consumer.topic}")
	  public void listen(ConsumerRecord<String, String> kafkaMessage) {
		 logger.warn(String.format("Received data = %s", kafkaMessage.value()));
		 
		 String[] msgSplit = kafkaMessage.value().split(";");
		 
		 HashMap<String, String> tagValues = new HashMap<String, String>();
		 HashMap<String, Object> fieldValues = new HashMap<String, Object>();
		 
		 String key, value;
		 for(String tag : msgSplit[0].split(",")){
			 key = tag.split(":")[0];
			 value = tag.split(":")[1];
			 tagValues.put(key, value);
		 }
		 
		 Float val;
		 for(String field : msgSplit[1].split(",")){
			 key = field.split(":")[0];
			 val = Float.parseFloat(field.split(":")[1]);
			 fieldValues.put(key, val);
		 }
		 
		 influxDbConfig.write("temp", tagValues, fieldValues);
		 
	  }
}
