package com.example.notepad.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.R;
import com.example.notepad.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .asGif()
                .load(R.drawable.ic_book) // đặt file .gif vào thư mục res/drawable
                .into(binding.imgBook);

        Glide.with(this)
                .asGif()
                .load(R.drawable.ic_flower) // đặt file .gif vào thư mục res/drawable
                .into(binding.imgFlower);

        binding.layoutSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
    }
}
