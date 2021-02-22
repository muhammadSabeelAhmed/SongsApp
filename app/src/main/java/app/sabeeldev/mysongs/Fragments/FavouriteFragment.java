package app.sabeeldev.mysongs.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.Adpater.FavouriteAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.Favourite;

public class FavouriteFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    FavouriteAdapter favouriteAdapter;

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
        recentHandler();
    }


    public void recentHandler() {
        MainActivity.viewModel.getAllFav().observe(this, new Observer<List<Favourite>>() {
            @Override
            public void onChanged(List<Favourite> recents) {
                favouriteAdapter.setFavourite(recents);
          //  Toast.makeText(v.getContext(), "onChanged"+recents.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}