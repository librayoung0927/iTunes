package com.m800.itunes.interfaces;

public interface OnAPIListener {
    void onPreExecute();

    void onResponse(String response);

    void onError(int statusCode, String error);
}