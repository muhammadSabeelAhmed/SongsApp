package app.sabeeldev.mysongs.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.sabeeldev.mysongs.Adpater.MainAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.R;

public class HomeFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

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
        showList();
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
    }

}