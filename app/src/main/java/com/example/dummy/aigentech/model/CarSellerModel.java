package com.example.dummy.aigentech.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Neeta on 10/29/2019.
 */

public class CarSellerModel implements Parcelable {
    int id;
    String seller_name;
    String model_name;
    String manufacture_name;
    String mileage;
    String colour;
    String exshowroom_price;
    String rto;
    String price;
    String carUrl;

    public CarSellerModel()
    {
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getManufacture_name() {
        return manufacture_name;
    }

    public void setManufacture_name(String manufacture_name) {
        this.manufacture_name = manufacture_name;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getExshowroom_price() {
        return exshowroom_price;
    }

    public void setExshowroom_price(String exshowroom_price) {
        this.exshowroom_price = exshowroom_price;
    }

    public String getRto() {
        return rto;
    }

    public void setRto(String rto) {
        this.rto = rto;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarUrl() {
        return carUrl;
    }

    public void setCarUrl(String carUrl) {
        this.carUrl = carUrl;
    }

    protected CarSellerModel(Parcel in) {
        id = in.readInt();
        seller_name = in.readString();
        model_name = in.readString();
        manufacture_name = in.readString();
        mileage = in.readString();
        colour = in.readString();
        exshowroom_price = in.readString();
        rto = in.readString();
        price = in.readString();
        carUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(seller_name);
        dest.writeString(model_name);
        dest.writeString(manufacture_name);
        dest.writeString(mileage);
        dest.writeString(colour);
        dest.writeString(exshowroom_price);
        dest.writeString(rto);
        dest.writeString(price);
        dest.writeString(carUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CarSellerModel> CREATOR = new Parcelable.Creator<CarSellerModel>() {
        @Override
        public CarSellerModel createFromParcel(Parcel in) {
            return new CarSellerModel(in);
        }

        @Override
        public CarSellerModel[] newArray(int size) {
            return new CarSellerModel[size];
        }
    };
}