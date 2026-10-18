package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Case02Activity extends AppCompatActivity {

    private EditText etName;
    private Button btnSubmit;
    private TextView tvSelection;
    private ListView lvPerson;

    private ArrayList<String> names;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case02);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvSelection = findViewById(R.id.tvSelection);
        lvPerson = findViewById(R.id.lvPerson);

        names = new ArrayList<>();
        names.add("teo");
        names.add("ty");
        names.add("bin");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lvPerson.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = etName.getText().toString().trim();
                if (inputName.isEmpty()) {
                    Toast.makeText(Case02Activity.this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
                    return;
                }

                names.add(inputName);
                adapter.notifyDataSetChanged();
                etName.setText("");
            }
        });

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = names.get(position);
                String info = "position: " + position + " ; value = " + selectedItem;
                tvSelection.setText(info);
            }
        });

        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String deletedItem = names.get(position);

                names.remove(position);

                adapter.notifyDataSetChanged();

                tvSelection.setText("Đã xóa phần tử: " + deletedItem);

                return true;
            }
        });
    }
}
