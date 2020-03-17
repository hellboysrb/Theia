package com.smigic.sensorsReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

@SpringBootApplication
public class SensorsReaderApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SensorsReaderApplication.class, args);

		ArrayList<String> sensorList = new ArrayList<String>();
		sensorList.add("C:\\resources\\sensor_01.exe --Sensor1");
		sensorList.add("C:\\resources\\sensor_02.exe");
		sensorList.add("C:\\resources\\sensor_03.exe --Sensor3");

		ServerSocket ss = new ServerSocket(1987);

		for(String sensor: sensorList) {
			new Sensor(sensor, ss);
		}

	}
}
