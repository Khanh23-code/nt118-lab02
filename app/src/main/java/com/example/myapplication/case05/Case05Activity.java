package com.example.myapplication.case05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Case05Activity extends AppCompatActivity {

    private EditText edtDishName;
    private Spinner spnThumbnail;
    private CheckBox cbPromotion;
    private Button btnAddDish;
    private GridView gvDishes;

    private List<Thumbnail> thumbnailList;
    private ThumbnailAdapter thumbnailAdapter;

    private ArrayList<Dish> dishes;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_case05);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtDishName = findViewById(R.id.edtDishName);
        spnThumbnail = findViewById(R.id.spnThumbnail);
        cbPromotion = findViewById(R.id.cbPromotion);
        btnAddDish = findViewById(R.id.btnAddDish);
        gvDishes = findViewById(R.id.gvDishes);

        thumbnailList = Arrays.asList(Thumbnail.values());
        thumbnailAdapter = new ThumbnailAdapter(this, R.layout.item_selected_thumbnail, thumbnailList);
        spnThumbnail.setAdapter(thumbnailAdapter);

        dishes = new ArrayList<>();
        dishes.add(new Dish("Dish 01", Thumbnail.Thumbnail1, true));
        dishes.add(new Dish("Dish 02", Thumbnail.Thumbnail2, false));

        dishAdapter = new DishAdapter(this, R.layout.item_dish, dishes);
        gvDishes.setAdapter(dishAdapter);

        btnAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtDishName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(Case05Activity.this, "Vui lòng nhập tên món ăn!", Toast.LENGTH_SHORT).show();
                    edtDishName.requestFocus();
                    return;
                }

                Thumbnail selectedThumbnail = (Thumbnail) spnThumbnail.getSelectedItem();
                boolean isPromotion = cbPromotion.isChecked();

                Dish dish = new Dish(name, selectedThumbnail, isPromotion);
                dishes.add(dish);

                dishAdapter.notifyDataSetChanged();

                edtDishName.setText("");
                spnThumbnail.setSelection(0);
                cbPromotion.setChecked(false);
                edtDishName.requestFocus();
            }
        });

        gvDishes.setOnItemClickListener((parent, view, position, id) -> {
            Dish d = dishes.get(position);
            String promoText = d.isPromotion() ? " (Có khuyến mãi ⭐)" : "";
            Toast.makeText(Case05Activity.this, d.getName() + promoText, Toast.LENGTH_SHORT).show();
        });

        gvDishes.setOnItemLongClickListener((parent, view, position, id) -> {
            Dish removed = dishes.remove(position);
            dishAdapter.notifyDataSetChanged();
            Toast.makeText(Case05Activity.this, "Đã xóa món: " + removed.getName(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
