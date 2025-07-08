package com.example.lethicamtien_2123110041;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkboxSelect;
        Button btnBuyNow;
        TextView btnIncrease, btnDecrease;
        ImageView imgCart;
        TextView tvName, tvPrice, tvQuantity;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkboxSelect = itemView.findViewById(R.id.checkboxSelect);
            btnBuyNow = itemView.findViewById(R.id.btnBuyNow);
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

        // ✅ Load ảnh từ URL bằng Glide
        Glide.with(context)
                .load(item.getImageUrl())
                .placeholder(R.drawable.placeholder_image) // ảnh tạm
                .into(holder.imgCart);

        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        // Tăng số lượng
        holder.btnIncrease.setOnClickListener(v -> {
            int qty = item.getQuantity() + 1;
            item.setQuantity(qty);
            holder.tvQuantity.setText(String.valueOf(qty));
        });

        // Giảm số lượng
        holder.btnDecrease.setOnClickListener(v -> {
            int qty = item.getQuantity();
            if (qty > 1) {
                qty--;
                item.setQuantity(qty);
                holder.tvQuantity.setText(String.valueOf(qty));
            }
        });

        // Checkbox chọn mua
        holder.checkboxSelect.setChecked(item.isSelected());
        holder.checkboxSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
        });

        // Nút Mua ngay
        holder.btnBuyNow.setOnClickListener(v -> {
            Toast.makeText(context, "Mua: " + item.getName(), Toast.LENGTH_SHORT).show();
            // Chuyển qua CheckoutActivity nếu muốn
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
