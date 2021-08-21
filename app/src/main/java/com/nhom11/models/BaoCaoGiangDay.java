package com.nhom11.models;

public class BaoCaoGiangDay {
    private int maBaoCaoGiangDay;
    private int maGiangVien;
    private int maBaoCaoHocPhan;
    private int maLop;
    private int tongSoLop;
    private float tongSoGio;
    private int soTietMotNgay;
    private String loaiTiet;

    public BaoCaoGiangDay() {
    }

    public BaoCaoGiangDay(int maBaoCaoGiangDay, int maGiangVien, int maBaoCaoHocPhan, int maLop,
                          int tongSoLop, float tongSoGio, int soTietMotNgay, String loaiTiet) {
        this.maBaoCaoGiangDay = maBaoCaoGiangDay;
        this.maGiangVien = maGiangVien;
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
        this.maLop = maLop;
        this.tongSoLop = tongSoLop;
        this.tongSoGio = tongSoGio;
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

    public int getMaBaoCaoHocPhan() {
        return maBaoCaoHocPhan;
    }

    public void setMaBaoCaoHocPhan(int maBaoCaoHocPhan) {
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public int getTongSoLop() {
        return tongSoLop;
    }

    public void setTongSoLop(int tongSoLop) {
        this.tongSoLop = tongSoLop;
    }

    public float getTongSoGio() {
        return tongSoGio;
    }

    public void setTongSoGio(float tongSoGio) {
        this.tongSoGio = tongSoGio;
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

//    @Override
//    public String toString() {
//        return "BaoCaoGiangDay{" +
//                "maBaoCaoGiangDay=" + maBaoCaoGiangDay +
//                ", maGiangVien=" + maGiangVien +
//                ", maBaoCaoHocPhan=" + maBaoCaoHocPhan +
//                ", maLop=" + maLop +
//                ", tongSoLop=" + tongSoLop +
//                ", tongSoGio=" + tongSoGio +
//                ", soTietMotNgay=" + soTietMotNgay +
//                ", loaiTiet='" + loaiTiet + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "+" + maBaoCaoGiangDay + " - " + maGiangVien + " - " + maBaoCaoHocPhan + " - "
                + maLop + " - " + tongSoLop + " lớp - " + tongSoGio + " giờ - " + soTietMotNgay
                + " - " + loaiTiet;
    }
}
