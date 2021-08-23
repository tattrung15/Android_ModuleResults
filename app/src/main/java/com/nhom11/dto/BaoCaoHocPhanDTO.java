package com.nhom11.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class BaoCaoHocPhanDTO implements Parcelable {
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

    protected BaoCaoHocPhanDTO(Parcel in) {
        maBaoCaoHocPhan = in.readInt();
        maHocPhan = in.readString();
        tenHocPhan = in.readString();
        tongSoLop = in.readInt();
        tongSoGio = in.readFloat();
        loaiHocPhan = in.readString();
    }

    public static final Creator<BaoCaoHocPhanDTO> CREATOR = new Creator<BaoCaoHocPhanDTO>() {
        @Override
        public BaoCaoHocPhanDTO createFromParcel(Parcel in) {
            return new BaoCaoHocPhanDTO(in);
        }

        @Override
        public BaoCaoHocPhanDTO[] newArray(int size) {
            return new BaoCaoHocPhanDTO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(maBaoCaoHocPhan);
        parcel.writeString(maHocPhan);
        parcel.writeString(tenHocPhan);
        parcel.writeInt(tongSoLop);
        parcel.writeFloat(tongSoGio);
        parcel.writeString(loaiHocPhan);
    }
}
