package app.sabeeldev.mysongs.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.Adpater.RecentAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.Recent;


public class RecentFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    RecentAdapter recentAdapter;

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
        recentHandler();
    }


    public void recentHandler() {
        MainActivity.viewModel.getAllRecents().observe(this, new Observer<List<Recent>>() {
            @Override
            public void onChanged(List<Recent> recents) {
                recentAdapter.setRecent(recents);
//                Toast.makeText(MainActivity.this, "onChanged\n" + stores.get(1).getName() + "\n" + stores.get(1).getContact(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

