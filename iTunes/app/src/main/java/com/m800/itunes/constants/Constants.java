package com.m800.itunes.constants;

import com.m800.itunes.BuildConfig;

public class Constants {
    //測試外網
    private static final String SERVER_URL_DEV = "https://itunes.apple.com/search";

    //正式外網
    private static final String SERVER_URL = "https://itunes.apple.com/search";

    public static String getServerUrl() {
        return BuildConfig.DEBUG ? SERVER_URL_DEV : SERVER_URL;
    }


}
