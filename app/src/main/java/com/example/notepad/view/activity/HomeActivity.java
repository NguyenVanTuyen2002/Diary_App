package com.example.notepad.view.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.notepad.database.DataManager;
import com.example.notepad.database.NoteDiaryEntity;
import com.example.notepad.databinding.ActivityHomeBinding;
import com.example.notepad.view.adapter.HomeAdapter;
import com.example.notepad.view.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeAdapter adapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new HomeAdapter();
        adapter.setNoteDiaryEntityList(new ArrayList<>());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.setAppDatabase(DataManager.getInstance().createDatabase(this));

        binding.listNoteDiary.setAdapter(adapter);

        viewModel.getDiaryList().observe(this, new Observer<List<NoteDiaryEntity>>() {
            @Override
            public void onChanged(List<NoteDiaryEntity> noteDiaryEntities) {
                if (noteDiaryEntities != null && !noteDiaryEntities.isEmpty()) {
                    adapter.setNoteDiaryEntityList(noteDiaryEntities);
                    binding.listNoteDiary.setVisibility(View.VISIBLE);
                    binding.imgNoteHome.setVisibility(GONE);
                    binding.txtNoteHome.setVisibility(GONE);
                } else {
                    binding.listNoteDiary.setVisibility(GONE);
                    binding.imgNoteHome.setVisibility(VISIBLE);
                    binding.txtNoteHome.setVisibility(VISIBLE);
                }
            }
        });

        viewModel.getData();

        binding.btnNewDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddNewDiaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
