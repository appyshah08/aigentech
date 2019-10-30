package com.example.dummy.aigentech.common;

/**
 * Created by Neeta on 10/29/2019.
 */

public class DBScript {

    public static final String TABLE_NAME = "car_record";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SELLER_NAME = "seller_name";
    public static final String COLUMN_MODEL_NAME = "model_name";
    public static final String COLUMN_MANUFACTURE_NAME = "manufacture_name";
    public static final String COLUMN_MILEAGE = "mileage";
    public static final String COLUMN_COLOUR = "colour";
    public static final String COLUMN_EX_SHOWROOM_PRICE = "exshowroom_price";
    public static final String COLUMN_RTO = "rto";
    public static final String COLUMN_Price = "price";
    public static final String COLUMN_IMAGE_URL = "image_url";




    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SELLER_NAME + " TEXT,"
                    + COLUMN_MODEL_NAME + " TEXT,"
                    + COLUMN_MANUFACTURE_NAME + " TEXT,"
                    + COLUMN_MILEAGE + " TEXT,"
                    + COLUMN_COLOUR + " TEXT,"
                    + COLUMN_EX_SHOWROOM_PRICE + " TEXT,"
                    + COLUMN_RTO + " TEXT,"
                    + COLUMN_Price + " TEXT,"
                    + COLUMN_IMAGE_URL + " TEXT"
                    + ")";
}
