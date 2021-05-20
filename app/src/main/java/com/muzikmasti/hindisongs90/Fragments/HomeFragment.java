package com.muzikmasti.hindisongs90.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.rvadapter.AdmobNativeAdAdapter;
import com.muzikmasti.hindisongs90.Adpater.MainAdapter;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.GeneralClasses.PreferencesHandler;
import com.muzikmasti.hindisongs90.Model.SongsMaster;
import com.muzikmasti.hindisongs90.R;

import java.util.Random;

import me.timos.thuanle.fbnativeadadapter.FBNativeAdAdapter;

public class HomeFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    Handler myhandler;
    Runnable myrunnable;
    public static int random = 0;
    PreferencesHandler preferencesHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return v;
    }

    private void init() {
        preferencesHandler = new PreferencesHandler(v.getContext());
        setupRv();
        myhandler = new Handler();
        myrunnable = new Runnable() {
            @Override
            public void run() {
                if (Global.playList.size() > 0) {
                    showList();
                } else {
                    myhandler.postDelayed(myrunnable, 200);
                }
            }
        };
        myhandler.post(myrunnable);
    }

    private void setupRv() {
//        rvMain.setHasFixedSize(true);
        recyclerView = v.findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mainAdapter = new MainAdapter();
        Log.d("BannerId", "" + Global.API_KEY.get("Native"));
        if (preferencesHandler.getAds().equals("facebook")) {
            FBNativeAdAdapter fbAdapter = FBNativeAdAdapter.Builder.with(Global.API_KEY.get("Native"), mainAdapter).adItemIterval(4)
                    .build();
            recyclerView.setAdapter(fbAdapter);

        } else {
            AdmobNativeAdAdapter admobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with(Global.API_KEY.get("Native"), mainAdapter,
                    "small").adItemInterval(4).build();
            recyclerView.setAdapter(admobNativeAdAdapter);
        }
        //recyclerView.setAdapter(mainAdapter);
    }

    private void showList() {
        Global.myRandoms.clear();
        for (int i = 0; i < Global.playList.size(); i++) { // 2 * 100 = 200 outer
            Log.d("MyPlayListsSize", "" + Global.sortedList.get(i).getMysongs().size());
            Random rand = new Random();
            if (Global.sortedList.get(i).getMysongs().size() < 7) {
                random = rand.nextInt(Global.sortedList.get(i).getMysongs().size());
            } else {
                random = rand.nextInt(7);
            }
            Global.myRandoms.add(random);
            mainAdapter.addMain(new SongsMaster(Global.playList.get(i), Global.sortedList.get(i).getMysongs()));
            // outerAdapter.addOuter(new Outer("Recommended", inners));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Global.mKProgressHUD.isShowing()) {
                    Global.mKProgressHUD.dismiss();
                }
            }
        }, 1000);
    }
}