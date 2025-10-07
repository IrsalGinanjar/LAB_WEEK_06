package com.example.lab_week_06;

import android.widget.ImageView;

// Tambahkan 'public' di sini
public interface ImageLoader {
    void loadImage(String imageUrl, ImageView imageView);
}