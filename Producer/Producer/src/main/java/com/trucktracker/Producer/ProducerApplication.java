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
		props.setProperty("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
		props.setProperty("value.serializer", CustomSerializer.class.getName());
		Truck truck = new Truck();
		truck.setId(14);
		truck.setLatitude("24 N");
		truck.setLongitude("28 E");
		KafkaProducer<Integer, Truck> producer = new KafkaProducer<>(props);
		ProducerRecord<Integer, Truck> record = new ProducerRecord<>("OrderCustomTopic", truck.getId(),truck);
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
