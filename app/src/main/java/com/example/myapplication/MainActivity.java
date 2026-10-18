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

        // Các nút bài 2 - bài 6 (sẽ được mở dần khi thực hiện tiếp)
        findViewById(R.id.btnCase02).setOnClickListener(v -> 
            showPendingToast("Bài 2: ListView & ArrayList"));
        findViewById(R.id.btnCase03).setOnClickListener(v -> 
            showPendingToast("Bài 3: ArrayList & ListView với Object"));
        findViewById(R.id.btnCase04).setOnClickListener(v -> 
            showPendingToast("Bài 4: Custom Adapter cho ListView"));
        findViewById(R.id.btnCase05).setOnClickListener(v -> 
            showPendingToast("Bài 5: GridView & Spinner"));
        findViewById(R.id.btnCase06).setOnClickListener(v -> 
            showPendingToast("Bài 6: RecyclerView"));

        // Ghi chú: Nếu bạn muốn tự động mở thẳng màn hình Bài 1 mỗi khi Run từ Android Studio,
        // bạn chỉ cần bỏ comment dòng bên dưới:
        // startActivity(new Intent(MainActivity.this, Case01Activity.class));
    }

    private void showPendingToast(String taskName) {
        Toast.makeText(this, taskName + " đang chờ bạn xác nhận để triển khai!", Toast.LENGTH_SHORT).show();
    }
}