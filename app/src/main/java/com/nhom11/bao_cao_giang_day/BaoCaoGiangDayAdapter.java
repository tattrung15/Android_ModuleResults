package com.nhom11.bao_cao_giang_day;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhom11.R;
import com.nhom11.models.BaoCaoGiangDay;

import java.util.ArrayList;

public class BaoCaoGiangDayAdapter extends ArrayAdapter {

    Activity context = null;
    int layoutID;
    ArrayList<BaoCaoGiangDay> list = null;

    public BaoCaoGiangDayAdapter(@NonNull Activity context, int layoutID, @NonNull ArrayList<BaoCaoGiangDay> objects) {
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

            final TextView txtTenLopBCGD = convertView.findViewById(R.id.txtTenLopBCGD);
            final TextView txtSiSoBCGD = convertView.findViewById(R.id.txtSiSoBCGD);
            final TextView txtSoTietBCGD = convertView.findViewById(R.id.txtSoTietBCGD);

            //Đổ dữ liệu tạm
            txtTenLopBCGD.setText("KTPM2");
            txtSiSoBCGD.setText("70");
            txtSoTietBCGD.setText("5");
        }
        return convertView;
    }
}
