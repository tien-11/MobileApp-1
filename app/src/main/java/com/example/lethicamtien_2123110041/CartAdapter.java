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

import com.example.lethicamtien_2123110041.R;
import com.example.lethicamtien_2123110041.CartItem;

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


        holder.imgCart.setImageResource(item.getImageResId());
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("" + item.getPrice());
        holder.tvQuantity.setText(" " + item.getQuantity());

        // Xử lý tăng
        holder.btnIncrease.setOnClickListener(v -> {
            int qty = item.getQuantity() + 1;
            item.setQuantity(qty);
            holder.tvQuantity.setText(String.valueOf(qty));
        });

        // Xử lý giảm
        holder.btnDecrease.setOnClickListener(v -> {
            int qty = item.getQuantity();
            if (qty > 1) {
                qty--;
                item.setQuantity(qty);
                holder.tvQuantity.setText(String.valueOf(qty));
            }
        });
        holder.checkboxSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked); // thêm thuộc tính selected trong CartItem nếu muốn
        });
        holder.btnBuyNow.setOnClickListener(v -> {
            Toast.makeText(context, "Mua: " + item.getName(), Toast.LENGTH_SHORT).show();
            // Hoặc chuyển sang màn hình thanh toán...
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
