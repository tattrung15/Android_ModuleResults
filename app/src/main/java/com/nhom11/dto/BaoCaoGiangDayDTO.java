package com.nhom11.dto;

public class BaoCaoGiangDayDTO {
    private int maBaoCaoGiangDay;
    private int maGiangVien;
    private String tenGiangVien;
    private int maBaoCaoHocPhan;
    private String maHocPhan;
    private String tenHocPhan;
    private int maLop;
    private String tenLop;
    private float soGioTrenLop;
    private int siSo;
    private int soTietMotNgay;
    private String loaiTiet;

    public BaoCaoGiangDayDTO() {
    }

    public BaoCaoGiangDayDTO(int maBaoCaoGiangDay, int maGiangVien, String tenGiangVien,
                             int maBaoCaoHocPhan, String maHocPhan, String tenHocPhan, int maLop,
                             String tenLop, float soGioTrenLop, int siSo, int soTietMotNgay,
                             String loaiTiet) {
        this.maBaoCaoGiangDay = maBaoCaoGiangDay;
        this.maGiangVien = maGiangVien;
        this.tenGiangVien = tenGiangVien;
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.soGioTrenLop = soGioTrenLop;
        this.siSo = siSo;
        this.soTietMotNgay = soTietMotNgay;
        this.loaiTiet = loaiTiet;
    }

    public int getMaBaoCaoGiangDay() {
        return maBaoCaoGiangDay;
    }

    public void setMaBaoCaoGiangDay(int maBaoCaoGiangDay) {
        this.maBaoCaoGiangDay = maBaoCaoGiangDay;
    }

    public int getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(int maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public int getMaBaoCaoHocPhan() {
        return maBaoCaoHocPhan;
    }

    public void setMaBaoCaoHocPhan(int maBaoCaoHocPhan) {
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
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

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public float getSoGioTrenLop() {
        return soGioTrenLop;
    }

    public void setSoGioTrenLop(float soGioTrenLop) {
        this.soGioTrenLop = soGioTrenLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public int getSoTietMotNgay() {
        return soTietMotNgay;
    }

    public void setSoTietMotNgay(int soTietMotNgay) {
        this.soTietMotNgay = soTietMotNgay;
    }

    public String getLoaiTiet() {
        return loaiTiet;
    }

    public void setLoaiTiet(String loaiTiet) {
        this.loaiTiet = loaiTiet;
    }

    @Override
    public String toString() {
        return "BaoCaoGiangDayDTO{" +
                "maBaoCaoGiangDay=" + maBaoCaoGiangDay +
                ", maGiangVien=" + maGiangVien +
                ", tenGiangVien='" + tenGiangVien + '\'' +
                ", maBaoCaoHocPhan=" + maBaoCaoHocPhan +
                ", maHocPhan=" + maHocPhan +
                ", tenHocPhan='" + tenHocPhan + '\'' +
                ", maLop=" + maLop +
                ", tenLop='" + tenLop + '\'' +
                ", soGioTrenLop=" + soGioTrenLop +
                ", siSo=" + siSo +
                ", soTietMotNgay=" + soTietMotNgay +
                ", loaiTiet='" + loaiTiet + '\'' +
                '}';
    }
}
