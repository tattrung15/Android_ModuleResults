package com.nhom11.chi_tiet_bao_cao_hoc_phan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhom11.R;
import com.nhom11.models.HocPhan;

import java.util.ArrayList;

public class CustomSpinner extends ArrayAdapter {
    Activity context = null;
    int layoutID;
    ArrayList<HocPhan> list = null;

    public CustomSpinner(@NonNull Activity context, int layoutID, @NonNull ArrayList<HocPhan> objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if (list.size() > 0) {
            //lấy dòng thứ i
            final TextView txtMaHP = convertView.findViewById(R.id.txtMaHP);
            final TextView txtTenHP = convertView.findViewById(R.id.txtTenHP);
            //lấy bản ghi thứ position gán cho thành phần tương ứng
            txtMaHP.setText(list.get(position).getMaHocPhan());
            txtTenHP.setText(list.get(position).getTenHocPhan());
        }
        return convertView;
    }
}
