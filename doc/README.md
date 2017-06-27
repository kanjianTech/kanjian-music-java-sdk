# java sdk 使用说明

## 示例代码


```java
import com.kanjian.Api;

Api api = new Api("appKey", "appSecret", "http://open-api.kanjian.com/");

// 获取基因列表,从0开始往后的20条数据
api.genreList("appSecret", "0", "20");

// 通过基因id为1获取专辑列表,从0开始往后的20条数据
api.albumListByGenre("appSecret", "0", "20", "1");

// 通过基因id为1获取单曲列表,从0开始往后的20条数据
api.trackListByGenre("appSecret", "0", "20", "1");

// 获取艺人列表,从0开始往后的20条数据
api.artistList("appSecret", "0", "20");

// 通过艺人id为1获取专辑列表,从0开始往后的20条数据
api.albumListByArtist("appSecret", "0", "20", "1");

// 通过艺人id为1获取单曲列表,从0开始往后的20条数据
api.trackListByArtist("appSecret", "0", "20", "1");

// 通过id为1获取专辑明细
api.albumDetail("appSecret", "1");

// 通过专辑id为1获取单曲列表,从0开始往后的20条数据
api.trackListByAlbum("appSecret", "0", "20", "1");

// 通过id为1获取单曲明细
api.trackDetail("appSecret", "1");

// 通过关键字 love 搜索艺人,从0开始往后的20条数据
api.searchArtist("appSecret", "0", "20", "love");

// 通过关键字 love 搜索专辑,从0开始往后的20条数据
api.searchAlbum("appSecret", "0", "20", "love");

// 通过关键字 love 搜索单曲,从0开始往后的20条数据
api.searchTrack("appSecret", "0", "20", "love");
```

# 关键代码解读
* Util.GetSig
```java
public static String GetSig(String appSecret, TreeMap<String, String> params) throws Exception {
	// 将除了 sig 以外的所有请求参数的原始值按照参数名的字典序排序
	// tips params为TreeMap,已经默认排好序
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
```