package com.example.contentmedia.ui.song;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.example.contentmedia.R;
import com.example.contentmedia.Utils.Utils;
import com.example.contentmedia.model.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SongActivity extends AppCompatActivity  implements  SongAdapter.SongUserCallback {

    ArrayList<Song> lstSong;
    RecyclerView rvSong;
    SongAdapter songAdapter;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        rvSong = findViewById(R.id.rcv_song);
        //
        Gson gson = new Gson();
        String data = Utils.getAssetJsonData(this);
        Type type = new TypeToken<ArrayList<Song>>() {
        }.getType();
        lstSong = gson.fromJson(data, type);


        songAdapter = new SongAdapter(lstSong);
        songAdapter.setSongCallback(this);




        // hiển thi
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvSong.setAdapter(songAdapter);
        rvSong.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClickPlay(String songName) {
        try {
            //lấy file mp3 tương ứng
            AssetFileDescriptor afd=getAssets().openFd("sound/"+songName);
            //khởi tạo media player
            if(player==null){
                player= new MediaPlayer();
            }
            player.stop();
            player.reset();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            player.prepare();
            player.start();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void stopMusicPlayer(){
        if(player==null)
            return;
        player.stop();
        player.release();
        player=null;
    }

    @Override
    public void onItemClickStop() {
        stopMusicPlayer();
    }
}
