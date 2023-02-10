package com.example.onlineshophesam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshophesam.Api.OnlineShopApi;
import com.example.onlineshophesam.adapter.MusicAdapter;
import com.example.onlineshophesam.entity.Music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadMusicActivity extends BaseActivity {

    TextView tvMusic , tvProfile , tvDarbare , tvSettings , tvHome;


    ListView listViewMusic;
    ImageView backimg;
    EditText etSearchMusic;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadmusic);

        init();
        navigation();

        checkPermission();
        readServer();
    }
    private void readServer(){
        //create interface from retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OnlineShopApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create interface from api
        final OnlineShopApi api = retrofit.create(OnlineShopApi.class);

        //make request
        final Call<ArrayList<Music>> request = api.getallMusic();

        //put into queue
        request.enqueue(new Callback<ArrayList<Music>>() {
            @Override
            public void onResponse(Call<ArrayList<Music>> call, Response<ArrayList<Music>> response) {
                if (response.code()==200){
                    progress.setVisibility(View.INVISIBLE);
                    final ArrayList<Music> data = response.body();
                    final MusicAdapter adapter = new MusicAdapter(DownloadMusicActivity.this , data);
                    listViewMusic.setAdapter(adapter);

                    etSearchMusic.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            adapter.getFilter().filter(s);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    listViewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                            AlertDialog alertDialog;
                            final AlertDialog.Builder builder = new AlertDialog.Builder(DownloadMusicActivity.this);
                            builder.setTitle("آیا مطمئن به انجام دانلود هستید ؟");
                            builder.setCancelable(false);
                            builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Music music = (Music) parent.getItemAtPosition(position);

                                    new DownloadTask().execute(music.getFileUrl());
                                }

                            });

                            builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });

                    ArrayList<Music> list1 = response.body();
                    for (Music item : list1){

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Music>> call, Throwable t) {

            }
        });

    }


    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }

    private class DownloadTask extends AsyncTask<String,Integer,String>
    {

        ProgressDialog progressDialog;
        public DownloadTask(){
            progressDialog = new ProgressDialog(DownloadMusicActivity.this);
            progressDialog.setTitle("دانلود");
            progressDialog.setMessage("در حال دانلود فایل ...");
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String namefile = generateString();

            String fileUrl = params[0];

            try {
                URL url = new URL(fileUrl);

                URLConnection connection = url.openConnection();

                int lenghtOfFile = connection.getContentLength();

                connection.connect();

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());


                File myFile = Environment.getExternalStorageDirectory();

                File folder = new File(myFile, "OnlineShopMusic");
                if (!folder.exists())
                    folder.mkdir();

                File file = new File(folder, generateString()+".mp3");

                OutputStream outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];

                int count = -1;
                int total = 0;

                while ((count=inputStream.read(buffer))!=-1){
                    total += count;
                    publishProgress((total*100)/lenghtOfFile);
                    outputStream.write(buffer,0,count);
                }
                outputStream.flush();
                outputStream.close();

                inputStream.close();

                //notification
                NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(DownloadMusicActivity.this);
                builder.setContentTitle("دانلود با موفقیت انجام شد");
                builder.setContentText(namefile+".mp3");
                builder.setSmallIcon(R.drawable.ic_music_circle);
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");

                PendingIntent pendingIntent=PendingIntent.getActivity(DownloadMusicActivity.this , 0 ,intent , PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    builder.setChannelId("chanelId");
                    NotificationChannel channel = new NotificationChannel("chanelId", "name", NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(channel);
                }

                Notification notification = builder.build();
                manager.notify(1,notification);

                return "دانلود با موفقیت انجام شد";

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(DownloadMusicActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                return "دانلود انجام نشد";
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(DownloadMusicActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private void navigation(){

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DownloadMusicActivity.this , MainActivityHome.class);
                startActivity(intent);
            }
        });
        tvDarbare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadMusicActivity.this , MainActivityDarbarema.class);
                startActivity(intent);
            }
        });
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadMusicActivity.this , MainActivityProfile.class);
                startActivity(intent);
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadMusicActivity.this , MainActivitySettings.class);
                startActivity(intent);
            }
        });

    }

    private void init(){
        listViewMusic = findViewById(R.id.listViewMusic);
        backimg = findViewById(R.id.backimg);
        etSearchMusic = findViewById(R.id.etSearchMusic);
        progress = findViewById(R.id.progress);

        tvMusic = findViewById(R.id.tvMusic);
        tvDarbare = findViewById(R.id.tvDarbare);
        tvSettings = findViewById(R.id.tvSettings);
        tvProfile = findViewById(R.id.tvProfile);
        tvHome = findViewById(R.id.tvHome);
    }
}