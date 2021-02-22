package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;

public class AllPlayListAdapter extends RecyclerView.Adapter<AllPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayList.Songs> newsongsPlayList;

    public AllPlayListAdapter() {
        newsongsPlayList = new ArrayList<>();
    }

    public void addInner(List<PlayList.Songs> inners) {
        newsongsPlayList.clear();
        newsongsPlayList.addAll(inners);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllPlayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.playlist_items, parent, false);
        return new AllPlayListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlayListAdapter.ViewHolder holder, int position) {
        //  if (newsongsPlayList.get(position).getAlbumName().equals(currentPlayList)) {
        holder.playlist_card.setVisibility(View.VISIBLE);
        Picasso.get().load(newsongsPlayList.get(position).getImage()).into(holder.song_img);
        holder.song_title.setText(newsongsPlayList.get(position).getTitle());
//        } else {
//            holder.playlist_card.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return newsongsPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView song_img;
        TextView song_title;
        RelativeLayout playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_img = itemView.findViewById(R.id.playlistSong_img);
            song_title = itemView.findViewById(R.id.playlistSong_title);
            playlist_card = itemView.findViewById(R.id.playlist_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, song_title.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

