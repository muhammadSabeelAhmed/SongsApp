package app.sabeeldev.mysongs.GeneralClasses;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHandler {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String APP_FIRST_TIME = "app_first_time";
    private static final String UEMAIL = "uemail";
    private static final String PLAYER = "player";

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
}

