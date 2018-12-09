package com.m800.itunes.module.apiParams;

import com.android.volley.Request;

import java.util.Map;

public class InParamsTopSongs extends InParamsBase {

    public InParamsTopSongs(String keyword) {
        super("?term=" + keyword + "&country=tw&limit=100&media=music&entity=song", null);
        setMethod(Request.Method.GET);

    }

    @Override
    public Map<String, String> renderMap() throws Exception {

        return null;
    }
}
