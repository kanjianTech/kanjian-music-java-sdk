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
