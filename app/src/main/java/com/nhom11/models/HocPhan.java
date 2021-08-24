package com.nhom11.models;

public class HocPhan {
    private String maHocPhan;
    private String tenHocPhan;

    public HocPhan() {
    }

    public HocPhan(String maHocPhan, String tenHocPhan) {
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    @Override
    public String toString() {
        return "HocPhan{" +
                "maHocPhan='" + maHocPhan + '\'' +
                ", tenHocPhan='" + tenHocPhan + '\'' +
                '}';
    }
}
