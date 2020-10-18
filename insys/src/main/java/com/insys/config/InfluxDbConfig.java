package com.insys.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;

@Component
public class InfluxDbConfig {
	
	private final static String serverURL = "http://127.0.0.1:8086";
	private final static String username = "root";
	private final static String password = "root";
	
	private static InfluxDB influxDbConnect = null;
 
	private static String getDefaultDatabase(){
		return "testDb";
	}
	
	private static InfluxDB getInfluxDbConfigObj(String database){
		
		if(influxDbConnect == null){
			influxDbConnect = InfluxDBFactory.connect(serverURL, username, password);
			
			String db = (database == null || database.isEmpty()) ? getDefaultDatabase() : database;
			
			influxDbConnect.setDatabase(db);
		}
		
		return influxDbConnect;
	}
	
	
	public void write(String measurement, HashMap<String, String> tagValues, HashMap<String, Object> fieldValues){
		InfluxDB connect = getInfluxDbConfigObj(null);
		
		connect.write(Point.measurement(measurement)
				.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag(tagValues)
				.fields(fieldValues)
				.build());

	}
	
	
	
}
