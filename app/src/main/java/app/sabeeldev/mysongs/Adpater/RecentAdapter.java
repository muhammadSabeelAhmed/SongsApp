package app.sabeeldev.mysongs.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.sabeeldev.mysongs.R;
import app.sabeeldev.mysongs.RoomDatabase.Recent;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {
    List<Recent> recentList;
    Context context;

    public RecentAdapter() {
        recentList = new ArrayList<>();
    }

    public void setRecent(List<Recent> recentList) {
        this.recentList = recentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.fav_rec_items, parent, false);
        return new RecentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title.setText(recentList.get(position).getTitle());
        holder.txt_album.setText(recentList.get(position).getAlbumName());
        Picasso.get().load(recentList.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return recentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt_remove, txt_title, txt_album;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.favRecSong_img);
            txt_remove = itemView.findViewById(R.id.favRecSong_remove);
            txt_title = itemView.findViewById(R.id.favRecSong_title);
            txt_album = itemView.findViewById(R.id.favRecSong_album);
        }
    }
}
