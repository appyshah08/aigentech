package com.example.dummy.aigentech.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.activity.ViewAdActivity;
import com.example.dummy.aigentech.model.CarSellerModel;

import java.util.List;

public class SellerListAdapter extends RecyclerView.Adapter<SellerListAdapter.SellerViewHolder> {

    List<CarSellerModel> carSellerModelList;
    Context context;

    public SellerListAdapter(List<CarSellerModel> carSellerModelList,Context context)
    {
        this.carSellerModelList = carSellerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.seller_item_view, parent, false);
        SellerViewHolder viewHolder = new SellerViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SellerViewHolder holder, int position) {
             CarSellerModel carSellerModel = carSellerModelList.get(position);
             if(carSellerModel.getCarUrl()!=null && !carSellerModel.getCarUrl().isEmpty())
             {
                 if(carSellerModel.getCarUrl().equalsIgnoreCase("CM1")){
                     Glide.with(context).load(context.getResources().getDrawable(R.drawable.carimg1)).listener(new RequestListener<Drawable>() {
                         @Override
                         public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                             return false;
                         }

                         @Override
                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             return false;
                         }
                     }).into(holder.ivCarImage);
                 }else if(carSellerModel.getCarUrl().equalsIgnoreCase("CM2")){
                     Glide.with(context).load(context.getResources().getDrawable(R.drawable.carimg2)).listener(new RequestListener<Drawable>() {
                         @Override
                         public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                             return false;
                         }

                         @Override
                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             return false;
                         }
                     }).into(holder.ivCarImage);
                 }else {
                     Glide.with(context).load(carSellerModel.getCarUrl()).listener(new RequestListener<Drawable>() {
                         @Override
                         public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                             return false;
                         }

                         @Override
                         public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             return false;
                         }
                     }).into(holder.ivCarImage);
                 }
             }
             if(carSellerModel.getSeller_name()!=null && !carSellerModel.getSeller_name().isEmpty())
             {
                 holder.tvSellerName.setText(carSellerModel.getSeller_name());
             }
             if(carSellerModel.getPrice()!=null && !carSellerModel.getPrice().isEmpty())
             {
                 holder.tvCarPrice.setText(carSellerModel.getPrice());
             }
             if(carSellerModel.getModel_name()!=null && !carSellerModel.getModel_name().isEmpty())
             {
                 holder.tvCarName.setText(carSellerModel.getModel_name());
             }
    }

    @Override
    public int getItemCount() {
        return getCarSellerModelList().size();
    }

    public List<CarSellerModel> getCarSellerModelList() {
        return carSellerModelList;
    }

    public void setCarSellerModelList(List<CarSellerModel> carSellerModelList) {
        this.carSellerModelList = carSellerModelList;
    }

    class SellerViewHolder extends RecyclerView.ViewHolder{
        CardView cvMain;
        ImageView ivCarImage;
        TextView tvCarName;
        TextView tvCarPrice;
        TextView tvSellerName;

        public SellerViewHolder(View itemView) {
            super(itemView);
            cvMain = itemView.findViewById(R.id.cvMain);
            ivCarImage = itemView.findViewById(R.id.ivCarImage);
            tvCarName = itemView.findViewById(R.id.tvCarName);
            tvCarPrice = itemView.findViewById(R.id.tvCarPrice);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            cvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextIntent = new Intent(context, ViewAdActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("SELLER_KEY",carSellerModelList.get(getAdapterPosition()));
                    nextIntent.putExtras(bundle);
                    context.startActivity(nextIntent);
                }
            });
        }
    }
}
