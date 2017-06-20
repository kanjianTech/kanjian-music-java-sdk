package com.kanjian;

import com.kanjian.Api;


public class Test {
    public static void main(String[] args) throws Exception {
        Api api = new Api("", "", "");

        System.out.println("test genreList");
        System.out.println(api.genreList("1212", "0", "20"));
        System.out.println("test genreList");

        System.out.println("test albumListByGenre");
        System.out.println(api.albumListByGenre("1212", "0", "20", "1"));
        System.out.println("test albumListByGenre");

        System.out.println("test trackListByGenre");
        System.out.println(api.trackListByGenre("1212", "0", "20", "1"));
        System.out.println("test trackListByGenre");

        System.out.println("test artistList");
        System.out.println(api.artistList("1212", "0", "20"));
        System.out.println("test artistList");

        System.out.println("test albumListByArtist");
        System.out.println(api.albumListByArtist("1212", "0", "20", "1"));
        System.out.println("test albumListByArtist");

        System.out.println("test trackListByArtist");
        System.out.println(api.trackListByArtist("1212", "0", "20", "1"));
        System.out.println("test trackListByArtist");

        System.out.println("test albumDetail");
        System.out.println(api.albumDetail("1212", "1"));
        System.out.println("test albumDetail");

        System.out.println("test trackListByAlbum");
        System.out.println(api.trackListByAlbum("1212", "0", "20", "1"));
        System.out.println("test trackListByAlbum");

        System.out.println("test trackDetail");
        System.out.println(api.trackDetail("1212", "1"));
        System.out.println("test trackDetail");

        System.out.println("test searchArtist");
        System.out.println(api.searchArtist("1212", "0", "20", "love"));
        System.out.println("test searchArtist");

        System.out.println("test searchAlbum");
        System.out.println(api.searchAlbum("1212", "0", "20", "love"));
        System.out.println("test searchAlbum");

        System.out.println("test searchTrack");
        System.out.println(api.searchTrack("1212", "0", "20", "love"));
        System.out.println("test searchTrack");

    }
}
