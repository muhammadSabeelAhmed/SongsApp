package app.sabeeldev.mysongs.GeneralClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.ArrowPositionRules;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.BalloonSizeSpec;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.Model.Video;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.DbViewmModel;
import app.sabeeldev.mysongs.RoomDatabase.Favourite;
import app.sabeeldev.mysongs.RoomDatabase.Recent;

public class Global {
    public static boolean isPlay = false;
    public static boolean back_status = false;
    public static ArrayList<PlayList.Songs> mySongslists = new ArrayList<>();
    public static ArrayList<String> playList = new ArrayList<>();
    public static ArrayList<SongsMaster> sortedList = new ArrayList<>();
    public static List<Favourite> favList = new ArrayList<>();
    public static List<Recent> recentList = new ArrayList<>();
    public static ArrayList<Video.AllFormats> videosFormats = new ArrayList<>();
    public static String TEST_URL_MP4 = "";
    public static String TEST_URL_MP3 = "";
    public static String duration = "";
    public static String message = "";
    public static String[] API_KEY = {"e833a616b2mshee1f6fe52763456p17b9a0jsn2cc1159eb10a", "1120ad2ab4msha38bfe8c8a0d860p1e23b4jsn22089439ad47"};
    public static String CURRENT_API = API_KEY[1];
    public static String videoTitle = "";
    public static String playListSelected = "";
    public static String videoCode = "";
    public static String imgURL = "";
    public static KProgressHUD mKProgressHUD;
    public static int height = 0;
    public static int width = 0;
    public static String playerChecker = "";

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

    public static void showPopup(Context context, String message) {
        Balloon balloon = new Balloon.Builder(context)
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                .setArrowPosition(0.5f)
                .setWidth(BalloonSizeSpec.WRAP)
                .setHeight(65)
                .setTextSize(15f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText(message)
                .setTextColor(ContextCompat.getColor(context, R.color.black))
                .setTextIsHtml(true)
                .setIconDrawable(ContextCompat.getDrawable(context, R.drawable.logo_icon))
                .setBackgroundColor(ContextCompat.getColor(context, R.color.purple_700))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();
    }
}
