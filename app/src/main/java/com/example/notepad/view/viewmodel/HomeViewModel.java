package com.example.notepad.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notepad.database.AppDatabase;
import com.example.notepad.database.NoteDiaryEntity;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private AppDatabase appDatabase;
    private MutableLiveData<List<NoteDiaryEntity>> diaryList = new MutableLiveData<>();

    public void setAppDatabase(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public MutableLiveData<List<NoteDiaryEntity>> getDiaryList() {
        return diaryList;
    }

    public void getData() {
        List<NoteDiaryEntity> notes =appDatabase.noteDiaryDao().getAll();
        diaryList.setValue(notes);
    }

    public void deleteNote(NoteDiaryEntity note) {
        appDatabase.noteDiaryDao().deleteNoteDiary(note);
    }
}
