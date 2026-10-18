package com.example.myapplication.case05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Context context;
    private List<Dish> dishes;

    public DishAdapter(@NonNull Context context, int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dishes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dish, parent, false);
        }

        Dish dish = getItem(position);

        ImageView ivThumbnail = convertView.findViewById(R.id.ivDishThumbnail);
        TextView tvName = convertView.findViewById(R.id.tvDishName);
        ImageView ivPromotion = convertView.findViewById(R.id.ivPromotion);

        if (dish != null) {
            tvName.setText(dish.getName());
            tvName.setSelected(true);

            if (dish.getThumbnail() != null) {
                ivThumbnail.setImageResource(dish.getThumbnail().getImg());
            }

            if (dish.isPromotion()) {
                ivPromotion.setVisibility(View.VISIBLE);
            } else {
                ivPromotion.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
