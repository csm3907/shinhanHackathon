package com.fund.util;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	/**
     * Map을 json으로 변환
     *
     * @param map Map<String, Object>.
     * @return JSONObject.
     */
    public static JSONObject getJsonStringFromMap( Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject;
    }
    
    /**
     * List<Map>을 jsonArray로 변환
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.put( getJsonStringFromMap(map));
        }
        
        return jsonArray;
    }
    
    /**
     * List<Object>을 jsonArray로 변환
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     * @throws JsonProcessingException 
     */
    public static JSONArray getJsonArrayFromListObject(List<Object> list ) throws JsonProcessingException
    {
        JSONArray jsonArray = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();
        for( Object object : list ) {
        	String jsonStr = mapper.writeValueAsString(object);
            jsonArray.put( new JSONObject(jsonStr));
        }
        
        return jsonArray;
    }
    
    public static JSONObject toJson(String str) throws ParseException {
    	return new JSONObject(str);
    }

}
