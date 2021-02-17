package app.sabeeldev.mysongs.Adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.sabeeldev.mysongs.Model.PlayList;
import app.sabeeldev.mysongs.R;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {
    ArrayList<PlayList.Songs> newsongsPlayList;

    public void setPlayList(ArrayList<PlayList.Songs> songsPlayList) {
        this.newsongsPlayList = songsPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_items, parent, false);
        return new PlayListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView song_img;
        TextView song_title;
        CardView playlist_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_img = itemView.findViewById(R.id.playlistSong_img);
            song_title = itemView.findViewById(R.id.playlistSong_title);
            playlist_card = itemView.findViewById(R.id.playlist_card);
        }
    }
}
