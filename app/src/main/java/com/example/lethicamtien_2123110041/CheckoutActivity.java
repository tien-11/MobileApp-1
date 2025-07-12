package com.example.lethicamtien_2123110041;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    EditText edtAddress;
    Button btnOrder;
    TextView txtTotal;
    RadioGroup rgShipping, rgPayment;
    LinearLayout layoutProducts; // Chứa nhiều sản phẩm khi mua từ giỏ hàng

    Product product; // Cho mua ngay 1 sp
    ArrayList<CartItem> selectedItems; // Cho nhiều sp từ giỏ hàng

    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Ánh xạ view
        edtAddress = findViewById(R.id.edtAddress);
        btnOrder = findViewById(R.id.btnOrder);
        txtTotal = findViewById(R.id.txtTotal);
        rgShipping = findViewById(R.id.rgShipping);
        rgPayment = findViewById(R.id.rgPayment);
        layoutProducts = findViewById(R.id.layoutProducts); // LinearLayout chứa sản phẩm

        // Kiểm tra: mua 1 sản phẩm hay nhiều sản phẩm
        product = (Product) getIntent().getSerializableExtra("product");
        selectedItems = (ArrayList<CartItem>) getIntent().getSerializableExtra("cart_items");

        if (product != null) {
            // 💥 XÓA selectedItems để không hiển thị nhầm
            selectedItems = null;
            showSingleProduct();
        } else if (selectedItems != null && !selectedItems.isEmpty()) {
            showMultipleProducts();
        }


        btnOrder.setOnClickListener(v -> {
            String address = edtAddress.getText().toString().trim();
            int shippingId = rgShipping.getCheckedRadioButtonId();
            int paymentId = rgPayment.getCheckedRadioButtonId();

            if (address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập địa chỉ giao hàng!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (shippingId == -1 || paymentId == -1) {
                Toast.makeText(this, "Vui lòng chọn phương thức vận chuyển và thanh toán!", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton shipping = findViewById(shippingId);
            RadioButton payment = findViewById(paymentId);

            StringBuilder summary = new StringBuilder("✅ Đặt hàng thành công\n");

            if (product != null) {
                String quantityStr = ((EditText) findViewById(R.id.edtQuantity)).getText().toString().trim();
                int quantity = quantityStr.isEmpty() ? 1 : Integer.parseInt(quantityStr);

                summary.append("Tên: ").append(product.getName()).append("\n");
                summary.append("Số lượng: ").append(quantity).append("\n");
                summary.append("Tổng: ").append(formatPrice(product.getPrice() * quantity)).append("\n");
            } else if (selectedItems != null) {
                for (CartItem item : selectedItems) {
                    summary.append("Tên: ").append(item.getName())
                            .append(" - SL: ").append(item.getQuantity())
                            .append(" - ").append(formatPrice(item.getPrice() * item.getQuantity()))
                            .append("\n");
                }
                summary.append("Tổng: ").append(formatPrice(totalPrice)).append("\n");
            }

            summary.append("Giao đến: ").append(address).append("\n");
            summary.append("Vận chuyển: ").append(shipping.getText()).append("\n");
            summary.append("Thanh toán: ").append(payment.getText());

            Toast.makeText(this, summary.toString(), Toast.LENGTH_LONG).show();
        });
    }

    private void showSingleProduct() {
        layoutProducts.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.item_checkout_single, null);

        ImageView img = view.findViewById(R.id.imgProduct);
        TextView name = view.findViewById(R.id.txtProductName);
        TextView price = view.findViewById(R.id.txtPrice);
        EditText edtQuantity = view.findViewById(R.id.edtQuantity);

        name.setText(product.getName());
        price.setText("Giá: " + formatPrice(product.getPrice()));
        edtQuantity.setText("1");

        Glide.with(this).load(product.getImageUrl()).into(img);
        layoutProducts.addView(view);

        totalPrice = product.getPrice();
        txtTotal.setText("Tổng: " + formatPrice(totalPrice));

        edtQuantity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                try {
                    int quantity = Integer.parseInt(s.toString());
                    totalPrice = product.getPrice() * quantity;
                } catch (Exception e) {
                    totalPrice = product.getPrice();
                }
                txtTotal.setText("Tổng: " + formatPrice(totalPrice));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }


    private void showMultipleProducts() {
        layoutProducts.removeAllViews();
        totalPrice = 0;

        for (CartItem item : selectedItems) {
            View view = getLayoutInflater().inflate(R.layout.item_checkout_cart, null);

            ImageView img = view.findViewById(R.id.imgProduct);
            TextView name = view.findViewById(R.id.txtProductName);
            TextView price = view.findViewById(R.id.txtPrice);
            TextView qty = view.findViewById(R.id.txtQuantity); // Bây giờ đúng ID

            name.setText(item.getName());
            price.setText("Giá: " + formatPrice(item.getPrice()));
            qty.setText("Số lượng: " + item.getQuantity());

            Glide.with(this).load(item.getImageUrl()).into(img);

            totalPrice += item.getPrice() * item.getQuantity();
            layoutProducts.addView(view);
        }

        txtTotal.setText("Tổng: " + formatPrice(totalPrice));
    }

    private String formatPrice(int value) {
        return String.format("%,d", value).replace(",", ".") + "đ";
    }

}
