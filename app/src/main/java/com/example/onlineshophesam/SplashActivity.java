package com.example.onlineshophesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.onlineshophesam.Service.ServiceProduct;

public class SplashActivity extends BaseActivity   {

    MyBroadcast myBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myBroadcast = new MyBroadcast();
        Intent intent = new Intent(this, ServiceProduct.class);
        startService(intent);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myBroadcast,new IntentFilter(Const.ACTION_NAME));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcast);

    }

    class MyBroadcast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {

            Intent newIntent = new Intent(SplashActivity.this , MainActivityHome.class);
            startActivity(newIntent);
            finish();
        }
    }
}