package com.example.dummy.aigentech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dummy.aigentech.R;
import com.example.dummy.aigentech.model.CarSellerModel;

import java.util.List;

public class SellerListAdapter extends RecyclerView.Adapter<SellerListAdapter.SellerViewHolder> {

    List<CarSellerModel> carSellerModelList;
    Context context;

    SellerListAdapter(List<CarSellerModel> carSellerModelList,Context context)
    {
        this.carSellerModelList = carSellerModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SellerViewHolder holder, int position) {
             CarSellerModel carSellerModel = carSellerModelList.get(position);
             if(carSellerModel.getCarUrl()!=null && !carSellerModel.getCarUrl().isEmpty())
             {
                 Glide.with(context).load(carSellerModel.getCarUrl()).into(holder.ivCarImage);
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
        return carSellerModelList.size();
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
        }
    }
}
