package com.smigic.logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class LoggerApplication {

	private static String logFilesLocation = "logs\\";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LoggerApplication.class, args);

		do {
			try (Socket s = new Socket("localhost", 1987); InputStreamReader in = new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8)) {
				BufferedReader bf = new BufferedReader(in);
				String line = bf.readLine();
				System.out.println(line);
				JsonFactory factory = new JsonFactory();

				ObjectMapper mapper = new ObjectMapper(factory);
				JsonNode rootNode = mapper.readTree(line);

				Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
				String tmpTimeStamp = "";
				while (fieldsIterator.hasNext()) {
					Map.Entry<String,JsonNode> field = fieldsIterator.next();
					if(field.getKey().toLowerCase() == "timestamp") tmpTimeStamp = field.getValue().toString().replace("\"","").replace("T","_").substring(0,13) + "h";
				}
				String fileName = logFilesLocation + tmpTimeStamp + "h_json.log";
				FileWriter myWriter = new FileWriter( fileName, true);
				myWriter.append(line + System.lineSeparator());
				myWriter.close();


			} catch (IOException e) {
				// Write in error log file
				//e.printStackTrace();
			}
		} while (true);

	}
}
