package com.example.lethicamtien_2123110041;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class SuggestionAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final List<Product> productList;

    public SuggestionAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false);
        }

        Product product = productList.get(position);

        TextView tvName = convertView.findViewById(R.id.tvSuggestionName);
        ImageView imgProduct = convertView.findViewById(R.id.imgSuggestion);

        tvName.setText(product.getName());

        Glide.with(context)
                .load(product.getImageUrl())
                .placeholder(R.drawable.placeholder_image) // bạn có thể thêm ảnh placeholder nếu muốn
                .into(imgProduct);

        return convertView;
    }
}
