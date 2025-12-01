package com.example.notepad.view.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.notepad.R;
import com.example.notepad.model.Intro;

import java.util.ArrayList;
import java.util.List;

public class IntroViewModel extends ViewModel {
    private List<Intro> data;
    public List<Intro> initData(){
        data = new ArrayList<>();
        data.add(new Intro(R.drawable.intro1, "Customize Your Journal", "Personalize Your Diary with a Variety of Emojis, Stickers, and Backgrounds", "Next"));
        data.add(new Intro(R.drawable.intro2, "Secure Journal Keeper", "Project Your Diary with Password Security", "Next"));
        data.add(new Intro(R.drawable.intro3, "Emotion Journal", "Monitor Your Emotional Journey Every Day", "Next"));
        data.add(new Intro(R.drawable.intro4, "Track expenses", "Effortlessly Monitor and Control Your Spending", "Start"));
        return data;
    }

}
