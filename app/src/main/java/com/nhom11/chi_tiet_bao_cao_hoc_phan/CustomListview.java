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
import com.nhom11.models.BaoCaoGiangDay;

import java.util.ArrayList;

public class CustomListview extends ArrayAdapter {
    Activity context = null;
    int layoutID;
    ArrayList<BaoCaoGiangDay> list = null;

    public CustomListview(@NonNull Activity context, int layoutID, @NonNull ArrayList<BaoCaoGiangDay> objects) {
        super(context, layoutID, objects);
        this.context = context;
        this.layoutID = layoutID;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);
        if (list.size() > 0) {
            //lấy dòng thứ i
            final TextView txtMaLop =convertView.findViewById(R.id.txtMaLop);
            final TextView txtMaGv = convertView.findViewById(R.id.txtMaGv);
            final TextView txtLoaiTiet = convertView.findViewById(R.id.txtLoaiTiet);
            //lấy bản ghi thứ position gán cho thành phần tương ứng
            txtMaLop.setText(list.get(position).getMaLop()+" ");
            txtMaGv.setText(list.get(position).getMaGiangVien()+" ");
            txtLoaiTiet.setText(list.get(position).getLoaiTiet());
        }
        return convertView;
    }
}
