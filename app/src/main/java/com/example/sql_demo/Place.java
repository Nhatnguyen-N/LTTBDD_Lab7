package com.example.sql_demo;

public class Place {
    private String STT;
    private String diadiem;

    public Place(){}

    public Place(String diadiem) {
        this.diadiem = diadiem;
    }

    public Place(String STT, String diadiem) {
        this.STT = STT;
        this.diadiem = diadiem;
    }

    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }
}
