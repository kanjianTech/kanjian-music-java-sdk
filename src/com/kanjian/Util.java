package com.kanjian;

import java.util.Base64;
import java.util.TreeMap;
import static java.util.stream.Collectors.joining;

import javax.crypto.Mac;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;  

import java.security.MessageDigest;

import java.math.BigInteger;


class Util {

	public static String GetSig(String appSecret, TreeMap<String, String> params) throws Exception {
		// 将除了 sig 以外的所有请求参数的原始值按照参数名的字典序排序
		// 将排序后的参数键值对用&拼接，即拼成 key1=val1&key2=val2&...
		String tmpStr = params.entrySet()
		                      .stream()
		                      .map(e -> e.getKey()+"="+e.getValue())
		                      .collect(joining("&"));

		// 将第二步骤得到的字符串进行 Base64 编码
		byte[] b64Str = Base64.getEncoder().encode(tmpStr.getBytes());

		// 将 app_secret 作为哈希 key 对第三步骤得到的 Base64 编码后的字符串进行 HMAC-SHA1 哈希运算得到字节数组
		byte[] secret=appSecret.getBytes("UTF-8");
		SecretKey secretKey = new SecretKeySpec(secret, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKey);
		byte[] sha1Str = mac.doFinal(b64Str);

		// 对第四步骤得到的字节数组进行 MD5 运算得到 32 位字符串，即为 sig
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(sha1Str);

		String sig = new BigInteger(1, md.digest()).toString(16);

		return sig;
	}

}