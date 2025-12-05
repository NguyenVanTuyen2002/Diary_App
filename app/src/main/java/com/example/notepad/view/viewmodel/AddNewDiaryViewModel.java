package com.example.notepad.view.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.notepad.database.AppDatabase;
import com.example.notepad.database.NoteDiaryEntity;

public class AddNewDiaryViewModel extends ViewModel {

    private AppDatabase appDatabase;

    public void setAppDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void saveNote(NoteDiaryEntity note) {
        appDatabase.noteDiaryDao().insertNoteDiary(note);
        if (appDatabase != null) {
            Log.d("Tuyen", "Ảnh           " + note.getImgContent() + "Content      " + note.getContent());
        }else {
            Log.d("Tuyen", "appDatabase = null → Bạn chưa setAppDatabase()");
        }
    }

    public void updateNote(NoteDiaryEntity note) {
        appDatabase.noteDiaryDao().updateNoteDiary(note);
    }

    public NoteDiaryEntity getNoteById(int id){
        return appDatabase.noteDiaryDao().getNoteById(id);
    }
}
