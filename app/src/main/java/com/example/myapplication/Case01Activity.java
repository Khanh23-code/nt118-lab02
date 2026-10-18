package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Case01Activity extends AppCompatActivity {

    private TextView tvSelection;
    private ListView lvPerson;
    // Mảng dữ liệu định sẵn theo tài liệu Lab02
    private final String[] arr = {"Teo", "Ty", "Bin", "Bo"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case01);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Khởi tạo đối tượng listview và textview
        tvSelection = findViewById(R.id.tvSelection);
        lvPerson = findViewById(R.id.lvPerson);

        // 2 & 3. Xây dựng adapter với mảng dữ liệu định sẵn
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);

        // 4. SetAdapter cho listview
        lvPerson.setAdapter(adapter);

        // 5. Xử lý sự kiện click trên ListView
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị position và value lên TextView theo định dạng của tài liệu
                String message = "position :" + position + " ; value =" + arr[position];
                tvSelection.setText(message);
                Toast.makeText(Case01Activity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
