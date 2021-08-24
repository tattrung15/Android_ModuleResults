package com.nhom11.bao_cao_giang_day;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhom11.R;
import com.nhom11.models.GiangVien;

import java.util.ArrayList;

public class CustomSpinnerGiangVien extends ArrayAdapter<GiangVien> {

    Activity context;
    int layoutID;
    ArrayList<GiangVien> list;

    public CustomSpinnerGiangVien(@NonNull Activity context, int layoutID, @NonNull ArrayList<GiangVien> list) {
        super(context, layoutID, list);
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID, null);
        }
        if (list.size() > 0) {

            TextView txtMaGiangVien = convertView.findViewById(R.id.txtMaHP);
            TextView txtTenGiangVien = convertView.findViewById(R.id.txtTenHP);

            txtMaGiangVien.setText(String.valueOf(list.get(position).getMaGiangVien()));
            txtTenGiangVien.setText(list.get(position).getTenGiangVien());
        }
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID, null);
        }
        if (list.size() > 0) {

            LinearLayout customSpinnerBCHP = convertView.findViewById(R.id.customSpinnerBCHP);
            customSpinnerBCHP.setPadding(0, 0, 0, 0);

            TextView txtMaGiangVien = convertView.findViewById(R.id.txtMaHP);
            TextView txtTenGiangVien = convertView.findViewById(R.id.txtTenHP);

            txtMaGiangVien.setText(String.valueOf(list.get(position).getMaGiangVien()));
            txtTenGiangVien.setText(list.get(position).getTenGiangVien());
        }
        return convertView;
    }
}
