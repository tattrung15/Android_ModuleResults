package com.nhom11.dto;

public class BaoCaoHocPhanDTO {
    private int maBaoCaoHocPhan;
    private String maHocPhan;
    private int tongSoLop;
    private float tongSoGio;
    private String tenHocPhan;


    public BaoCaoHocPhanDTO(int maBaoCaoHocPhan, String maHocPhan, int tongSoLop, float tongSoGio, String loaiHocPhan, String tenHocPhan) {
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
        this.maHocPhan = maHocPhan;
        this.tongSoLop = tongSoLop;
        this.tongSoGio = tongSoGio;
        this.loaiHocPhan = loaiHocPhan;
        this.tenHocPhan = tenHocPhan;
    }

    public BaoCaoHocPhanDTO() {
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    private String loaiHocPhan;

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

    public String getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(String loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

}
