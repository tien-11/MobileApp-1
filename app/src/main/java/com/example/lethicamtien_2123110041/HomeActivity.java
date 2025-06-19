package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    ListView listNganh;

    String tutorials[] = {
            "GHẾ SOFA",
            "TỦ 2 NGĂN",
            "TỦ KÍNH",
            "TỦ MINI",
            "BÀN ĂN",
            "BÀN GỖ",
            "ĐÈN NGỦ",
            "KỆ SÁCH",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listNganh = findViewById(R.id.list);
        ArrayAdapter<String> arr;

        arr = new ArrayAdapter<String>(this, R.layout.item_home, tutorials);
        listNganh.setAdapter(arr);

        Button btnHome = findViewById(R.id.btnBack);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(it);
            }
        });
    }
}