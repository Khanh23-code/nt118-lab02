package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nút mở Bài 1
        Button btnCase01 = findViewById(R.id.btnCase01);
        btnCase01.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Case01Activity.class);
            startActivity(intent);
        });

        // Nút mở Bài 2
        Button btnCase02 = findViewById(R.id.btnCase02);
        btnCase02.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Case02Activity.class);
            startActivity(intent);
        });
        // Nút mở Bài 3
        Button btnCase03 = findViewById(R.id.btnCase03);
        btnCase03.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.myapplication.case03.Case03Activity.class);
            startActivity(intent);
        });
        // Nút mở Bài 4
        Button btnCase04 = findViewById(R.id.btnCase04);
        btnCase04.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.myapplication.case04.Case04Activity.class);
            startActivity(intent);
        });
        // Nút mở Bài 5
        Button btnCase05 = findViewById(R.id.btnCase05);
        btnCase05.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.myapplication.case05.Case05Activity.class);
            startActivity(intent);
        });
        // Nút mở Bài 6
        Button btnCase06 = findViewById(R.id.btnCase06);
        btnCase06.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.example.myapplication.case06.Case06Activity.class);
            startActivity(intent);
        });

        // Ghi chú: Nếu bạn muốn tự động mở thẳng màn hình Bài 1 mỗi khi Run từ Android Studio,
        // bạn chỉ cần bỏ comment dòng bên dưới:
        // startActivity(new Intent(MainActivity.this, Case01Activity.class));
    }

    private void showPendingToast(String taskName) {
        Toast.makeText(this, taskName + " đang chờ bạn xác nhận để triển khai!", Toast.LENGTH_SHORT).show();
    }
}