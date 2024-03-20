package com.qestit.data;

public interface IJsonDataManager {

	// JSON-related methods(JsonUtils)
	void setJsonFile(String jsonPath);

//	String readJsonFile(String jsonPath); // return String or complex object based on implementation

//	Object getJsonData(String key); // Generalized to Object since JSON data can be various types
//
//	void initJsonContext(String jsonContent);
//
//	String getJsonDataSourceString();

	Object getData(String key);
	
//	String get(String key);

}
