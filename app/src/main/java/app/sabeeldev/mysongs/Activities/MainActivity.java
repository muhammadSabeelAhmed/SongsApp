package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.Adpater.MainAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRv();
        showList();
        // mainAdapter = new MainAdapter();

//        mainAdapter.(Global.sortedList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        recyclerView.setAdapter(mainAdapter);
    }

    private void setupRv() {
//        rvMain.setHasFixedSize(true);
        recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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