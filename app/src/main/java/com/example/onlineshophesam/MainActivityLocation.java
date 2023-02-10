package com.example.onlineshophesam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivityLocation extends BaseActivity implements LocationListener {

    LocationManager locationManager;
    TextView  tvlng , tvalt , tvlat;
    Button btnback , btnnamayesh;
    ImageView backimg ;
    GifImageView pb;

    GPSBroadcast gpsBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_location);

        init();
        nanigation();
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        gpsBroadcast=new GPSBroadcast();

    }

    AlertDialog alertDialog ;
    class GPSBroadcast extends BroadcastReceiver {

        public GPSBroadcast() {
            View alertDialogView = LayoutInflater.from(MainActivityLocation.this).inflate(R.layout.alerdialoggps, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLocation.this);
            builder.setView(alertDialogView);
            builder.setCancelable(false);

            alertDialog = builder.create();

            ImageButton btngps = alertDialogView.findViewById(R.id.btngps);
            btngps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);

                }
            });
            ImageButton btnback = alertDialogView.findViewById(R.id.btnback);
            btnback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }

        @Override
        public void onReceive(Context context, Intent intent) {


            Boolean statusGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (statusGPS) {
                alertDialog.dismiss();
            } else {
                alertDialog.show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(gpsBroadcast,new IntentFilter(Const.ACTION_CHECK_GPS));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , 0);
            return;
        }else
        {
            requestLocationUpdate();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(this);
        unregisterReceiver(gpsBroadcast);
    }

    private void requestLocationUpdate()
    {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0)
        {
            if(grantResults[0] ==PackageManager.PERMISSION_GRANTED)
            {
                requestLocationUpdate();
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lng= location.getLongitude();
        double lat=location.getLatitude();
        double alt=location.getAltitude();

        Log.e(Const.TAG , "lat = " + lat + " lng = " + lng);

        tvlng.setText(""+lng);
        tvlat.setText(""+lat);
        tvalt.setText(""+alt);

        pb.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        alertDialog.show();


    }

    private void nanigation(){
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnnamayesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityLocation.this, "در حال حاضر دسترسی به این بخش امکان پذیر نیست!", Toast.LENGTH_SHORT).show();
            }
        });
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void init(){
        tvlng=findViewById(R.id.tvlng);
        tvlat=findViewById(R.id.tvlat);
        tvalt=findViewById(R.id.tvalt);
        btnback = findViewById(R.id.btnback);
        btnnamayesh = findViewById(R.id.btnnamayesh);
        backimg = findViewById(R.id.backimg);
        pb = findViewById(R.id.pb);
    }
}