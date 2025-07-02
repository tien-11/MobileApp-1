package com.example.lethicamtien_2123110041;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        // Dữ liệu mẫu
        productList = new ArrayList<>();
        productList.add(new Product(
                "Rest + Black sọc caro",
                "Sọc caro mang phong cách cổ điển kết hợp hiện đại.",
                "5.000.000 VND",
                R.drawable.sp1,
                "Đây là mẫu thiết kế mang đậm chất Gothic cổ điển kết hợp với đường may hiện đại, tạo nên vẻ đẹp huyền bí và cuốn hút."
        ));

        productList.add(new Product(
                "White + Black caro",
                "Bộ váy Gothic Lolita đối lập trắng - đen đầy cá tính.",
                "4.800.000 VND",
                R.drawable.sp2,
                "Chiếc váy này lấy cảm hứng từ phong cách Gothic cổ điển, kết hợp hoạ tiết caro trắng đen tạo điểm nhấn mạnh mẽ."
        ));

        productList.add(new Product(
                "Ivory-white + Black",
                "Áo khoác phối ren và bèo nhẹ nhàng nữ tính.",
                "3.500.000 VND",
                R.drawable.sp3,
                "Thiết kế phối màu tinh tế giữa trắng ngà và đen, mang lại vẻ thanh lịch nhưng không kém phần nổi bật."
        ));

        productList.add(new Product(
                "White + Red",
                "Thiết kế lấy cảm hứng từ búp bê cổ điển.",
                "5.200.000 VND",
                R.drawable.sp4,
                "Váy Gothic đỏ trắng nổi bật với chi tiết nơ lớn, ren và lớp váy nhiều tầng tạo vẻ ngoài bồng bềnh."
        ));

        productList.add(new Product(
                "White + Black",
                "Chiếc váy mang phong cách quý tộc phương Tây.",
                "5.000.000 VND",
                R.drawable.sp7,
                "Bộ váy kết hợp ren, lưới và chi tiết nơ thủ công phù hợp với những buổi tiệc hoặc cosplay Gothic sang trọng."
        ));

        productList.add(new Product(
                "White + Brown",
                "Sự pha trộn giữa ren, lụa và nơ tay phồng.",
                "4.300.000 VND",
                R.drawable.sp6,
                "Thiết kế nhẹ nhàng với gam màu trắng nâu tạo nên sự cổ điển và ấm áp, phù hợp đi dạo hoặc chụp ảnh."
        ));

        // Thêm tùy ý

        // Khởi tạo Adapter
        adapter = new ProductAdapter(this, productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
