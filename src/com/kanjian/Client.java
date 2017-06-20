package com.kanjian;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.joining;

import java.io.*;
import java.net.*;

public class Client {
    public static String Get(String url, Map params) throws Exception {

    	TreeMap<String, String> tmpParams = (TreeMap<String, String>) params;
		String tmpStr = tmpParams.entrySet()
		                         .stream()
		                         .map(e -> e.getKey()+"="+e.getValue())
		                         .collect(joining("&"));
		String tmpUrlStr = url + "?" + tmpStr;

		StringBuilder result = new StringBuilder();
		URL tmpUrl = new URL(tmpUrlStr);

		HttpURLConnection conn = (HttpURLConnection) tmpUrl.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		return result.toString();
    }
}
