package com.baidu.www.shareinprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;

import java.util.Locale;

/**
 * Created by DAV on 15/6/25.
 * Contentresovler处理类
 */
public class StringResolver {

    private static final int MATCH_RIGHT_NUM = 1;

    private static final String CONTENT_TYPE = "prefs_detail";

    private static final String CONTENT_KEY = "prefs_key";

    private static final String CONTENT_VALUE = "prefs_value";

    private static final String sAuthority = "com.baidu.www.shareinprovider.url";

    private static final String TAG = "StringResolver";

    public static void putString(Context cxt, String key, String str){
        UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(sAuthority, CONTENT_TYPE, MATCH_RIGHT_NUM);
        Uri sUri = Uri.parse("content://"+ sAuthority + "/" + CONTENT_VALUE);

        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTENT_KEY, key);
        contentValues.put(CONTENT_VALUE, str);
        cxt.getContentResolver().update(sUri, contentValues, null, null);
    }

    public static String getString(Context cxt, String key, String defStr){
        UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(sAuthority, CONTENT_TYPE, MATCH_RIGHT_NUM);
        Uri sUri = Uri.parse("content://"+ sAuthority + "/" + CONTENT_VALUE);

        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTENT_KEY, key);
        contentValues.put(CONTENT_VALUE, defStr);
        Uri uri = cxt.getContentResolver().insert(sUri, contentValues);
        Log.v(TAG, uri.toString());
        return uri.toString();
    }

}
