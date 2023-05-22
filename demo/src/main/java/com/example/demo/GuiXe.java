package com.example.demo;

public class GuiXe {
    private int ma;
    private String bienso;
    private String loaixe;
    private int khu;

    public GuiXe(int ma, String bienso, String loaixe, int khu) {
        this.ma = ma;
        this.bienso = bienso;
        this.loaixe = loaixe;
        this.khu = khu;
    }

    public GuiXe(){
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getBienso() {
        return bienso;
    }

    public void setBienso(String bienso) {
        this.bienso = bienso;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public int getKhu() {
        return khu;
    }

    public void setKhu(int khu) {
        this.khu = khu;
    }
}
