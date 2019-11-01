package com.example.dummy.aigentech.activity;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.fragment.MessageDialogFragment;
import com.example.dummy.aigentech.model.CarSellerModel;

public class ViewAdActivity extends AppCompatActivity {

    ImageView ivSavedPhoto;
    TextView tvSellerName,tvModelName,tvManuName,tvMileage,tvColour,tvExshowroomPrice,tvRto,tvPrice;
    CarSellerModel carSellerModel;
    Button btnCreateAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ad);
        initView();
        if(getIntent().getExtras()!=null)
        {
            carSellerModel = (CarSellerModel) getIntent().getExtras().get("SELLER_KEY");
        }
        setupView();
    }

    public void initView()
    {
        ivSavedPhoto = (ImageView) findViewById(R.id.ivSavedPhoto);
        tvSellerName = (TextView) findViewById(R.id.tvSellerName);
        tvModelName = (TextView) findViewById(R.id.tvModelName);
        tvManuName = (TextView) findViewById(R.id.tvManuName);
        tvMileage = (TextView) findViewById(R.id.tvMileage);
        tvColour = (TextView) findViewById(R.id.tvColour);
        tvExshowroomPrice =  (TextView) findViewById(R.id.tvExshowroomPrice);
        tvRto = (TextView) findViewById(R.id.tvRto);
        tvPrice=(TextView) findViewById(R.id.tvPrice);
        btnCreateAd = (Button) findViewById(R.id.btnCreateAd);
        btnCreateAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openDialog();
            }
        });
    }

    public void openDialog()
    {
        MessageDialogFragment dialog = new MessageDialogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, MessageDialogFragment.TAG);
    }

    public void setupView()
    {
        if(carSellerModel.getSeller_name()!=null && !carSellerModel.getSeller_name().isEmpty())
        {
            tvSellerName.setText(carSellerModel.getSeller_name());
        }
        if(carSellerModel.getModel_name()!=null && !carSellerModel.getModel_name().isEmpty())
        {
            tvModelName.setText(carSellerModel.getModel_name());
        }
        if(carSellerModel.getManufacture_name()!=null && !carSellerModel.getManufacture_name().isEmpty())
        {
            tvManuName.setText(carSellerModel.getManufacture_name());
        }
        if(carSellerModel.getMileage()!=null && !carSellerModel.getMileage().isEmpty())
        {
            tvMileage.setText(carSellerModel.getMileage());
        }
        if(carSellerModel.getColour()!=null && !carSellerModel.getColour().isEmpty())
        {
            tvColour.setText(carSellerModel.getColour());
        }
        if(carSellerModel.getExshowroom_price()!=null && !carSellerModel.getExshowroom_price().isEmpty())
        {
            tvExshowroomPrice.setText(carSellerModel.getExshowroom_price());
        }
        if(carSellerModel.getRto()!=null && !carSellerModel.getRto().isEmpty())
        {
            tvRto.setText(carSellerModel.getRto());
        }
        if(carSellerModel.getPrice()!=null && !carSellerModel.getPrice().isEmpty())
        {
            tvPrice.setText(carSellerModel.getPrice());
        }
        if(carSellerModel.getCarUrl()!=null && !carSellerModel.getCarUrl().isEmpty())
        {
            if(carSellerModel.getCarUrl().equalsIgnoreCase("CM1")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.carimg1)).into(ivSavedPhoto);
            }else if(carSellerModel.getCarUrl().equalsIgnoreCase("CM2")){
                Glide.with(this).load(getResources().getDrawable(R.drawable.carimg2)).into(ivSavedPhoto);
            }else {
                Glide.with(this).load(carSellerModel.getCarUrl()).into(ivSavedPhoto);
            }
        }
    }
}
