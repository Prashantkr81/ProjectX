package com.example.projectx;

import static com.example.projectx.R.raw.kaun_disa_mein;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.media.MediaPlayer;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {




    //replace play with pause button
    private void replaceButton(int drawableResId) {
        playPauseButton.setImageResource(drawableResId);
    }


    //creating instances of methods and variables
     ImageButton playPauseButton;
     MediaPlayer mediaPlayer;
     SeekBar seekBar;
     Runnable runnable;
     TextView current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        playPauseButton = findViewById(R.id.play_button);

        ImageButton player = findViewById(R.id.player);
        ImageButton offlinePlaylist = findViewById(R.id.offlinePlaylist);
        ImageButton onlinePlaylist = findViewById(R.id.onlinePlaylist);



    //switch between three pages
        offlinePlaylist.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,offlinePlaylist.class);
            startActivity(intent);
        });

        onlinePlaylist.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, onlinePlaylist.class);
            startActivity(intent);
        });


        mediaPlayer = MediaPlayer.create(this, kaun_disa_mein);

        //play button controls
        playPauseButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                replaceButton(R.drawable.play_button_svgrepo_com);

            } else {
                mediaPlayer.start();
                replaceButton(R.drawable.pause_circle_svgrepo_com);

            }
        });


        seekBar


    }

    }

    

