package com.example.dummy.aigentech.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.common.CommonUtils;
import com.example.dummy.aigentech.common.DBHelper;
import com.example.dummy.aigentech.common.DBScript;
import com.example.dummy.aigentech.common.ImageCompression;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateAdActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CODE = 87;
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
                checkPermission();
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
        cv.put(DBScript.COLUMN_IMAGE_URL,imageFilePath);
        DBHelper dbHelper = DBHelper.getInstance(this);
        dbHelper.insertCarEntry(cv);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Ad created successfully");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        final AlertDialog dialog = alert.create();
        dialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
               // dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(CreateAdActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(CreateAdActivity.this.getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        dialog.show();
    }

    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(
                        this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Camera and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        ActivityCompat.requestPermissions(
                                CreateAdActivity.this,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(CreateAdActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(CreateAdActivity.this.getResources().getColor(R.color.colorPrimaryDark));
                    }
                });
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
            openCameraIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CODE:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                         + + grantResults[2]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                ){
                    // Permissions are granted
                    openCameraIntent();
                }else {
                    // Permissions are denied
                    Toast.makeText(this,"Permissions denied could not open camera",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private static final int REQUEST_IMAGE_CAPTURE = 100;

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,  getPackageName()+".provider", photoFile);

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    pictureIntent.setClipData(ClipData.newRawUri("", photoURI));
                    pictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                //pictureIntent.addFlags( Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION );
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    String imageFilePath;
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            //don't compare the data to null, it will always come as  null because we are providing a file URI, so load with the imageFilePath we obtained before opening the cameraIntent
            ImageCompression.compressImage(this,imageFilePath);
            File file=new File(imageFilePath);

            if(file.exists()) {
                Glide.with(this).load(file).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).placeholder(this.getResources().getDrawable(R.drawable.carimg1)).into(ivSavedPhoto);
                // If you are using Glide.
            }
        }
    }


}
