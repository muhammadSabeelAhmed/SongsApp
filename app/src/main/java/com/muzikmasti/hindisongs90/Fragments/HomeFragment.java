package com.muzikmasti.hindisongs90.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.internal.GmsLogger;

import com.muzikmasti.hindisongs90.Adpater.MainAdapter;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.Model.SongsMaster;
import com.muzikmasti.hindisongs90.R;

public class HomeFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    Handler myhandler;
    Runnable myrunnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return v;
    }

    private void init() {
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
        recyclerView.setAdapter(mainAdapter);
    }

    private void showList() {
        for (int i = 0; i < Global.playList.size(); i++) { // 2 * 100 = 200 outer
            Log.d("MyPlayListsSize", "" + Global.sortedList.get(i).getMysongs().size());
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