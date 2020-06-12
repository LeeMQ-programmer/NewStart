package com.start.pro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class Util_JSON {

	private String str;
	private String key;
	
	public Util_JSON() {}

	public void setStr(String str) {
		this.str = str;
	}
	public String getStr() {
		List<String> list = jsonToList(str, key);
		String lists = null;
		if(list.size()>1) {
			lists = list.get(0) + "외 " +(list.size()-1);
		}else {
			lists = list.get(0);
		}
		System.out.println(lists);
		return lists;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

	



	// str -> jsonArray -> List<String>
	public List<String> jsonToList(String str, String key){

		JSONParser parser = new JSONParser();
		List<String> list = new ArrayList<String>();
		Object obj = null;
		try {
			obj = parser.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONArray jArr = (JSONArray) obj;
		for (int i = 0; i < jArr.size(); i++) {
			JSONObject jObj = (JSONObject) jArr.get(i);
			list.add((String) jObj.get(key));
			System.out.println("몇번째??"+list.get(i));
		}
		
		return list;
	}
	
	//List<String> -> jsonArray -> String
	public String listToJson(List<String> list,String key) {
		JSONArray jarr = new JSONArray(); 
		for (String str : list) {
			JSONObject obj = new JSONObject();
			obj.put(key, str);
			jarr.add(obj);
		}
		System.out.println(jarr.toJSONString());
		return jarr.toJSONString();
	}
	
}
