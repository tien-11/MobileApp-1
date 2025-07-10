package com.example.lethicamtien_2123110041;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, imgDetailIcon, imgAddIcon;
        TextView tvName, tvDesc;


        public ProductViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            imgDetailIcon = itemView.findViewById(R.id.imgDetailIcon);
            imgAddIcon = itemView.findViewById(R.id.imgAddIcon);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product p = productList.get(position);

        holder.tvName.setText(p.getName());
        holder.tvDesc.setText("Giá: " + p.getPrice() + " VND");

        // ✅ Load ảnh sản phẩm
        Glide.with(context)
                .load(p.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imgProduct);

        // ✅ Gán sự kiện icon chi tiết
        holder.imgDetailIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("name", p.getName());
            intent.putExtra("description", p.getDescription());
            intent.putExtra("price", p.getPrice());
            intent.putExtra("imageUrl", p.getImageUrl());
            context.startActivity(intent);
        });

        // ✅ Gán sự kiện icon thêm vào giỏ
        holder.imgAddIcon.setOnClickListener(v -> {
            CartItem item = new CartItem(p.getName(), p.getImageUrl(), p.getPrice(), 1);
            CartManager.addToCart(item);
            Toast.makeText(context, "Đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();
        });
    }

    public void updateData(List<Product> newList) {
        productList.clear();
        productList.addAll(newList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
