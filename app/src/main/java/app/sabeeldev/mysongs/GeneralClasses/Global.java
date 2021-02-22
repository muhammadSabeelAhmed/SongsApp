package app.sabeeldev.mysongs.GeneralClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.DbViewmModel;

public class Global {
    public static boolean back_status = false;
    public static ArrayList<PlayList.Songs> mySongslists = new ArrayList<>();
    public static ArrayList<String> playList = new ArrayList<>();
    public static ArrayList<SongsMaster> sortedList = new ArrayList<>();
    public static String playListSelected = "";


    public static void changeFragmentMain(Context context, Fragment fragment, String device_back_tag, boolean status) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,
                R.anim.fadeout);
        transaction.replace(R.id.main_container, fragment);
        if (status) {
            transaction.addToBackStack(device_back_tag);
        }
        back_status = status;
        transaction.commit();
    }

    public static void changeActivity(Context context, Activity activity) {
        Intent in = new Intent();
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        in.setClass(context, activity.getClass());
        context.startActivity(in);
    }
}
