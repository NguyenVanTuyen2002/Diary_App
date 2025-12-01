package com.example.notepad.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class DataManager {
    private static DataManager dataManager;

    public DataManager() {
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public AppDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "NoteDiary").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
