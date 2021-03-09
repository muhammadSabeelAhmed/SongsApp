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
import com.muzikmasti.hindisongs90.Adpater.FavouriteAdapter;
import com.muzikmasti.hindisongs90.GeneralClasses.Global;
import com.muzikmasti.hindisongs90.R;
import com.muzikmasti.hindisongs90.RoomDatabase.Favourite;

public class FavouriteFragment extends Fragment implements View.OnClickListener {
    View v;
    RecyclerView recyclerView;
    FavouriteAdapter favouriteAdapter;
    TextView btn_clear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_favourite, container, false);
        init();
        return v;
    }

    private void init() {
        recyclerView = v.findViewById(R.id.favourite_recycler);
        favouriteAdapter = new FavouriteAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(favouriteAdapter);

        btn_clear = v.findViewById(R.id.clear_fav);
        btn_clear.setOnClickListener(this);
        
        favouriteHandler();

    }


    public void favouriteHandler() {
        MainActivity.viewModel.getAllFav().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> favourites) {
                Global.favList = favourites;
                favouriteAdapter.setFavourite(favourites);
                if (Global.favList.size() > 0) {
                    btn_clear.setVisibility(View.VISIBLE);
                } else {
                    btn_clear.setVisibility(View.GONE);
                }
                //  Toast.makeText(v.getContext(), "onChanged"+recents.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_fav:
                MainActivity.viewModel.clearFav();
                break;
        }
    }
}