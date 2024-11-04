package com.example.projectx;

import static com.example.projectx.R.raw.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    

    //replace play with pause button
    private void replaceButton(int drawableResId) {
        playPauseButton.setImageResource(drawableResId);

    }


    int[] audioFiles = {
            neele_neele_ambar_par,
            mere_samne_wali_khidki_mein,
            kaun_disa_mein,
            pal_pal_dil_ke_paas
    };
    String[] titles={
            "Neele Neele Ambar Par",
            "Mere Samne Wali Khidki Mein",
            "Kaun Disa Mein",
            "Pal Pal Dil Ke Paas"
    };

    int currentIndex=0;


    public void playNext() {
        mediaPlayer.reset();
        currentIndex = (currentIndex + 1 ) % audioFiles.length; // Loop back to the start
        setTitle(currentIndex);
        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]);
        mediaPlayer.start();
        replaceButton(R.drawable.pause_circle_svgrepo_com);
    }

    public void playPrevious() {
        mediaPlayer.reset();
        currentIndex = (currentIndex - 1 +audioFiles.length) % audioFiles.length; // Loop back to the start
        setTitle(currentIndex);

        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]);
        mediaPlayer.start();
        replaceButton(R.drawable.pause_circle_svgrepo_com);
    }

    public void setTitle(int index){
        mediaPlayer.reset();
        TextView songtitle = findViewById(R.id.songtitle);
        songtitle.setText((titles[index]));

    }

    //creating instances of methods and variables
     ImageButton playPauseButton;
     MediaPlayer mediaPlayer;
     ImageButton nextButton;
     ImageButton prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        playPauseButton = findViewById(R.id.play_button);
        nextButton = findViewById(R.id.next_button); // Ensure this line is included
        prevButton = findViewById(R.id.prev_Button); // Ensure this line is included

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


        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]); // currentIndex can be set based on your logic


        nextButton.setOnClickListener(v -> playNext());
        prevButton.setOnClickListener(v -> playPrevious());

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
    }



}

    

