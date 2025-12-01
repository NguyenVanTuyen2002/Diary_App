package com.example.notepad.database;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class NoteDiaryEntity {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String date;
    private String title;
    private String content;
    private String imgContent;
    private int imgFeel;

    public NoteDiaryEntity(String date, String title, String content, String imgContent, int imgFeel) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.imgContent = imgContent;
        this.imgFeel = imgFeel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public int getImgFeel() {
        return imgFeel;
    }

    public void setImgFeel(int imgFeel) {
        this.imgFeel = imgFeel;
    }
}
