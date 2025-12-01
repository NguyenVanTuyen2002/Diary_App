package com.example.notepad.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.notepad.database.DataManager;
import com.example.notepad.database.NoteDiaryEntity;
import com.example.notepad.databinding.ActivityAddNewdiaryBinding;
import com.example.notepad.view.viewmodel.AddNewDiaryViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddNewDiaryActivity extends AppCompatActivity {
    private ActivityAddNewdiaryBinding binding;
    private AddNewDiaryViewModel viewModel;
    private static final int PICK_IMAGE = 1;
    private NoteDiaryEntity noteDiaryEntity;
    private Uri uriImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewdiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AddNewDiaryViewModel.class);

        viewModel.setAppDatabase(DataManager.getInstance().createDatabase(this));

        binding.btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        binding.btnSaveAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtTitleAddNote.getText().toString();
                String content = binding.edtContentAddNote.getText().toString();

                String imgContent = (uriImage != null) ? uriImage.toString() : null;

                noteDiaryEntity = new NoteDiaryEntity("1/1/1", title,content, imgContent, 1);
                viewModel.saveNote(noteDiaryEntity);

                Intent intent = new Intent(AddNewDiaryActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            uriImage = data.getData();
            binding.imgAddNote.setImageURI(uriImage);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }


}
