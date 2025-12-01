package com.example.notepad.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {NoteDiaryEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDiaryDao noteDiaryDao();
}
