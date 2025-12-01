package com.example.notepad.model;

public class Intro {
    private int imgIntro;
    private String txtTitle;
    private String txtContent;
    private String txtBtnNextIntro;

    public Intro(int imgIntro, String txtTitle, String txtContent, String txtBtnNextIntro) {
        this.imgIntro = imgIntro;
        this.txtTitle = txtTitle;
        this.txtContent = txtContent;
        this.txtBtnNextIntro = txtBtnNextIntro;
    }

    public int getImgIntro() {
        return imgIntro;
    }

    public void setImgIntro(int imgIntro) {
        this.imgIntro = imgIntro;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public String getTxtBtnNextIntro() {
        return txtBtnNextIntro;
    }

    public void setTxtBtnNextIntro(String txtBtnNextIntro) {
        this.txtBtnNextIntro = txtBtnNextIntro;
    }
}
