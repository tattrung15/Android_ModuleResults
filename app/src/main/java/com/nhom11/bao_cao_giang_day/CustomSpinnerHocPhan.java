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
import com.nhom11.dto.BaoCaoHocPhanDTO;

import java.util.ArrayList;

public class CustomSpinnerHocPhan extends ArrayAdapter<BaoCaoHocPhanDTO> {

    Activity context;
    int layoutID;
    ArrayList<BaoCaoHocPhanDTO> list;

    public CustomSpinnerHocPhan(@NonNull Activity context, int layoutID,
                                @NonNull ArrayList<BaoCaoHocPhanDTO> list) {
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

            TextView txtMaHocPhan = convertView.findViewById(R.id.txtMaHP);
            TextView txtTenHocPhan = convertView.findViewById(R.id.txtTenHP);

            txtMaHocPhan.setText(String.valueOf(list.get(position).getMaHocPhan()));
            txtTenHocPhan.setText(list.get(position).getTenHocPhan());
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

            TextView txtMaHocPhan = convertView.findViewById(R.id.txtMaHP);
            TextView txtTenHocPhan = convertView.findViewById(R.id.txtTenHP);

            txtMaHocPhan.setText(String.valueOf(list.get(position).getMaHocPhan()));
            txtTenHocPhan.setText(list.get(position).getTenHocPhan());
        }
        return convertView;
    }
}
