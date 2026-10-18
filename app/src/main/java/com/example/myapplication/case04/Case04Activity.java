package com.example.myapplication.case04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Case04Activity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtName;
    private CheckBox cbIsManager;
    private Button btnAdd;
    private ListView lvEmployees;

    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case04);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        cbIsManager = findViewById(R.id.cbIsManager);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployees = findViewById(R.id.lvEmployees);

        employees = new ArrayList<>();
        employees.add(new Employee("1", "Nhân", true));
        employees.add(new Employee("2", "Vân", false));
        employees.add(new Employee("3", "An", false));
        employees.add(new Employee("4", "Bình", true));

        adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployees.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                boolean isManager = cbIsManager.isChecked();

                if (id.isEmpty()) {
                    Toast.makeText(Case04Activity.this, "Vui lòng nhập ID!", Toast.LENGTH_SHORT).show();
                    edtId.requestFocus();
                    return;
                }

                if (name.isEmpty()) {
                    Toast.makeText(Case04Activity.this, "Vui lòng nhập Full Name!", Toast.LENGTH_SHORT).show();
                    edtName.requestFocus();
                    return;
                }

                Employee employee = new Employee(id, name, isManager);
                employees.add(employee);

                adapter.notifyDataSetChanged();

                edtId.setText("");
                edtName.setText("");
                cbIsManager.setChecked(false);
                edtId.requestFocus();

                Toast.makeText(Case04Activity.this, "Đã thêm thành công: " + name, Toast.LENGTH_SHORT).show();
            }
        });

        lvEmployees.setOnItemClickListener((parent, view, position, id) -> {
            Employee emp = employees.get(position);
            String role = emp.isManager() ? "Manager" : "Staff";
            Toast.makeText(Case04Activity.this, emp.getFullName() + " (" + role + ")", Toast.LENGTH_SHORT).show();
        });

        lvEmployees.setOnItemLongClickListener((parent, view, position, id) -> {
            Employee removed = employees.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(Case04Activity.this, "Đã xóa: " + removed.getFullName(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
