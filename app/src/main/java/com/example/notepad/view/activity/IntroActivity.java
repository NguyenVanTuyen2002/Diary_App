package com.example.notepad.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.notepad.databinding.ActivityIntroBinding;
import com.example.notepad.model.Intro;
import com.example.notepad.view.adapter.IntroAdapter;
import com.example.notepad.view.viewmodel.IntroViewModel;

import java.util.List;

public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;
    private IntroAdapter adapter;
    private List<Intro> intros;
    private IntroViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(IntroViewModel.class);

        adapter = new IntroAdapter();
        intros = viewModel.initData();
        adapter.setIntros(intros);
        binding.viewPagerIntro.setAdapter(adapter);
        binding.dotPagerIntro.attachTo(binding.viewPagerIntro);

        adapter.setOnNextClickListener(position -> {
            int next = position + 1;

            if (next < intros.size()) {
                binding.viewPagerIntro.setCurrentItem(next, true);

            } else {
                Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
