package com.nhom11.models;

public class BaoCaoHocPhan {
    private int maBaoCaoHocPhan;
    private String maHocPhan;
    private int tongSoLop;
    private float tongSoGio;
    private String loaiHocPhan;

    public BaoCaoHocPhan() {
    }

    public BaoCaoHocPhan(int maBaoCaoHocPhan, String maHocPhan, int tongSoLop, float tongSoGio,
                         String loaiHocPhan) {
        this.maBaoCaoHocPhan = maBaoCaoHocPhan;
        this.maHocPhan = maHocPhan;
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

    public String getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(String loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

    @Override
    public String toString() {
        return "BaoCaoHocPhan{" +
                "maBaoCaoHocPhan=" + maBaoCaoHocPhan +
                ", maHocPhan='" + maHocPhan + '\'' +
                ", tongSoLop=" + tongSoLop +
                ", tongSoGio=" + tongSoGio +
                ", loaiHocPhan='" + loaiHocPhan + '\'' +
                '}';
    }
}
