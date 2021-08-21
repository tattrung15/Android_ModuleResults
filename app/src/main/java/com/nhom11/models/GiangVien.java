package com.nhom11.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GiangVien implements Parcelable {
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

    protected GiangVien(Parcel in) {
        if (in.readByte() == 0) {
            maGiangVien = null;
        } else {
            maGiangVien = in.readInt();
        }
        tenGiangVien = in.readString();
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<GiangVien> CREATOR = new Creator<GiangVien>() {
        @Override
        public GiangVien createFromParcel(Parcel in) {
            return new GiangVien(in);
        }

        @Override
        public GiangVien[] newArray(int size) {
            return new GiangVien[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (maGiangVien == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maGiangVien);
        }
        parcel.writeString(tenGiangVien);
        parcel.writeString(username);
        parcel.writeString(password);
    }
}
