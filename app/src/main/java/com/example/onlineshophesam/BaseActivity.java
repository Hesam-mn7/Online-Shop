package com.example.onlineshophesam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends  AppCompatActivity{

    WifiBroadcast wifiBroadcast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
        if(sharedPreferences.contains(Const.SHARED_PREF_KEY_AUTHNTICATION))
        {
            boolean flag=sharedPreferences.getBoolean(Const.SHARED_PREF_KEY_AUTHNTICATION,false);
            if(!flag)
            {
                goToRegisterActivity();
            }
        }else
        {
            goToRegisterActivity();
        }

        int themeValue = sharedPreferences.getInt(Const.SHARED_PREF_KEY_THEME, 0);
        if(themeValue!=0)
        {
            setTheme(themeValue);
        }
        int fontValue = sharedPreferences.getInt(Const.SHARED_PREF_KEY_FONT,0);
        if (fontValue!=0)
        {
            setTheme(fontValue);
        }

        wifiBroadcast=new WifiBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(wifiBroadcast,new IntentFilter(Const.Action_CONNECTIVITY));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroadcast);
    }

    private void goToRegisterActivity()
    {
        Intent intent=new Intent(this,MainActivityProfile.class);
        startActivity(intent);
        finish();
    }

    AlertDialog alertDialog;

     class WifiBroadcast extends BroadcastReceiver {

        public WifiBroadcast() {
            View alert= LayoutInflater.from(BaseActivity.this).inflate(R.layout.alerdialogwifi,null);
            AlertDialog.Builder builder=new AlertDialog.Builder(BaseActivity.this);
            builder.setView(alert);
            builder.setCancelable(false);
            alertDialog=builder.create();
            ImageButton btnwifi = alert.findViewById(R.id.btnwifi);
            btnwifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            ImageButton btndata = alert.findViewById(R.id.btndata);
            btndata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));

                }
            });
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (isNetworkAvailable()){
                alertDialog.dismiss();
            }
            else{
                alertDialog.show();
            }
        }
    }
}
