package com.example.notepad.view.activity;

import android.app.ComponentCaller;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
    private boolean isEditMode = false;

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



        // ktra nếu noteId == -1 thì là tạo mới, noteId != -1 thì là sửa
        int noteId = getIntent().getIntExtra("note_id", -1);

        if (noteId != -1) {
            isEditMode = true;
            noteDiaryEntity = viewModel.getNoteById(noteId);
            if (noteDiaryEntity != null) {
                binding.edtTitleAddNote.setText(noteDiaryEntity.getTitle());
                binding.edtContentAddNote.setText(noteDiaryEntity.getContent());

                if (noteDiaryEntity.getImgContent() != null){
                    uriImage = Uri.parse(noteDiaryEntity.getImgContent());
                    binding.imgAddNote.setImageURI(uriImage);
                }
            }
        }

        binding.btnSaveAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtTitleAddNote.getText().toString();
                String content = binding.edtContentAddNote.getText().toString();

                String imgContent = (uriImage != null) ? uriImage.toString() : null;

                if (isEditMode == false) {
                    noteDiaryEntity = new NoteDiaryEntity("1/1/1", title,content, imgContent, 1);
                    viewModel.saveNote(noteDiaryEntity);
                }else {
                    noteDiaryEntity.setTitle(title);
                    noteDiaryEntity.setContent(content);
                    noteDiaryEntity.setImgContent(imgContent);
                    viewModel.updateNote(noteDiaryEntity);
                }

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

            // Lưu quyền đọc URI
            final int takeFlags = data.getFlags()
                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            getContentResolver().takePersistableUriPermission(uriImage, takeFlags);

            binding.imgAddNote.setImageURI(uriImage);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        // BẮT BUỘC phải thêm 2 dòng này
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        startActivityForResult(intent, PICK_IMAGE);
    }

}
