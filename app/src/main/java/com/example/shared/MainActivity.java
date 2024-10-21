package com.example.shared;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtMSV, edtAddress;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtName = findViewById(R.id.edt1);
        edtMSV = findViewById(R.id.edt2);
        edtAddress = findViewById(R.id.edt3);
        btnSave = findViewById(R.id.btnluu);

        // Gọi hàm loadData để lấy dữ liệu từ SharedPreferences khi mở Activity
        loadData();

        // Lưu dữ liệu khi nhấn nút "Lưu"
        btnSave.setOnClickListener(v -> saveData());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Hàm lưu dữ liệu vào SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Lưu tên, mã sinh viên và địa chỉ
        editor.putString("userName", edtName.getText().toString());
        editor.putString("userMSV", edtMSV.getText().toString());
        editor.putString("userAddress", edtAddress.getText().toString());

        // Áp dụng thay đổi
        editor.apply();
        
        // Hiển thị Toast thông báo lưu thành công
        Toast.makeText(MainActivity.this, "Lưu thông tin thành công!", Toast.LENGTH_SHORT).show();
    }

    // Hàm lấy dữ liệu từ SharedPreferences
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        // Lấy tên, mã sinh viên và địa chỉ, nếu không có thì đặt giá trị mặc định
        String savedName = sharedPreferences.getString("userName", "");
        String savedMSV = sharedPreferences.getString("userMSV", "");
        String savedAddress = sharedPreferences.getString("userAddress", "");

        // Hiển thị dữ liệu đã lưu lên EditText
        edtName.setText(savedName);
        edtMSV.setText(savedMSV);
        edtAddress.setText(savedAddress);
    }
}