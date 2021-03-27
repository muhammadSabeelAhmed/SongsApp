package com.muzikmasti.hindisongs90.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.muzikmasti.hindisongs90.Adpater.AllPlayListAdapter;
import com.muzikmasti.hindisongs90.Ads.AdmobIntrestitialAds;
import com.muzikmasti.hindisongs90.Ads.FacebookIntrestitialAds;
import com.muzikmasti.hindisongs90.Fragments.FacebookBanner;
import com.muzikmasti.hindisongs90.Fragments.GoogleBanner;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.R;

import static com.muzikmasti.hindisongs90.GeneralClasses.Global.playListSelected;

public class AllActivity extends AppCompatActivity {
    TextView viewAlltxt;
    RecyclerView recyclerView;
    AllPlayListAdapter playListAdapter;
    PreferencesHandler preferencesHandler;
    AdmobIntrestitialAds admobIntrestitialAds;
    FacebookIntrestitialAds facebookIntrestitialAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d("MyPlaylist", "" + playListSelected);
        playListAdapter = new AllPlayListAdapter();
        preferencesHandler = new PreferencesHandler(AllActivity.this);
        viewAlltxt = findViewById(R.id.viewAll_title);
        viewAlltxt.setText(playListSelected);
        recyclerView = findViewById(R.id.viewAll_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(playListAdapter);
        Log.d("MyfavSize", "" + Global.favList.size());

        for (int i = 0; i < Global.playList.size(); i++) {
            if (Global.playList.get(i).equals(playListSelected)) {
                playListAdapter.addAllItems(Global.sortedList.get(i).getMysongs());
                playListAdapter.notifyDataSetChanged();
            }
        }
        initAds();
    }


    private void initAds() {
        admobIntrestitialAds = new AdmobIntrestitialAds(AllActivity.this);
        facebookIntrestitialAds = new FacebookIntrestitialAds(AllActivity.this);

        if (preferencesHandler.getAds().equals("addmob")) {
            admobIntrestitialAds.initIntrestAds();
            Global.changeFragmentSplash(AllActivity.this, new GoogleBanner(), "GoogleBanner", false);
        } else if(preferencesHandler.getAds().equals("facebook")){
            Global.changeFragmentSplash(AllActivity.this, new FacebookBanner(), "FacebookBanner", false);
            facebookIntrestitialAds.initIntrestAds();
        }
    }


}