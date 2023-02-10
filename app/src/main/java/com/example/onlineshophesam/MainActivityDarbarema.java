package com.example.onlineshophesam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityDarbarema extends BaseActivity {

    TextView tvMusic , tvProfile , tvDarbare , tvSettings , tvHome;
    ImageView backimg ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_darbarema);
        init();
        navigation();

    }

    private void navigation(){
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbarema.this , DownloadMusicActivity.class);
                startActivity(intent);
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbarema.this , MainActivityHome.class);
                startActivity(intent);
            }
        });

        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbarema.this , MainActivityProfile.class);
                startActivity(intent);
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDarbarema.this , MainActivitySettings.class);
                startActivity(intent);
            }
        });

    }

    private void init(){
        tvMusic = findViewById(R.id.tvMusic);
        tvDarbare = findViewById(R.id.tvDarbare);
        tvSettings = findViewById(R.id.tvSettings);
        tvProfile = findViewById(R.id.tvProfile);
        tvHome = findViewById(R.id.tvHome);

        backimg = findViewById(R.id.backimg);

    }
}