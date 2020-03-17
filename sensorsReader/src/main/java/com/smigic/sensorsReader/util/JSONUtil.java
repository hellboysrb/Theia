package com.smigic.sensorsReader.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;

public class JSONUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> String convertToJSON(T obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
}
