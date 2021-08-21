package com.nhom11.dto;

public class BaoCaoHocPhanDTO {
    private int maBaoCaoHocPhan;
    private String maHocPhan;
    private String tenHocPhan;
    private int tongSoLop;
    private float tongSoGio;
    private String loaiHocPhan;

    public BaoCaoHocPhanDTO() {
    }

    public BaoCaoHocPhanDTO(int maBaoCaoHocPhan, String maHocPhan, String tenHocPhan, int tongSoLop,
                            float tongSoGio, String loaiHocPhan) {
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.tongSoLop = tongSoLop;
        this.tongSoGio = tongSoGio;
        this.loaiHocPhan = loaiHocPhan;
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

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    public String getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(String loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

    @Override
    public String toString() {
        return "BaoCaoHocPhanDTO{" +
                "maBaoCaoHocPhan=" + maBaoCaoHocPhan +
                ", maHocPhan='" + maHocPhan + '\'' +
                ", tenHocPhan='" + tenHocPhan + '\'' +
                ", tongSoLop=" + tongSoLop +
                ", tongSoGio=" + tongSoGio +
                ", loaiHocPhan='" + loaiHocPhan + '\'' +
                '}';
    }
}
