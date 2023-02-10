package com.example.onlineshophesam.entity;

public class Music {

    private String titleMusic;
    private String detailMusic;
    private String namefile;
    private String fileUrl;
    private String imgMusic;

    public Music(String titleMusic, String detailMusic, String namefile, String fileUrl, String imgMusic) {
        this.titleMusic = titleMusic;
        this.detailMusic = detailMusic;
        this.namefile = namefile;
        this.fileUrl = fileUrl;
        this.imgMusic = imgMusic;
    }

    public String getTitleMusic() {
        return titleMusic;
    }

    public void setTitleMusic(String titleMusic) {
        this.titleMusic = titleMusic;
    }

    public String getDetailMusic() {
        return detailMusic;
    }

    public void setDetailMusic(String detailMusic) {
        this.detailMusic = detailMusic;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getImgMusic() {
        return imgMusic;
    }

    public void setImgMusic(String imgMusic) {
        this.imgMusic = imgMusic;
    }
}
