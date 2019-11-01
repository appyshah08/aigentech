package com.example.dummy.aigentech.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dummy.aigentech.model.CarSellerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neeta on 10/29/2019.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "car_db";

    private static DBHelper mInstance = null;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DBScript.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        onCreate(db);
    }

    public static DBHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public long insertCarEntry(ContentValues values) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();



        // insert row
        long id = db.insert(DBScript.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }



    public List<CarSellerModel> getAllModel() {
        List<CarSellerModel> carSellerModelList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DBScript.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarSellerModel carSellerModel = new CarSellerModel();
                carSellerModel.setId(cursor.getInt(cursor.getColumnIndex(DBScript.COLUMN_ID)));
                carSellerModel.setSeller_name(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_SELLER_NAME)));
                carSellerModel.setModel_name(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_MODEL_NAME)));
                carSellerModel.setManufacture_name(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_MANUFACTURE_NAME)));
                carSellerModel.setMileage(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_MILEAGE)));
                carSellerModel.setColour(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_COLOUR)));
                carSellerModel.setExshowroom_price(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_EX_SHOWROOM_PRICE)));
                carSellerModel.setRto(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_RTO)));
                carSellerModel.setPrice(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_Price)));
                carSellerModel.setCarUrl(cursor.getString(cursor.getColumnIndex(DBScript.COLUMN_IMAGE_URL)));
                carSellerModelList.add(carSellerModel);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carSellerModelList;
    }



}
