package com.example.onlineshophesam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshophesam.DbHelper.DbHelperProduct;
import com.example.onlineshophesam.entity.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivityDetails extends BaseActivity {
    ImageView imgProductDetails , backimg , vertImg , favoriteEmpty , favorite ;
    TextView titleActionBar;
    TextView tvtitleProductDetails , tvmodelProductDetails , tvidProductDetails ,
            tvvalueProductDetails , tvdetailProductDetails ;
    TextView tvnazar1 , tvnazar2 , tvnazar3 , tvName1 , tvName2 , tvName3 ;
    LinearLayout lineardisLike1 , lineardisLike2 , lineardisLike3 ,
            linearLike1 , linearLike2 , linearLike3;

    Button btnchat , btncall ;

    RatingBar ratingBar;

    LinearLayout share , etelaresani , pishnehad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        init();
        navigation();

        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        final String idProduct = intent.getStringExtra("idProduct");
        final String titleProduct = intent.getStringExtra("titleProduct");
        final String valueProduct = intent.getStringExtra("valueProduct");
        final String modelProduct = intent.getStringExtra("modelProduct");
        String detailProduct = intent.getStringExtra("detailProduct");
        final String numberPhone = intent.getStringExtra("numberPhone");
        final String imgProduct = intent.getStringExtra("imgProduct");
        final String name1 = intent.getStringExtra("name1");
        final String name2 = intent.getStringExtra("name2");
        final String name3 = intent.getStringExtra("name3");
        final String nazar1 = intent.getStringExtra("nazar1");
        final String nazar2 = intent.getStringExtra("nazar2");
        final String nazar3 = intent.getStringExtra("nazar3");
        final String like1 = intent.getStringExtra("like1");
        final String like2 = intent.getStringExtra("like2");
        final String like3 = intent.getStringExtra("like3");
        final String numStar = intent.getStringExtra("numStar");
        final String favoriteId = intent.getStringExtra("favorite");

        if (favoriteId.equals("0")){
            favoriteEmpty.setVisibility(View.VISIBLE);
            favorite.setVisibility(View.INVISIBLE);
        }
        if(favoriteId.equals("1")){
            favoriteEmpty.setVisibility(View.INVISIBLE);
            favorite.setVisibility(View.VISIBLE);
        }

        favoriteEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteEmpty.setVisibility(View.INVISIBLE);
                favorite.setVisibility(View.VISIBLE);
                new DbHelperProduct(MainActivityDetails.this).updateFavorite(idProduct, "1");
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteEmpty.setVisibility(View.VISIBLE);
                favorite.setVisibility(View.INVISIBLE);
                new DbHelperProduct(MainActivityDetails.this).updateFavorite(idProduct, "0");
            }
        });

        ratingBar.setRating(Float.parseFloat(numStar));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                new DbHelperProduct(MainActivityDetails.this).updatenumStar(idProduct, String.valueOf(rating));
            }
        });

        titleActionBar.setText(titleProduct);

        tvtitleProductDetails.setText(titleProduct);
        tvmodelProductDetails.setText(modelProduct);
        tvvalueProductDetails.setText(valueProduct);
        tvdetailProductDetails.setText(detailProduct);
        tvidProductDetails.setText(idProduct);

        tvName1.setText(name1);
        tvName2.setText(name2);
        tvName3.setText(name3);
        tvnazar1.setText(nazar1);
        tvnazar2.setText(nazar2);
        tvnazar3.setText(nazar3);

        if (like1.equals("0")){
            linearLike1.setVisibility(View.INVISIBLE);
            lineardisLike1.setVisibility(View.VISIBLE);
        }
        if (like1.equals("1")){
            linearLike1.setVisibility(View.VISIBLE);
            lineardisLike1.setVisibility(View.INVISIBLE);
        }
        if (like2.equals("0")){
            linearLike2.setVisibility(View.INVISIBLE);
            lineardisLike2.setVisibility(View.VISIBLE);
        }
        if (like2.equals("1")){
            linearLike2.setVisibility(View.VISIBLE);
            lineardisLike2.setVisibility(View.INVISIBLE);
        }
        if (like3.equals("0")){
            linearLike3.setVisibility(View.INVISIBLE);
            lineardisLike3.setVisibility(View.VISIBLE);
        }
        if (like3.equals("1")){
            linearLike3.setVisibility(View.VISIBLE);
            lineardisLike3.setVisibility(View.INVISIBLE);
        }

        Picasso.get()
                .load(imgProduct)
                .placeholder(R.drawable.loadingg)
                .error(R.drawable.nophoto)
                .into(imgProductDetails);

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("sms:+" + numberPhone));
                startActivity(intent1);
            }
        });
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("tel:+" + numberPhone));
                startActivity(intent1);
            }
        });
        vertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);

                final BottomSheetDialog dialog = new BottomSheetDialog(MainActivityDetails.this);
                dialog.setContentView(view);
                dialog.show();
                share = view.findViewById(R.id.share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,"اپلیکیشن فروشگاه آنلاین حسام"+ "\n" +
                                " شماره کالا : "+ idProduct + "\n" +
                                " نام محصول : " + titleProduct  + "\n" +
                                " مدل : " + modelProduct  + "\n" +
                                " قیمت : " + valueProduct + " تومان "
                        );
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);

                    }
                });
                etelaresani = view.findViewById(R.id.etelaresani);
                etelaresani.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivityDetails.this, "اطلاع رسانی شگفت انگیز انجام شد", Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });
                pishnehad = view.findViewById(R.id.pishnehad);
                pishnehad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("https://www.digikala.com/incredible-offers/"));
                        startActivity(intent1);

                    }
                });

            }
        });

        imgProductDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivityDetails.this, MainActivityPic.class);
                intent1.putExtra("img", imgProduct);
                startActivity(intent1);

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

    }
    private void init(){

        titleActionBar = findViewById(R.id.titleActionBar);
        backimg = findViewById(R.id.backimg);

        imgProductDetails = findViewById(R.id.imgProductDetails);
        tvtitleProductDetails = findViewById(R.id.tvtitleProductDetails);
        tvmodelProductDetails = findViewById(R.id.tvmodelProductDetails);
        tvidProductDetails = findViewById(R.id.tvidProductDetails);
        tvvalueProductDetails = findViewById(R.id.tvvalueProductDetails);
        tvdetailProductDetails = findViewById(R.id.tvdetailProductDetails);
        btnchat = findViewById(R.id.btnchat);
        btncall = findViewById(R.id.btncall);
        vertImg = findViewById(R.id.vertImg);
        favoriteEmpty = findViewById(R.id.favoriteEmpty);
        favorite = findViewById(R.id.favorite);
        ratingBar = findViewById(R.id.rating);


        tvnazar1 = findViewById(R.id.tvnazar1);
        tvnazar2 = findViewById(R.id.tvnazar2);
        tvnazar3 = findViewById(R.id.tvnazar3);
        tvName1 = findViewById(R.id.tvName1);
        tvName2 = findViewById(R.id.tvName2);
        tvName3 = findViewById(R.id.tvName3);
        lineardisLike1 = findViewById(R.id.lineardisLike1);
        lineardisLike2 = findViewById(R.id.lineardisLike2);
        lineardisLike3 = findViewById(R.id.lineardisLike3);
        linearLike1 = findViewById(R.id.linearLike1);
        linearLike2 = findViewById(R.id.linearLike2);
        linearLike3 = findViewById(R.id.linearLike3);

    }
}