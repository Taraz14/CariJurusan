package id.kunya.carijurusan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by muhammad on 03/01/2020.
 */
public class PreferencesHelper {
    private static final String SHARED_PREF_NAME = "Settings";
    private static final String SHARED_PREF_ORDER = "General Values";
    private static final String TAG_LOGIN = "login";

    private static PreferencesHelper mInstance;
    private static Context mCtx;

    private PreferencesHelper(Context context) {
        mCtx = context;
    }

    public static synchronized PreferencesHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreferencesHelper(context);
        }
        return mInstance;
    }


    public boolean setString(String tag, String value){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag, value);
        editor.apply();
        return true;
    }

    public boolean saveLoginID(String nim){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_LOGIN, nim);
        editor.apply();
        return true;
    }

    public String getLoginID(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_LOGIN, null);
    }

    public String getStringData(String tag){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(tag, null);
    }

    public boolean setDataNull(String tag, String value){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag, value);
        editor.apply();
        return true;
    }

    public boolean setDataOrder(String tag, String value){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_ORDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag, value);
        editor.apply();
        return true;
    }

}
