package com.smigic.sensorsReader.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smigic.sensorsReader.converter.humidityConverter;
import com.smigic.sensorsReader.converter.temperatureConverter;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class SensorMessage {

	private String name;

	private String timestamp;

	@JsonSerialize( converter = temperatureConverter.class)
	@JsonIgnoreProperties()
	private float temperature;

	@JsonSerialize( converter = humidityConverter.class)
	private float humidity;

}
