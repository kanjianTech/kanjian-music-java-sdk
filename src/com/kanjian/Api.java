package com.kanjian;

import java.lang.reflect.Type;

import com.kanjian.Client;
import com.kanjian.Util;

import java.sql.Timestamp;

import java.util.Map;
import java.util.List;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Api {
    private String appKey;
    private String appSecret;
    private String serverHost;
    private Map<String, String> token;

    public Api(String appKey, String appSecret, String serverHost) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.serverHost = serverHost;
    }

    public String token() throws Exception {
        String url = this.serverHost + "/v1/token";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long now = timestamp.getTime();

        if (this.token == null) {
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("app_key", this.appKey);
            params.put("timestamp", "" + now);

            String sig = Util.GetSig(this.appSecret, params);
            params.put("sig", sig);

            String tmpToken = Client.Get(url, params);
            Gson gson = new Gson();
            Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
            this.token = gson.fromJson(tmpToken, stringStringMap);

            this.token.put("expires_at", "" + (now + Long.valueOf(this.token.get("expires_in")) * 1000));
        }

        if (this.token.containsKey("expires_at") && Long.parseLong(this.token.get("expires_at")) > now + 10 * 1000) { // magic no
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("app_key", this.appKey);
            params.put("timestamp", "" + now);

            String sig = Util.GetSig(this.appSecret, params);
            params.put("sig", sig);

            String tmpToken = Client.Get(url, params);
            Gson gson = new Gson();
            Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
            this.token = gson.fromJson(tmpToken, stringStringMap);

            if(this.token.containsKey("expires_in")){
                this.token.put("expires_at", "" + (now + Long.parseLong(this.token.get("expires_in")) * 1000));
            }
        }

        return this.token.get("access_token");
    }

    public String genreList(String device_id, String page, String count) throws Exception {

        String url = this.serverHost + "/v1/genre";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);

    }

    public String albumListByGenre(String device_id, String page, String count, String genreId) throws Exception {

        String url = this.serverHost + "/v1/genre/" + genreId + "/album";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String trackListByGenre(String device_id, String page, String count, String genreId) throws Exception {

        String url = this.serverHost + "/v1/genre/" + genreId + "/track";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String artistList(String device_id, String page, String count) throws Exception {

        String url = this.serverHost + "/v1/artist";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);

    }

    public String albumListByArtist(String device_id, String page, String count, String artistId) throws Exception {

        String url = this.serverHost + "/v1/artist/" + artistId + "/album";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String trackListByArtist(String device_id, String page, String count, String artistId) throws Exception {

        String url = this.serverHost + "/v1/artist/" + artistId + "/track";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String albumDetail(String device_id, String albumId) throws Exception {

        String url = this.serverHost + "/v1/album/" + albumId;
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String trackListByAlbum(String device_id, String page, String count, String albumId) throws Exception {

        String url = this.serverHost + "/v1/album/" + albumId + "/track";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String trackDetail(String device_id, String trackId) throws Exception {

        String url = this.serverHost + "/v1/track/" + trackId;
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String searchArtist(String device_id, String page, String count, String keyword) throws Exception {

        String url = this.serverHost + "/v1/search/artist";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);
        params.put("keyword", keyword);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String searchAlbum(String device_id, String page, String count, String keyword) throws Exception {

        String url = this.serverHost + "/v1/search/album";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);
        params.put("keyword", keyword);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

    public String searchTrack(String device_id, String page, String count, String keyword) throws Exception {

        String url = this.serverHost + "/v1/search/track";
        String token = this.token();

        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("app_key", this.appKey);
        params.put("access_token", token);
        params.put("device_id", device_id);
        params.put("page", page);
        params.put("count", count);
        params.put("keyword", keyword);

        String sig = Util.GetSig(this.appSecret, params);
        params.put("sig", sig);

        return Client.Get(url, params);
    }

}
