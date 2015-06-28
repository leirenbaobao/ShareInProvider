package com.baidu.www.shareinprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by DAV on 15/6/25.
 */
public class StringProvider extends ContentProvider{

    private static final int MATCH_RIGHT_NUM = 1;

    private static final String CONTENT_TYPE = "prefs_detail";

    private static final String CONTENT_KEY = "prefs_key";

    private static final String CONTENT_VALUE = "prefs_value";

    private static final String sAuthority = "com.baidu.www.shareinprovider.url";

    private static final String TAG = "StringProvider";

    private static final UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {
        sMatcher.addURI(sAuthority, CONTENT_VALUE, MATCH_RIGHT_NUM);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        int code = sMatcher.match(uri);
        switch (code){
            case MATCH_RIGHT_NUM:
                return sAuthority + "." + CONTENT_TYPE;
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int code = sMatcher.match(uri);
        switch (code){
            case MATCH_RIGHT_NUM:
                return getPrefsValue(getContext(), contentValues);
            default:
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int code = sMatcher.match(uri);
        Log.v(TAG, uri.toString() + code);
        switch (code){
            case MATCH_RIGHT_NUM:
                putPrefsValue(getContext(), contentValues);
                break;
            default:
                break;
        }
        return 0;
    }

    private Uri getPrefsValue(Context cxt, ContentValues values){
        String key = values.getAsString(CONTENT_KEY);
        String defValue = values.getAsString(CONTENT_VALUE);

        SharedPreferences prefs = cxt.getSharedPreferences("NameShare", Context.MODE_PRIVATE);
        return Uri.parse(prefs.getString(key, defValue));
    }

    private void putPrefsValue(Context cxt, ContentValues values){
        String key = values.getAsString(CONTENT_KEY);
        String value = values.getAsString(CONTENT_VALUE);

        SharedPreferences prefs = cxt.getSharedPreferences("NameShare", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
