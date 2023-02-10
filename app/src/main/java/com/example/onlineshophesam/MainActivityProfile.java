package com.example.onlineshophesam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivityProfile extends AppCompatActivity {

    TextInputEditText etUserName,etPassword , etNumberPhone , etEmail;
    Button btnRegister;
    ImageView backimg;
    TextView tvMusic , tvProfile , tvDarbare , tvSettings , tvHome , tvtozihat , titleActionBar;
    LinearLayout llnavigation;
    TextInputLayout outlinedTextField , outlinedTextField2 , outlinedTextField3 , outlinedTextField4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        init();
        navigation();
        String[] a = loadData(MainActivityProfile.this);

        etUserName.setText(a[0]);
        etPassword.setText(a[1]);
        etNumberPhone.setText(a[2]);
        etEmail.setText(a[3]);

        if(etUserName.getText().toString().isEmpty()){
            backimg.setVisibility(View.INVISIBLE);
            tvtozihat.setText("برای عضویت در اپلیکیشن اطلاعات زیر را وارد کنید.");
            titleActionBar.setText("ثبت نام / ورود");
            llnavigation.setVisibility(View.INVISIBLE);
        }
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String numberPhone=etNumberPhone.getText().toString();
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();

                if(userName.length()<3){
                    outlinedTextField.setError("نام باید حداقل بیشتر از دو حرف باشد.  مانند: علی");
                }
                else if((numberPhone.length() != 11) || !numberPhone.startsWith("0")){
                    outlinedTextField2.setError("لطفا شماره موبایل را صحیح وارد کنید.  مانند: 09121234567");
                    outlinedTextField.setError(null);
                }
                else if(email.isEmpty()){
                    outlinedTextField3.setError("لطفا ایمیل را صحیح وارد کنید.  مانند: test@gmail.com");
                    outlinedTextField2.setError(null);
                    outlinedTextField.setError(null);
                }
                else if(password.length()<8 ){
                    outlinedTextField4.setError("کلمه عبور باید حداقل بیشتر از هشت کارکتر باشد.");
                    outlinedTextField3.setError(null);
                    outlinedTextField2.setError(null);
                    outlinedTextField.setError(null);
                }
                else {
                    SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Const.SHARED_PREF_KEY_AUTHNTICATION,true);
                    editor.putString("userName", userName);
                    editor.putString("password", password);
                    editor.putString("numberPhone", numberPhone);
                    editor.putString("email", email);

                    editor.apply();

                    final AlertDialog alertDialog;

                    View alertDialogView = LayoutInflater.from(MainActivityProfile.this).inflate(R.layout.alerdialogprofile, null);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityProfile.this);
                    builder.setView(alertDialogView);
                    builder.setCancelable(false);

                    alertDialog = builder.create();
                    alertDialog.show();

                    TextView tvtext = alertDialogView.findViewById(R.id.tvtext);
                    tvtext.setText("کاربر عزیز "+userName+" اطلاعات شما با موفقیت ثبت شد.");

                    TextView btnYes=alertDialogView.findViewById(R.id.btntaiid);
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MainActivityProfile.this , MainActivityHome.class);
                            startActivity(intent);
                        }

                    });
                    TextView btnEdit=alertDialogView.findViewById(R.id.btnedit);

                    btnEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                }

            }
        });

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
                Intent intent = new Intent(MainActivityProfile.this , DownloadMusicActivity.class);
                startActivity(intent);
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityProfile.this , MainActivityHome.class);
                startActivity(intent);
            }
        });
        tvDarbare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityProfile.this , MainActivityDarbarema.class);
                startActivity(intent);
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityProfile.this , MainActivitySettings.class);
                startActivity(intent);
            }
        });


    }

    public static String[] loadData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN, MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "");
        String password = sharedPreferences.getString("password", "");
        String numberPhone = sharedPreferences.getString("numberPhone", "");
        String email = sharedPreferences.getString("email", "");

        String[] a = {userName, password , numberPhone , email};

        return a ;

    }
    private void init(){

        tvMusic = findViewById(R.id.tvMusic);
        tvDarbare = findViewById(R.id.tvDarbare);
        tvSettings = findViewById(R.id.tvSettings);
        tvProfile = findViewById(R.id.tvProfile);
        tvHome = findViewById(R.id.tvHome);
        llnavigation=findViewById(R.id.llnavigation);
        backimg = findViewById(R.id.backimg);

        titleActionBar = findViewById(R.id.titleActionBar);
        tvtozihat = findViewById(R.id.tvtozihat);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        etNumberPhone = findViewById(R.id.etNumberPhone);
        etEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btnRegister);

        outlinedTextField=findViewById(R.id.outlinedTextField);
        outlinedTextField2=findViewById(R.id.outlinedTextField2);
        outlinedTextField3=findViewById(R.id.outlinedTextField3);
        outlinedTextField4=findViewById(R.id.outlinedTextField4);

    }
}