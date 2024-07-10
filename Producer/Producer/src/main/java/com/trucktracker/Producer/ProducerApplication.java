package com.trucktracker.Producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		String Latitude="22 N";
		String Longitude="34 E";
		String lat_long=Latitude+","+Longitude;
		int id=4;
		KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
		ProducerRecord<Integer, String> record = new ProducerRecord<>("OrderTopic", id,lat_long);
		try {
			producer.send(record);
			System.out.println("Message sent");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			producer.close();
		}
		
	}

}
