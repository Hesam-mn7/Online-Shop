package com.example.onlineshophesam;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onlineshophesam.DbHelper.DbHelperProduct;
import com.example.onlineshophesam.adapter.ProductListGridViewAdapter;
import com.example.onlineshophesam.adapter.SlidingImage_Adapter;
import com.example.onlineshophesam.entity.Product;
import com.example.onlineshophesam.slide.ImageModel;
import com.viewpagerindicator.CirclePageIndicator;
//import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivityHome extends BaseActivity {

    TextView tvMusic , tvProfile , tvDarbare , tvSettings , tvHome;
    ImageView imgmylocation;
    GridView gridViewProduct;
    ListView listviewProduct;
    EditText etsearch;
    ViewPager pager;
    ImageView btnGridBlue , btnGridBlack , btnListViewBlack , btnListViewBlue;

    LinearLayout sildeshow;


    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.slidepic2, R.drawable.slidepic1,
            R.drawable.slidepic3,R.drawable.slidepic4 , R.drawable.slidepic5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        init();
        navigation();
        slideshow();
        shareprefrance();
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void slideshow() {

        //slideShow
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        mPager =  findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivityHome.this,imageModelArrayList));


        final CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
//        indicator.setRadius(4 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

        sildeshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.digikala.com/incredible-offers/"));
                startActivity(intent1);
            }
        });


    }
    private void initListGridView(){

        //create data
        ArrayList<Product> data = new DbHelperProduct(this).select();

        //create adaptor
        final ProductListGridViewAdapter productListViewAdapter = new ProductListGridViewAdapter(this , data , R.layout.listview_layout_product);
        final ProductListGridViewAdapter productGridViewAdapter = new ProductListGridViewAdapter(this , data , R.layout.gridview_layout_product);

        //bind
        gridViewProduct.setAdapter(productGridViewAdapter);
        listviewProduct.setAdapter(productListViewAdapter);

        btnGridBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGridBlue.setVisibility(View.VISIBLE);
                btnGridBlack.setVisibility(View.INVISIBLE);
                btnListViewBlue.setVisibility(View.INVISIBLE);
                btnListViewBlack.setVisibility(View.VISIBLE);
                gridViewProduct.setVisibility(View.VISIBLE);
                listviewProduct.setVisibility(View.INVISIBLE);
            }
        });

        btnListViewBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGridBlue.setVisibility(View.INVISIBLE);
                btnGridBlack.setVisibility(View.VISIBLE);
                btnListViewBlue.setVisibility(View.VISIBLE);
                btnListViewBlack.setVisibility(View.INVISIBLE);
                gridViewProduct.setVisibility(View.INVISIBLE);
                listviewProduct.setVisibility(View.VISIBLE);
            }
        });

        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productGridViewAdapter.getFilter().filter(s);
                productListViewAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        gridViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivityHome.this , MainActivityDetails.class);

                intent.putExtra("idProduct",product.getIdProduct());
                intent.putExtra("titleProduct", product.getTitleProduct());
                intent.putExtra("valueProduct", product.getValueProduct());
                intent.putExtra("modelProduct", product.getModelProduct());
                intent.putExtra("detailProduct", product.getDetailProduct());
                intent.putExtra("numberPhone", product.getNumberPhone());
                intent.putExtra("imgProduct", product.getImgProduct());
                intent.putExtra("name1" , product.getName1());
                intent.putExtra("name2" , product.getName2());
                intent.putExtra("name3" , product.getName3());
                intent.putExtra("nazar1" , product.getNazar1());
                intent.putExtra("nazar2" , product.getNazar2());
                intent.putExtra("nazar3" , product.getNazar3());
                intent.putExtra("like1" , product.getLike1());
                intent.putExtra("like2" , product.getLike2());
                intent.putExtra("like3" , product.getLike3());
                intent.putExtra("numStar" , product.getNumStar());
                intent.putExtra("favorite" , product.getFavorite());

                startActivity(intent);

            }
        });
        listviewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivityHome.this , MainActivityDetails.class);

                intent.putExtra("idProduct",product.getIdProduct());
                intent.putExtra("titleProduct", product.getTitleProduct());
                intent.putExtra("valueProduct", product.getValueProduct());
                intent.putExtra("modelProduct", product.getModelProduct());
                intent.putExtra("detailProduct", product.getDetailProduct());
                intent.putExtra("numberPhone", product.getNumberPhone());
                intent.putExtra("imgProduct", product.getImgProduct());
                intent.putExtra("name1" , product.getName1());
                intent.putExtra("name2" , product.getName2());
                intent.putExtra("name3" , product.getName3());
                intent.putExtra("nazar1" , product.getNazar1());
                intent.putExtra("nazar2" , product.getNazar2());
                intent.putExtra("nazar3" , product.getNazar3());
                intent.putExtra("like1" , product.getLike1());
                intent.putExtra("like2" , product.getLike2());
                intent.putExtra("like3" , product.getLike3());
                intent.putExtra("numStar" , product.getNumStar());
                intent.putExtra("favorite" , product.getFavorite());

                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        initListGridView();
    }

    private void navigation(){

        tvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivityHome.this , DownloadMusicActivity.class);
                startActivity(intent);
            }
        });
        tvDarbare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this , MainActivityDarbarema.class);
                startActivity(intent);
            }
        });

        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this , MainActivityProfile.class);
                startActivity(intent);
            }
        });
        imgmylocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this , MainActivityLocation.class);
                startActivity(intent);

            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityHome.this , MainActivitySettings.class);
                startActivity(intent);
            }
        });

    }
    private void shareprefrance(){
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN , MODE_PRIVATE);
        int themeValue = sharedPreferences.getInt(Const.SHARED_PREF_KEY_COLORAGAHI, 0);
        if(themeValue!=0)
        {
            setTheme(themeValue);
        }

    }

    private void init(){
        tvMusic = findViewById(R.id.tvMusic);
        tvDarbare = findViewById(R.id.tvDarbare);
        tvSettings = findViewById(R.id.tvSettings);
        tvProfile = findViewById(R.id.tvProfile);
        tvHome = findViewById(R.id.tvHome);

        imgmylocation = findViewById(R.id.imgmylocation);
        etsearch = findViewById(R.id.etsearch);
        pager = findViewById(R.id.pager);

        btnGridBlack = findViewById(R.id.btnGridBlack);
        btnListViewBlack = findViewById(R.id.btnListViewBlack);
        btnListViewBlue = findViewById(R.id.btnListViewBlue);
        btnGridBlue = findViewById(R.id.btnGridBlue);
        listviewProduct = findViewById(R.id.listviewProduct);
        gridViewProduct = findViewById(R.id.gridviewProduct);

        sildeshow = findViewById(R.id.sildeshow);

    }
}