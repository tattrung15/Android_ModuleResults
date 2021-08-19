package com.nhom11.models;

public class GiangVien {
    private Integer maGiangVien;
    private String tenGiangVien;
    private String username;
    private String password;

    public GiangVien() {
    }

    public GiangVien(Integer maGiangVien, String tenGiangVien, String username, String password) {
        this.maGiangVien = maGiangVien;
        this.tenGiangVien = tenGiangVien;
        this.username = username;
        this.password = password;
    }

    public Integer getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(Integer maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GiangVien{" +
                "maGiangVien=" + maGiangVien +
                ", tenGiangVien='" + tenGiangVien + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
