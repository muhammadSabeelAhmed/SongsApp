package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import app.sabeeldev.mysongs.Adpater.PlayListAdapter;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.R;

import static app.sabeeldev.mysongs.GeneralClasses.Global.playListSelected;

public class AllActivity extends AppCompatActivity {
    TextView viewAlltxt;
    RecyclerView recyclerView;
    PlayListAdapter playListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        Intent intent = getIntent();
        Log.d("MyPlaylist", "" + playListSelected);
        playListAdapter = new PlayListAdapter();
        viewAlltxt = findViewById(R.id.viewAll_title);
        viewAlltxt.setText(playListSelected);
        recyclerView = findViewById(R.id.viewAll_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(AllActivity.this, 3));
        recyclerView.setAdapter(playListAdapter);

        for (int i = 0; i < Global.playList.size(); i++) {
            if (Global.playList.get(i).equals(playListSelected)) {
                playListAdapter.addInner(Global.sortedList.get(i).getMysongs());
            }
        }

    }
}