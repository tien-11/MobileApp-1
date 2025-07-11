package com.example.lethicamtien_2123110041;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private OnCartChangedListener onCartChangedListener;

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    public CartAdapter(Context context, List<CartItem> cartItems, OnCartChangedListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.onCartChangedListener = listener;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxSelect;
        TextView btnIncrease, btnDecrease;
        ImageView imgCart;
        TextView tvName, tvPrice, tvQuantity;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkboxSelect = itemView.findViewById(R.id.checkboxSelect);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            imgCart = itemView.findViewById(R.id.imgCart);
            tvName = itemView.findViewById(R.id.tvCartName);
            tvPrice = itemView.findViewById(R.id.tvCartPrice);
            tvQuantity = itemView.findViewById(R.id.tvCartQuantity);
        }
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imgCart);

        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice() + " VND");
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        holder.btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
            if (onCartChangedListener != null) onCartChangedListener.onCartChanged();
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
                if (onCartChangedListener != null) onCartChangedListener.onCartChanged();
            }
        });

        holder.checkboxSelect.setChecked(item.isSelected());
        holder.checkboxSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
            if (onCartChangedListener != null) onCartChangedListener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
