package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.sabeeldev.mysongs.GeneralClasses.Global;
import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.Model.SongsMaster;
import app.sabeeldev.mysongs.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<SongsMaster> playList;
    Context context;
    PlayListAdapter playListAdapter;

    public void setAllPlayList(ArrayList<SongsMaster> playList) {
        this.playList = playList;
        playListAdapter = new PlayListAdapter();
        //  notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.main_playlist, parent, false);
        return new MainAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.playlist_title.setText(playList.get(position).getPlayList());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        playListAdapter.setPlayList(playList.get(position).getMysongs());
        holder.recyclerView.setAdapter(playListAdapter);
      //  playListAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlist_title, view_all;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlist_title = itemView.findViewById(R.id.playlist_title);
            view_all = itemView.findViewById(R.id.viewAll);
            recyclerView = itemView.findViewById(R.id.mainPlaylist_recycler);
        }
    }
}
