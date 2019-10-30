package com.example.dummy.aigentech.activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.common.DBHelper;
import com.example.dummy.aigentech.common.DBScript;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

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

                break;
        }

        return false;
    }

    public void initView()
    {
        insertDataInDb();
        recyclerView = findViewById(R.id.rvSellerName);

    }

    public void insertDataInDb()
    {
        DBHelper dbHelper = DBHelper.getInstance(this);
        for(int i=0;i<3;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put(DBScript.COLUMN_IMAGE_URL,R.drawable.carimg1);
        }
    }
}
