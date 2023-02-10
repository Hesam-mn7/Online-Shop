package com.example.onlineshophesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivityPic extends BaseActivity {
    ImageView  backimg;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pic);

        img = findViewById(R.id.img);
        backimg = findViewById(R.id.backimg);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        if(intent == null){
            finish();
            return;
        }

        String imgId = intent.getStringExtra("img");
        Picasso.get()
                .load(imgId)
                .placeholder(R.drawable.loadingg)
                .error(R.drawable.nophoto)
                .into(img);
    }
}