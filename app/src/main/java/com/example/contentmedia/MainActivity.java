package com.example.contentmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.contentmedia.ui.contact.ContactActivity;
import com.example.contentmedia.ui.song.SongActivity;

public class MainActivity extends AppCompatActivity {
    Button btMediaPlayer;
    Button btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btMediaPlayer = findViewById(R.id.btMediaPlayer);
        btnContact = findViewById(R.id.btContact);

        btMediaPlayer.setOnClickListener(v -> startActivity(new Intent(this, SongActivity.class)));
        btnContact.setOnClickListener(v -> startActivity(new Intent(this, ContactActivity.class)));
    }
}

