package com.example.lethicamtien_2123110041;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;

public class LoginActivity extends AppCompatActivity {

    EditText objPhone, objPass;
    Button btnNextPage, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ đúng cách
        objPhone     = findViewById(R.id.editTextUsername);
        objPass      = findViewById(R.id.editTextPassword);
        btnNextPage  = findViewById(R.id.btnLogin);
        btnRegister  = findViewById(R.id.btnRegister);

        // Nhận số điện thoại sau đăng ký (nếu có)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("phone")) {
            String registeredPhone = intent.getStringExtra("phone");
            objPhone.setText(registeredPhone); // Tự động điền
        }

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPhone = objPhone.getText().toString().trim();
                String inputPass  = objPass.getText().toString().trim();

                if (inputPhone.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences pref = getSharedPreferences("UserData", MODE_PRIVATE);
                String savedPhone = pref.getString("phone", "");
                String savedPass  = pref.getString("password", "");

                if (inputPhone.equals(savedPhone) && inputPass.equals(savedPass)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(it);
                    finish(); // ✅ Đảm bảo không quay lại login
                } else {
                    Toast.makeText(getApplicationContext(), "Sai số điện thoại hoặc mật khẩu!", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}