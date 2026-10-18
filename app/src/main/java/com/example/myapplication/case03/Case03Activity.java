package com.example.myapplication.case03;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Case03Activity extends AppCompatActivity {

    private EditText etId;
    private EditText etName;
    private RadioGroup rgType;
    private RadioButton rdChinhThuc;
    private RadioButton rdThoiVu;
    private Button btnSubmit;
    private ListView lvEmployees;

    private ArrayList<Employee> employees;
    private ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case03);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        rgType = findViewById(R.id.rgType);
        rdChinhThuc = findViewById(R.id.rd_chinhthuc);
        rdThoiVu = findViewById(R.id.rd_thoivu);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvEmployees = findViewById(R.id.lvEmployees);

        employees = new ArrayList<>();
        employees.add(new EmployeeFulltime("m1", "Nguyen Thi Teo"));
        employees.add(new EmployeeParttime("m2", "Tran Van Ty"));
        employees.add(new EmployeeFulltime("m3", "Ho DO"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        lvEmployees.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee();
            }
        });

        lvEmployees.setOnItemClickListener((parent, view, position, id) -> {
            Employee emp = employees.get(position);
            Toast.makeText(Case03Activity.this, "Đã chọn: " + emp.toString(), Toast.LENGTH_SHORT).show();
        });

        lvEmployees.setOnItemLongClickListener((parent, view, position, id) -> {
            Employee removed = employees.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(Case03Activity.this, "Đã xóa: " + removed.getName(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    public void addNewEmployee() {
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Mã NV!", Toast.LENGTH_SHORT).show();
            etId.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Tên NV!", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return;
        }

        int radId = rgType.getCheckedRadioButtonId();
        Employee employee;

        if (radId == R.id.rd_chinhthuc) {
            employee = new EmployeeFulltime(id, name);
        } else {
            employee = new EmployeeParttime(id, name);
        }

        employees.add(employee);

        adapter.notifyDataSetChanged();

        etId.setText("");
        etName.setText("");
        rdChinhThuc.setChecked(true);
        etId.requestFocus();
    }
}
