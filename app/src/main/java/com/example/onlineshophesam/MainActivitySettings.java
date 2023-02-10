package com.example.onlineshophesam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.constraintlayout.widget.ConstraintLayoutStates;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivitySettings extends BaseActivity {
    Button  back;
    RadioGroup rgColorAgahi;
    RadioButton rbnight , btnday , rbDefult , rbandlso , rbAbi , rbmeshki ;
    LinearLayout shareApp;

    TextView tvMusic , tvProfile , tvDarbare , tvSettings , tvHome;
    ImageView backimg ;


    public static int ThemeValue=R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeValue);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        init();
        navigation();

        btnday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeValue=R.style.AppTheme;
                setCustomTheme(R.style.AppTheme);
                refresh();

            }
        });
        rbnight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeValue=R.style.darkTheme;
                setCustomTheme(R.style.darkTheme);
                refresh();
            }
        });
        rbandlso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeValue=R.style.fontTheme;
                setCustomFont(R.style.fontTheme);
                refresh();
            }
        });
        rbDefult.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ThemeValue=R.style.AppTheme;
                setCustomFont(R.style.fontThemebnazanin);
                refresh();
            }
        });

        rgColorAgahi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbAbi){
                    ThemeValue=R.style.AppTheme;
                    setCustomColor(R.style.colorAgahi);
                }
                else if(checkedId==R.id.rbmeshki) {
                    ThemeValue=R.style.AppTheme;
                    setCustomColor(R.style.colorAgahiDefult);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivitySettings.this , MainActivityHome.class);
                startActivity(intent);

            }
        });
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"اپلیکیشن فروشگاه آنلاین "+ "\n" +
                        " طراحی شده توسط : حسام الدین موسویون "+ "\n" +
                        " لینک دانلود : " + "https://uupload.ir/view/aztk_onlineshop.rar/"
                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    private void setCustomFont(int font)
    {
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.SHARED_PREF_KEY_FONT , font);
        editor.apply();
    }

    private void setCustomTheme(int theme)
    {
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.SHARED_PREF_KEY_THEME , theme);
        editor.apply();
    }

    private void setCustomColor(int color)
    {
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Const.SHARED_PREF_KEY_COLORAGAHI , color);
        editor.apply();
    }

    private void refresh()
    {
        finish();
        startActivity(getIntent());
    }

    private void navigation(){
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySettings.this , MainActivityHome.class);
                startActivity(intent);            }
        });

        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySettings.this , DownloadMusicActivity.class);
                startActivity(intent);
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySettings.this , MainActivityHome.class);
                startActivity(intent);
            }
        });

        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySettings.this , MainActivityProfile.class);
                startActivity(intent);
            }
        });
        tvDarbare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySettings.this , MainActivityDarbarema.class);
                startActivity(intent);
            }
        });

    }
    private void init(){

        back = findViewById(R.id.nback);
        btnday = findViewById(R.id.btnday);
        rbnight = findViewById(R.id.rbnight);
        rbDefult = findViewById(R.id.rbDefult);
        rbandlso = findViewById(R.id.rbandlso);
        rbAbi = findViewById(R.id.rbAbi);
        rbmeshki = findViewById(R.id.rbmeshki);
        rgColorAgahi = findViewById(R.id.rgColorAgahi);
        shareApp = findViewById(R.id.shareApp);

        tvMusic = findViewById(R.id.tvMusic);
        tvDarbare = findViewById(R.id.tvDarbare);
        tvSettings = findViewById(R.id.tvSettings);
        tvProfile = findViewById(R.id.tvProfile);
        tvHome = findViewById(R.id.tvHome);

        backimg = findViewById(R.id.backimg);
    }
}