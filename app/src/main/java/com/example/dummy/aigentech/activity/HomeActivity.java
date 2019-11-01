package com.example.dummy.aigentech.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.adapter.SellerListAdapter;
import com.example.dummy.aigentech.common.DBHelper;
import com.example.dummy.aigentech.common.DBScript;
import com.example.dummy.aigentech.model.CarSellerModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    List<CarSellerModel> carSellerModelList;
    SellerListAdapter sellerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.createAd:
                Intent nextIntent = new Intent(this,CreateAdActivity.class);
                startActivityForResult(nextIntent,45);
                break;
        }

        return false;
    }

    public void initView()
    {
        insertDataInDb();
        recyclerView = findViewById(R.id.rvSellerName);
        getDataFromDb();
    }

    public void insertDataInDb()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if(pref.getBoolean("insert",false)) {
            dbHelper = DBHelper.getInstance(this);
            ContentValues cv = new ContentValues();
            cv.put(DBScript.COLUMN_IMAGE_URL, "CM1");
            cv.put(DBScript.COLUMN_SELLER_NAME, "RT Car Showroom");
            cv.put(DBScript.COLUMN_MODEL_NAME, "Maruti Alto");
            cv.put(DBScript.COLUMN_MANUFACTURE_NAME, "Maruti");
            cv.put(DBScript.COLUMN_COLOUR, "BLUE");
            cv.put(DBScript.COLUMN_Price, "1,20,000");
            cv.put(DBScript.COLUMN_EX_SHOWROOM_PRICE, "2,20,000");
            cv.put(DBScript.COLUMN_RTO, "Mumbai");
            cv.put(DBScript.COLUMN_MILEAGE, "22 km/l");
            dbHelper.insertCarEntry(cv);

            cv.put(DBScript.COLUMN_IMAGE_URL, "CM2");
            cv.put(DBScript.COLUMN_SELLER_NAME, "RT Car Showroom");
            cv.put(DBScript.COLUMN_MODEL_NAME, "Maruti Alto LX1");
            cv.put(DBScript.COLUMN_MANUFACTURE_NAME, "Maruti");
            cv.put(DBScript.COLUMN_COLOUR, "RED");
            cv.put(DBScript.COLUMN_Price, "2,20,000");
            cv.put(DBScript.COLUMN_EX_SHOWROOM_PRICE, "3,20,000");
            cv.put(DBScript.COLUMN_RTO, "Mumbai");
            cv.put(DBScript.COLUMN_MILEAGE, "22 km/l");
            dbHelper.insertCarEntry(cv);
            // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("insert", true);
            editor.commit();
        }
    }

    public void getDataFromDb()
    {
        dbHelper = DBHelper.getInstance(this);
        carSellerModelList = dbHelper.getAllModel();
        sellerListAdapter=new SellerListAdapter(carSellerModelList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(sellerListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 45){
            updateDb();
        }
    }

    public void updateDb()
    {
        dbHelper = DBHelper.getInstance(this);
        carSellerModelList.clear();
        carSellerModelList = dbHelper.getAllModel();
        sellerListAdapter.setCarSellerModelList(carSellerModelList);
        sellerListAdapter.notifyDataSetChanged();
    }
}
