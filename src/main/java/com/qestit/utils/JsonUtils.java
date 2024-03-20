/*
 * Copyright (c) 2023 QESTIT
 * QesteniumX
 */

package com.qestit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.qestit.constants.FrameworkConstants;
import com.qestit.helpers.Helpers;

/**
 * To construct the map by the reading the config values from JSON. Not used in this framework but can be leveraged
 * instead of property file based on the requirements
 */

/**
 * Usage Scenarios: Use readJsonFile for operations requiring the full JSON
 * content. Use setJsonFile to prepare for querying JSON data from a specific
 * file. Use getJsonDataSourceString as a utility method to ensure JSON data is
 * always available for querying, especially in cases where the JSON file to be
 * used is the default one.
 */
public class JsonUtils {

	// Jackson
	private static Map<String, String> CONFIGMAP;

	// Json Path
	private static BufferedReader bufferedReader;
	private static StringBuffer stringBuffer;
	private static DocumentContext jsonContext;
	private static String lines;
	private static String jsonFilePathDefault = Helpers.getCurrentDir() + "src/test/resources/datajson/store.json";

	private JsonUtils() {
		super();
	}

	// Used to initialize static data members. (CONFIGMAP)
	// It is executed before the main method at the time of class instantiation.
	static {
		try {
			CONFIGMAP = new ObjectMapper().readValue(
					new File(Helpers.getCurrentDir() + FrameworkConstants.JSON_DATA_FILE_PATH),
					new TypeReference<HashMap<String, String>>() {
					});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toLowerCase()))) {
			try {
				throw new Exception("Key name " + key + " is not found. Please check config.json");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return CONFIGMAP.get(key.toLowerCase());
	}

	public static StringBuffer readJsonFile(String jsonPath) {
		try {
			bufferedReader = new BufferedReader(new FileReader(Helpers.getCurrentDir() + jsonPath));
			stringBuffer = new StringBuffer();
			while ((lines = bufferedReader.readLine()) != null) {
				stringBuffer.append(lines);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

	public static void setJsonFile(String jsonPath) {
		try {
			bufferedReader = new BufferedReader(new FileReader(Helpers.getCurrentDir() + jsonPath));
			stringBuffer = new StringBuffer();
			while ((lines = bufferedReader.readLine()) != null) {
				stringBuffer.append(lines);
			}
			jsonContext = JsonPath.parse(stringBuffer.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getJsonDataSourceString() {
		if (stringBuffer == null) {
			try {
				bufferedReader = new BufferedReader(new FileReader(jsonFilePathDefault));
				stringBuffer = new StringBuffer();
				while ((lines = bufferedReader.readLine()) != null) {
					stringBuffer.append(lines);
				}
				jsonContext = JsonPath.parse(stringBuffer.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}

	// The primary goal of this method is to retrieve specific data from the JSON
	// structure
	// that has been loaded into jsonContext
	public static Object getData(String key) {
		if (jsonContext == null) {
			getJsonDataSourceString();
		}
		// JsonPath.read(getJsonDataSourceString(), key);
		return jsonContext.read(key);
	}

}
