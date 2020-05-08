package com.example.retrofit2.pk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit2.R;
import com.example.retrofit2.pk.model.ProductData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapterRv extends RecyclerView.Adapter<ProductAdapterRv.ProductViewHolder> {
    private List<ProductData> productDataList;
    private Context context;

    public ProductAdapterRv(List<ProductData> productDataList, Context context) {
        this.productDataList = productDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Picasso.with(context).load(productDataList.get(position).getHinh()).into(holder.ivHinh);
        holder.tvTen.setText(productDataList.get(position).getTen());
        holder.tvGia.setText(productDataList.get(position).getGia());
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinh;
        TextView tvTen, tvGia;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinh = itemView.findViewById(R.id.ivHinhSanPham);
            tvTen = itemView.findViewById(R.id.tvTenSanPham);
            tvGia = itemView.findViewById(R.id.tvGiaSanPham);
        }
    }
}
