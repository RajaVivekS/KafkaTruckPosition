package com.trucktracker.Consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("group.id", "OrderGroup");
		KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("OrderTopic"));
		ConsumerRecords<Integer, String> record = consumer.poll(Duration.ofSeconds(180));
		for (ConsumerRecord<Integer, String> consumerRecord : record) {
			System.out.println("Truck ID: "+consumerRecord.key());
			System.out.println("Position:"+consumerRecord.value());			
		}
		consumer.close();
	}

}
