package com.smigic.sensorsReader.dto;

import com.smigic.sensorsReader.util.BytesUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorMessages {

	public static SensorMessage toSensorMessage(ByteBuffer bb) {

		int plength = bb.getInt();
		long timestamp = bb.getLong();
		int nlen = bb.get();

		//Long to String for Timestamp
		Date date=new Date(timestamp);
		SimpleDateFormat df2 = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZ");
		String timestamp_ut8 = df2.format(date);


		float temperature = -999999;
		float humidity = -1;

		byte[] dst = BytesUtil.getNextBytes(bb, 13, nlen);
		String name = new String(dst, StandardCharsets.UTF_8);
		int remainingBytes = plength - bb.position();
		if (remainingBytes == 5) {
			humidity = bb.getShort();
			temperature = getTemperature(bb, nlen);
		} else if (remainingBytes == 2) {
			humidity = bb.getShort();
		} else if (remainingBytes == 3) {
			temperature = getTemperature(bb, nlen);
		}

		return SensorMessage.builder()
				.name(name)
				.timestamp(timestamp_ut8)
				.temperature(temperature)
				.humidity(humidity)
				.build();
	}

	private static float getTemperature(ByteBuffer bb, int nlen) {
		byte[] temperatureBytes = BytesUtil.getNextBytes(bb, 13 + nlen, 3);
		byte[] concatenatedArrays = BytesUtil.concatenateByteArrays(new byte[] { 0x00 }, temperatureBytes);
		return (float)BytesUtil.toLong(concatenatedArrays);
	}

}
