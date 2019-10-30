package com.example.dummy.aigentech.activity;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.common.CommonUtils;
import com.example.dummy.aigentech.common.DBHelper;
import com.example.dummy.aigentech.common.DBScript;

public class CreateAdActivity extends AppCompatActivity {

    private EditText etSellerName,etModelName,etManuName,etMileage,etColour,etExshowroomPrice,etRto,etPrice;
    private ImageView ivUploadPhoto,ivSavedPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);
        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.create_ad_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_submit:
                validateValues();
                break;
        }
        return true;
    }



    public void validateValues(){
        if(!CommonUtils.isEmpty(etSellerName.getText().toString()))
        {
            if(!CommonUtils.isEmpty(etModelName.getText().toString()))
            {
                if(!CommonUtils.isEmpty(etManuName.getText().toString()))
                {
                    if(!CommonUtils.isEmpty(etMileage.getText().toString()))
                    {
                        if(!CommonUtils.isEmpty(etColour.getText().toString()))
                        {
                            if(!CommonUtils.isEmpty(etExshowroomPrice.getText().toString()))
                            {
                                if(!CommonUtils.isEmpty(etRto.getText().toString()))
                                {
                                    if(!CommonUtils.isEmpty(etPrice.getText().toString()))
                                    {
                                        //save to db
                                        savevaluestodb();
                                    }else{
                                        etRto.setError("Price is mandatory");
                                        etRto.requestFocus();
                                    }
                                }else{
                                    etRto.setError("RTO is mandatory");
                                    etRto.requestFocus();
                                }
                            }else{
                                etExshowroomPrice.setError("Ex Showroom Price is mandatory");
                                etExshowroomPrice.requestFocus();
                            }
                        }else{
                            etColour.setError("Colour is mandatory");
                            etColour.requestFocus();
                        }
                    }else{
                        etMileage.setError("Mileage is mandatory");
                        etMileage.requestFocus();
                    }
                }else{
                    etManuName.setError("Manufature Name is mandatory");
                    etManuName.requestFocus();
                }
            }else{
                etModelName.setError("Model Name is mandatory");
                etModelName.requestFocus();
            }
        }else{
            etSellerName.setError("Seller Name is mandatory");
            etSellerName.requestFocus();
        }
    }

    public void initView()
    {
        etSellerName= findViewById(R.id.etSellerName);
        etModelName = findViewById(R.id.etModelName);
        etManuName = findViewById(R.id.etManuName);
        etMileage = findViewById(R.id.etMileage);
        etColour = findViewById(R.id.etColour);
        etExshowroomPrice = findViewById(R.id.etExshowroomPrice);
        etRto = findViewById(R.id.etRto);
        etPrice = findViewById(R.id.etPrice);
        ivSavedPhoto = findViewById(R.id.ivSavedPhoto);
        ivUploadPhoto = findViewById(R.id.ivUploadPhoto);
        ivUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void savevaluestodb()
    {
        ContentValues cv = new ContentValues();
        cv.put(DBScript.COLUMN_SELLER_NAME,etSellerName.getText().toString());
        cv.put(DBScript.COLUMN_MODEL_NAME,etModelName.getText().toString());
        cv.put(DBScript.COLUMN_MANUFACTURE_NAME,etManuName.getText().toString());
        cv.put(DBScript.COLUMN_MILEAGE,etMileage.getText().toString());
        cv.put(DBScript.COLUMN_COLOUR,etColour.getText().toString());
        cv.put(DBScript.COLUMN_EX_SHOWROOM_PRICE,etExshowroomPrice.getText().toString());
        cv.put(DBScript.COLUMN_RTO,etRto.getText().toString());
        cv.put(DBScript.COLUMN_Price,etPrice.getText().toString());
        cv.put(DBScript.COLUMN_IMAGE_URL,filename);
        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.insertCarEntry(cv);

    }



}
