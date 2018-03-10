package com.fabrikam.testAzureApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import net.sf.json.JSONObject;

public class JsonConverter {
		public Map<String, String> call_json_object(String str){
		    Map<String, String> map = new HashMap<String, String>();
		    try {
		      map= addKeys("", new ObjectMapper().readTree(str), map);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	//	    System.out.println(map);
			return map;
		  }

		  private Map<String, String> addKeys(String currentPath, JsonNode jsonNode, Map<String, String> map) {
		    if (jsonNode.isObject()) {
		      ObjectNode objectNode = (ObjectNode) jsonNode;
		      Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
		      String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

		      while (iter.hasNext()) {
		        Map.Entry<String, JsonNode> entry = iter.next();
		        addKeys(pathPrefix + entry.getKey(), entry.getValue(), map);
		      }
		    } else if (jsonNode.isArray()) {
		      ArrayNode arrayNode = (ArrayNode) jsonNode;
		      for (int i = 0; i < arrayNode.size(); i++) {
		        addKeys(currentPath + "[" + i + "]", arrayNode.get(i), map);
		      }
		    } else if (jsonNode.isValueNode()) {
		      ValueNode valueNode = (ValueNode) jsonNode;
		      map.put(currentPath, valueNode.asText());
		    }
		    
//		//    System.out.println(map);
//		    
//		    for(Map.Entry<String, String> entry : map.entrySet()){
//		    	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//		    }
		    	return map;
		  }
		
		
	}
