package com.muzikmasti.hindisongs90.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.muzikmasti.hindisongs90.Activities.MainActivity;
import com.muzikmasti.hindisongs90.Adpater.RecentAdapter;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RoomDatabase.Recent;


public class RecentFragment extends Fragment implements View.OnClickListener {
    View v;
    RecyclerView recyclerView;
  public static   RecentAdapter recentAdapter;
    TextView btn_clear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recent, container, false);
        init();
        return v;
    }

    private void init() {
        recyclerView = v.findViewById(R.id.recent_recycler);
        recentAdapter = new RecentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(recentAdapter);

        btn_clear = v.findViewById(R.id.clear_recent);
        btn_clear.setOnClickListener(this);
        recentHandler();
    }


    public void recentHandler() {
        MainActivity.viewModel.getAllRecents().observe(this, new Observer<List<Recent>>() {
            @Override
            public void onChanged(List<Recent> recents) {
                Global.recentList = recents;
                recentAdapter.setRecent(recents);
                if (Global.recentList.size() > 0) {
                    btn_clear.setVisibility(View.VISIBLE);
                } else {
                    btn_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_recent:
                MainActivity.viewModel.clearRecent();
                break;
        }
    }
}

