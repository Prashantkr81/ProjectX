package com.example.projectx;

import static com.example.projectx.R.raw.*;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //creating instances of methods and variables...Declaration(done before OnCreate bcz of lifecycle of activity as they are used in function creation)
    ImageButton playPauseButton;
    MediaPlayer mediaPlayer;
    ImageButton nextButton;
    ImageButton prevButton;
    AudioManager audioManager;

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

    //next button function
    public void playNext() {
        mediaPlayer.reset();
        currentIndex = (currentIndex + 1 ) % audioFiles.length; // Loop back to the start
        setTitle(currentIndex);
        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]);
        mediaPlayer.start();
        TextView total_time = findViewById(R.id.total_time);
        total_time.setText(formatTime(mediaPlayer.getDuration()));
        SeekBar seekProgress = findViewById(R.id.seekBar);
        seekProgress.setMax(mediaPlayer.getDuration());
        replaceButton(R.drawable.pause_circle_svgrepo_com);
    }

    //previous button function
    public void playPrevious() {
        mediaPlayer.reset();
        currentIndex = (currentIndex - 1 +audioFiles.length) % audioFiles.length; // Loop back to the start
        setTitle(currentIndex);

        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]);
        mediaPlayer.start();
        TextView total_time = findViewById(R.id.total_time);
        total_time.setText(formatTime(mediaPlayer.getDuration()));
        SeekBar seekProgress = findViewById(R.id.seekBar);
        seekProgress.setMax(mediaPlayer.getDuration());
        replaceButton(R.drawable.pause_circle_svgrepo_com);
    }

    //set title for audio
    public void setTitle(int index){
//        mediaPlayer.reset();
        TextView songtitle = findViewById(R.id.songtitle);
        songtitle.setText((titles[index]));
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        playPauseButton = findViewById(R.id.play_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_Button);

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


        //Audio volume seek bar
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekVol= findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mediaPlayer = MediaPlayer.create(this, audioFiles[currentIndex]);

        
        //play button controls
        playPauseButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                replaceButton(R.drawable.play_button_svgrepo_com);
            }
            else {
                mediaPlayer.start();

                setTitle(currentIndex);
                TextView total_time = findViewById(R.id.total_time);
                total_time.setText(formatTime(mediaPlayer.getDuration()));
                replaceButton(R.drawable.pause_circle_svgrepo_com);

            }
        });

        nextButton.setOnClickListener(v -> playNext());
        prevButton.setOnClickListener(v -> playPrevious());

        SeekBar seekProgress = findViewById(R.id.seekBar);
        seekProgress.setMax(mediaPlayer.getDuration());


        //seekbar updation
        final Handler handler = new Handler(Looper.getMainLooper()); // Ensure UI updates on main thread
        Runnable updateSeekBarRunnable = () -> seekProgress.setProgress(mediaPlayer.getCurrentPosition());
        seekProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Only seek if the change is from the user
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

// Update every 500ms
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBarRunnable.run();
                TextView current_time = findViewById(R.id.current_time);
                current_time.setText(formatTime(mediaPlayer.getCurrentPosition()));
                handler.postDelayed(this, 100);
            }
        }, 0);


    }
}





    

