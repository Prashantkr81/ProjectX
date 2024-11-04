package com.example.projectx;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class offlinePlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.playlist);

//        ListView listView = findViewById(R.id.listElement);


        ImageButton player = findViewById(R.id.player);
        ImageButton offlinePlaylist = findViewById(R.id.offlinePlaylist);
        ImageButton onlinePlaylist = findViewById(R.id.onlinePlaylist);



        player.setOnClickListener(view -> {
            Intent intent = new Intent(com.example.projectx.offlinePlaylist.this, MainActivity.class);
            startActivity(intent);
        });
        onlinePlaylist.setOnClickListener(view -> {
            Intent intent = new Intent(com.example.projectx.offlinePlaylist.this, onlinePlaylist.class);
            startActivity(intent);
        });
    }
}
