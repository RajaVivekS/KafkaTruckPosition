package com.trucktracker.Consumer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDeserializer implements Deserializer<Truck> {

	@Override
	public Truck deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		Truck truck = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			truck = objectMapper.readValue(data,Truck.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return truck;
	}

}
