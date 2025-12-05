package com.example.notepad.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDiaryDao {
    @Query("SELECT * FROM  NoteDiaryEntity ORDER BY id DESC")
    List<NoteDiaryEntity> getAll();

    @Query("SELECT * FROM NoteDiaryEntity WHERE id = :noteId LIMIT 1")
    NoteDiaryEntity getNoteById(int noteId);

    @Insert
    void insertNoteDiary(NoteDiaryEntity note);

    @Delete
    void deleteNoteDiary(NoteDiaryEntity note);

    @Update
    void updateNoteDiary(NoteDiaryEntity note);

    @Query("DELETE FROM NoteDiaryEntity")
    void deleteAll();
}
