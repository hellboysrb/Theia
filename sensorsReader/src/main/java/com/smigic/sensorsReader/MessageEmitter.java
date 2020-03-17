package com.smigic.sensorsReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smigic.sensorsReader.stream.StreamClient;
import com.smigic.sensorsReader.dto.SensorMessage;
import com.smigic.sensorsReader.dto.SensorMessages;
import com.smigic.sensorsReader.util.JSONUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.UUID;

public class MessageEmitter implements Runnable {
	private ByteArrayOutputStream buffer;
	private byte[] data;
	private int numberBytes;
	private String sensorName = "";
	private final InputStream in;
	private final StreamClient<String> streamClient;
	private Socket sck;

	public MessageEmitter(int bufferSize, InputStream in, StreamClient<String> streamClient, String sensorName, Socket sck) {
		this.buffer = new ByteArrayOutputStream();
		this.data = new byte[bufferSize];
		this.in = in;
		this.streamClient = streamClient;
		this.sensorName = sensorName;
		this.sck = sck;
	}

	@Override
	public void run() {
		try {
			numberBytes = in.read(data, 0, data.length);
			buffer.write(data, 0, numberBytes);
			buffer.flush();
			ByteBuffer bb = ByteBuffer.wrap(buffer.toByteArray());
			SensorMessage msg = SensorMessages.toSensorMessage(bb);
			// Random GUID for Sensor name if is null or empty
			UUID uuid = UUID.randomUUID();
			String randomUUIDString = uuid.toString();
			String randomSensorName = randomUUIDString;
			// Random GUID if we don't receive agrument and name
			if ((msg.getName() == null || msg.getName().isEmpty()) && (this.sensorName == ""))
				msg.setName(randomSensorName);
			// Received agrument as name
			else if (this.sensorName != "")
				msg.setName(this.sensorName);
			String message = JSONUtil.convertToJSON(msg);
			PrintWriter pr = new PrintWriter(sck.getOutputStream());
			pr.println(message);
			pr.flush();
			System.out.println(message);
			streamClient.publishMsg(message);
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
			//throw new RuntimeException(ex.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
			//throw new RuntimeException(ex.getMessage());
		}
	}
}