package com.example.notepad.view.adapter;

import com.example.notepad.database.NoteDiaryEntity;

public interface OnClickListener {
    void onUpdate(NoteDiaryEntity note);
    void onDelete(NoteDiaryEntity note);
}
