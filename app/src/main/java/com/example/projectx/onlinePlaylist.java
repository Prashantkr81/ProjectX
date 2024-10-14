package com.example.projectx;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class onlinePlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.playlist2);

        ImageButton player = findViewById(R.id.player);
        ImageButton offlinePlaylist = findViewById(R.id.offlinePlaylist);
        ImageButton onlinePlaylist = findViewById(R.id.onlinePlaylist);

        player.setOnClickListener(view -> {
            Intent intent = new Intent(onlinePlaylist.this, MainActivity.class);
            startActivity(intent);
        }); // Close the setOnClickListener method properly here

        // Add similar listeners for other buttons if needed
        offlinePlaylist.setOnClickListener(view -> {
            Intent intent = new Intent(onlinePlaylist.this, offlinePlaylist.class);
            startActivity(intent);
        });
//
//        onlinePlaylist.setOnClickListener(view -> {
//            Intent intent = new Intent(onlinePlaylist.this, onlinePlaylist.class);
//            startActivity(intent);
//        });
    }
}
