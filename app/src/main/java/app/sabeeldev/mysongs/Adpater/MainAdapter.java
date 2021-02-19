package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.sabeeldev.mysongs.Activities.AllActivity;
import app.sabeeldev.mysongs.Activities.MainActivity;
import app.sabeeldev.mysongs.Activities.SplashActivity;
import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool recycledViewPool;
    ArrayList<SongsMaster> playList;
    Context context;

    public MainAdapter() {
        playList = new ArrayList<>();
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

//    public void setAllPlayList(ArrayList<SongsMaster> playList) {
//        this.playList = playList;
//        playListAdapter = new PlayListAdapter();
//        //  notifyDataSetChanged();
//    }

    public void addMain(SongsMaster songsMaster) {
        playList.add(songsMaster);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.main_playlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.rvOuter.setRecycledViewPool(recycledViewPool);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.playlist_title.setText(playList.get(position).getPlayList());
//        holder.rvOuter.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
//        //  playListAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlist_title, view_all;
        RecyclerView rvOuter;
        PlayListAdapter playListAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlist_title = itemView.findViewById(R.id.playlist_title);
            view_all = itemView.findViewById(R.id.viewAll);
//            view_all.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("ViewAllChecker", "" + playlist_title.getText().toString());
//                    Intent intent = new Intent(context, AllActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(new Intent(context, AllActivity.class));
//                }
//            });
            rvOuter = itemView.findViewById(R.id.mainPlaylist_recycler);
            setupRv();
        }

        private void setupRv() {
            rvOuter.setHasFixedSize(true);
            rvOuter.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            playListAdapter = new PlayListAdapter();
            rvOuter.setAdapter(playListAdapter);
        }

        public void bind(int position) {
            SongsMaster outer = playList.get(position);
            if (outer != null) {
                playlist_title.setText(outer.getPlayList());
                playListAdapter.addInner(outer.getMysongs());
                view_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ViewAllChecker", "" + playlist_title.getText().toString());
                        Intent intent = new Intent(context, AllActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(new Intent(context, AllActivity.class));
                    }
                });
            }
        }
    }
}
