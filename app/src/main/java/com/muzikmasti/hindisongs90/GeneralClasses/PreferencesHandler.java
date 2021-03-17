package com.muzikmasti.hindisongs90.GeneralClasses;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHandler {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String APP_FIRST_TIME = "app_first_time";
    private static final String UEMAIL = "uemail";
    private static final String PLAYER = "player";
    private static final String CURRENT_PLAYLIST = "playlist";
    private static final String CURRENT_SONG = "song_id";
    private static final String ADS = "ads";
    public PreferencesHandler() {

    }

    public PreferencesHandler(Context context) {
        if (pref == null) {
            pref = context.getSharedPreferences("oldhindisongs", Context.MODE_PRIVATE);
            editor = pref.edit();
        }
    }


    public String getAppFirstTime() {
        return pref.getString(APP_FIRST_TIME, "true");
    }

    public void setAppFirstTime(String appFirstTime) {
        editor.putString(APP_FIRST_TIME, appFirstTime);
        editor.apply();
        editor.commit();
    }

    public String getUemail() {
        return pref.getString(UEMAIL, "");
    }

    public void setUemail(String uemail) {
        editor.putString(UEMAIL, uemail);
        editor.apply();
        editor.commit();
    }

    public String getPlayer() {
        return pref.getString(PLAYER, "video");
    }

    public void setPlayer(String certificates) {
        editor.putString(PLAYER, certificates);
        editor.apply();
        editor.commit();
    }

    public String getCurrentPlaylist() {
        return pref.getString(CURRENT_PLAYLIST, "");
    }

    public void setCurrentPlaylist(String currentPlaylist) {
        editor.putString(CURRENT_PLAYLIST, currentPlaylist);
        editor.apply();
        editor.commit();
    }

    public String getCurrentSong() {
        return pref.getString(CURRENT_SONG, "");
    }

    public void setCurrentSong(String currentSong) {
        editor.putString(CURRENT_SONG, currentSong);
        editor.apply();
        editor.commit();
    }

    public String getAds() {
        return pref.getString(ADS, "");
    }

    public void setAds(String ads) {
        editor.putString(ADS, ads);
        editor.apply();
        editor.commit();
    }
}

