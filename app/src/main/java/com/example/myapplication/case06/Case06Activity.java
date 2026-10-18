package com.example.myapplication.case06;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Case06Activity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtName;
    private CheckBox cbIsManager;
    private Button btnAdd;
    private RecyclerView rvEmployees;

    private ArrayList<Employee> employees;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case06);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        cbIsManager = findViewById(R.id.cbIsManager);
        btnAdd = findViewById(R.id.btnAdd);
        rvEmployees = findViewById(R.id.rvEmployees);

        employees = new ArrayList<>();
        employees.add(new Employee("1", "Nhân", true));
        employees.add(new Employee("2", "Vân", false));
        employees.add(new Employee("3", "An", false));
        employees.add(new Employee("4", "Bình", true));

        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter(this, employees);
        rvEmployees.setAdapter(adapter);

        adapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position >= 0 && position < employees.size()) {
                    Employee emp = employees.get(position);
                    String role = emp.isManager() ? "Manager" : "Staff";
                    Toast.makeText(Case06Activity.this, emp.getFullName() + " (" + role + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(int position) {
                if (position >= 0 && position < employees.size()) {
                    Employee removed = employees.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, employees.size() - position);
                    Toast.makeText(Case06Activity.this, "Đã xóa: " + removed.getFullName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                boolean isManager = cbIsManager.isChecked();

                if (id.isEmpty()) {
                    Toast.makeText(Case06Activity.this, "Vui lòng nhập ID!", Toast.LENGTH_SHORT).show();
                    edtId.requestFocus();
                    return;
                }

                if (name.isEmpty()) {
                    Toast.makeText(Case06Activity.this, "Vui lòng nhập Full Name!", Toast.LENGTH_SHORT).show();
                    edtName.requestFocus();
                    return;
                }

                Employee employee = new Employee(id, name, isManager);
                employees.add(employee);

                adapter.notifyItemInserted(employees.size() - 1);
                rvEmployees.scrollToPosition(employees.size() - 1);

                edtId.setText("");
                edtName.setText("");
                cbIsManager.setChecked(false);
                edtId.requestFocus();

                Toast.makeText(Case06Activity.this, "Đã thêm thành công: " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
