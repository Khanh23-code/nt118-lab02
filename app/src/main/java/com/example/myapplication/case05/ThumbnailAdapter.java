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

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    private Context context;
    private List<Thumbnail> thumbnails;

    public ThumbnailAdapter(@NonNull Context context, int resource, @NonNull List<Thumbnail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.thumbnails = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, parent, false);
        }
        Thumbnail thumbnail = getItem(position);
        TextView tvName = convertView.findViewById(R.id.tvThumbnailSelected);
        if (thumbnail != null && tvName != null) {
            tvName.setText(thumbnail.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        }
        Thumbnail thumbnail = getItem(position);
        TextView tvName = convertView.findViewById(R.id.tvThumbnailName);
        ImageView ivImg = convertView.findViewById(R.id.ivThumbnailImg);

        if (thumbnail != null) {
            if (tvName != null) {
                tvName.setText(thumbnail.getName());
            }
            if (ivImg != null) {
                ivImg.setImageResource(thumbnail.getImg());
            }
        }
        return convertView;
    }
}
