package com.example.onlineshophesam.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.onlineshophesam.entity.Product;

import java.util.ArrayList;

public class DbHelperProduct extends SQLiteOpenHelper {

    public static final String TABLE_NAME = Product.class.getSimpleName();
    public static final String FIELD_ID = "idProduct";
    public static final String FIELD_TITLE = "titleProduct";
    public static final String FILED_DETAILS = "detailProduct";
    public static final String FILED_IMG = "imgProduct";
    public static final String FIELD_VALUE = "valueProduct";
    public static final String FIELD_MODEL = "modelProduct";
    public static final String FILED_NUMBERPHONE = "numberPhone";

    public static final String FILED_NAME1 = "name1";
    public static final String FILED_NAME2 = "name2";
    public static final String FILED_NAME3 = "name3";
    public static final String FILED_NAZAR1 = "nazar1";
    public static final String FILED_NAZAR2 = "nazar2";
    public static final String FILED_NAZAR3 = "nazar3";
    public static final String FILED_LIKE1 = "like1";
    public static final String FILED_LIKE2 = "like2";
    public static final String FILED_LIKE3 = "like3";

    public static final String FILED_NUMSTAR = "numStar";
    public static final String FILED_FAVORITE = "favorite";

    public static final String DATABASE_NAME = "DB.ONLINESHOP";
    public static final int DATABASE_VERSION = 20;

    public DbHelperProduct(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createTable(db);
    }

    private void createTable(SQLiteDatabase db){
        String query = "create table IF NOT EXISTS "+ TABLE_NAME + " (" +
                FIELD_ID + " varchar(10) primary key , " +
                FIELD_TITLE + " varchar(50) , " +
                FILED_DETAILS + " text , " +
                FILED_IMG + " varchar(100) , " +
                FIELD_MODEL + " varchar(50) , " +
                FILED_NUMBERPHONE + " varchar(25) ,  " +
                FIELD_VALUE + " varchar(25) , " +
                FILED_NAME1 + " varchar(25) , "  +
                FILED_NAME2 + " varchar(25) , "  +
                FILED_NAME3 + " varchar(25) , "  +
                FILED_NAZAR1 + " text , "  +
                FILED_NAZAR2 + " text , "  +
                FILED_NAZAR3 + " text , "  +
                FILED_LIKE1 + " varchar(2) , "  +
                FILED_LIKE2 + " varchar(2) , "  +
                FILED_LIKE3 + " varchar(2) , "  +
                FILED_NUMSTAR + " varchar(5) , "  +
                FILED_FAVORITE + " varchar(2)  "  +
                ")";
        db.execSQL(query);
    }
    public long insert (Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID,product.getIdProduct());
        contentValues.put(FIELD_TITLE,product.getTitleProduct());
        contentValues.put(FILED_DETAILS,product.getDetailProduct());
        contentValues.put(FILED_IMG,product.getImgProduct());
        contentValues.put(FIELD_MODEL,product.getModelProduct());
        contentValues.put(FILED_NUMBERPHONE,product.getNumberPhone());
        contentValues.put(FIELD_VALUE,product.getValueProduct());
        contentValues.put(FILED_NAME1,product.getName1());
        contentValues.put(FILED_NAME2,product.getName2());
        contentValues.put(FILED_NAME3,product.getName3());
        contentValues.put(FILED_NAZAR1,product.getNazar1());
        contentValues.put(FILED_NAZAR2,product.getNazar2());
        contentValues.put(FILED_NAZAR3,product.getNazar3());
        contentValues.put(FILED_LIKE1,product.getLike1());
        contentValues.put(FILED_LIKE2,product.getLike2());
        contentValues.put(FILED_LIKE3,product.getLike3());
        contentValues.put(FILED_NUMSTAR,product.getNumStar());
        contentValues.put(FILED_FAVORITE,product.getFavorite());

        return db.insert(TABLE_NAME,null,contentValues);

    }

    public ArrayList<Product> select(){
        ArrayList<Product> result = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {FIELD_ID , FIELD_TITLE , FILED_DETAILS , FILED_IMG , FIELD_MODEL , FILED_NUMBERPHONE  , FIELD_VALUE ,
                 FILED_NAME1 , FILED_NAME2 , FILED_NAME3 , FILED_NAZAR1 , FILED_NAZAR2 , FILED_NAZAR3 , FILED_LIKE1 , FILED_LIKE2
                , FILED_LIKE3 , FILED_NUMSTAR , FILED_FAVORITE
        };

        Cursor cursor = db.query(TABLE_NAME , columns , null , null , null , null , null);

        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String idProduct = cursor.getString(cursor.getColumnIndex(FIELD_ID));
                String titleProduct = cursor.getString(cursor.getColumnIndex(FIELD_TITLE));
                String detailProduct = cursor.getString(cursor.getColumnIndex(FILED_DETAILS));
                String imgProduct = cursor.getString(cursor.getColumnIndex(FILED_IMG));
                String modelProduct = cursor.getString(cursor.getColumnIndex(FIELD_MODEL));
                String numberPhone = cursor.getString(cursor.getColumnIndex(FILED_NUMBERPHONE));
                String valueProduct = cursor.getString(cursor.getColumnIndex(FIELD_VALUE));
                String name1 = cursor.getString(cursor.getColumnIndex(FILED_NAME1));
                String name2 = cursor.getString(cursor.getColumnIndex(FILED_NAME2));
                String name3 = cursor.getString(cursor.getColumnIndex(FILED_NAME3));
                String nazar1 = cursor.getString(cursor.getColumnIndex(FILED_NAZAR1));
                String nazar2 = cursor.getString(cursor.getColumnIndex(FILED_NAZAR2));
                String nazar3 = cursor.getString(cursor.getColumnIndex(FILED_NAZAR3));
                String like1 = cursor.getString(cursor.getColumnIndex(FILED_LIKE1));
                String like2 = cursor.getString(cursor.getColumnIndex(FILED_LIKE2));
                String like3 = cursor.getString(cursor.getColumnIndex(FILED_LIKE3));
                String numStar = cursor.getString(cursor.getColumnIndex(FILED_NUMSTAR));
                String favorite = cursor.getString(cursor.getColumnIndex(FILED_FAVORITE));

                Product p = new Product(idProduct , titleProduct , detailProduct , imgProduct , modelProduct , numberPhone, valueProduct ,
                         name1 , name2 , name3 , nazar1 , nazar2 , nazar3 , like1 , like2 , like3 , numStar , favorite
                );
                result.add(p);

            }
        }
        cursor.close();
        return result;

    }

    public boolean updatenumStar(String id, String numStar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, id);
        contentValues.put(FILED_NUMSTAR, numStar);
        return db.update(TABLE_NAME, contentValues, FIELD_ID + "=" + id, null)>0;
    }

    public boolean updateFavorite(String id, String favoriteId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, id);
        contentValues.put(FILED_FAVORITE, favoriteId);
        return db.update(TABLE_NAME, contentValues, FIELD_ID + "=" + id, null)>0;
    }


    public boolean checkEmptyDataBase(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Boolean rowExists ;

        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }



}
