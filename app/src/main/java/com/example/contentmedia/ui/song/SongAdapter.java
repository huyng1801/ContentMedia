package com.example.contentmedia.ui.song;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentmedia.R;
import com.example.contentmedia.model.Song;

import java.util.ArrayList;
import java.util.Random;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    ArrayList<Song> lstSong;
    Context context;
    SongUserCallback songUserCallback;


    public SongAdapter(ArrayList<Song> lstSong) {
        this.lstSong = lstSong;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View userView=inflater.inflate(R.layout.itemsong,parent,false);
        SongViewHolder viewHolder=new SongViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song item=lstSong.get(position);
        holder.tvTitle.setText(item.getTitle());
        //tạo màu random img cho Vector Asset
        Random rnd=new Random();
        int color= Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
        holder.ivSong.setImageResource(R.drawable.baseline_music_note_24);
        holder.ivSong.setColorFilter(color);
        //tao event
        holder.ivPlay.setOnClickListener(view->songUserCallback.onItemClickPlay(item.getFilename()));
        holder.ivPause.setOnClickListener(view->songUserCallback.onItemClickStop());
    }

    @Override
    public int getItemCount() {

            return lstSong.size();

    }

    public void setSongCallback(SongActivity songActivity) {
        songUserCallback = songActivity;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivPlay;
        ImageView ivPause;
        ImageView ivSong;
        public SongViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_song_name);
            ivPlay=itemView.findViewById(R.id.iv_play);
            ivPause=itemView.findViewById(R.id.iv_pause);
            ivSong=itemView.findViewById(R.id.iv_song);
        }
    }
    public interface SongUserCallback {
        void onItemClickPlay(String filename);

        void onItemClickStop();
    }


}

