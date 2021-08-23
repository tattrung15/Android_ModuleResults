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
import com.nhom11.dto.BaoCaoHocPhanDTO;

import java.util.ArrayList;

public class CustomListview extends ArrayAdapter {
    Activity context = null;
    int layoutID;
    ArrayList<BaoCaoHocPhanDTO> list = null;

    public CustomListview(@NonNull Activity context, int layoutID, @NonNull ArrayList<BaoCaoHocPhanDTO> objects) {
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
            TextView txtBCHP_MaHP = convertView.findViewById(R.id.txtBCHP_MaHP);
            TextView txtBCHP_TenHP = convertView.findViewById(R.id.txtBCHP_TenHP);
            TextView txtBCHP_TongSoLop = convertView.findViewById(R.id.txtBCHP_TongSoLop);
            TextView txtBCHP_TongSoGio = convertView.findViewById(R.id.txtBCHP_TongSoGio);
            TextView txtBCHP_LoaiHP = convertView.findViewById(R.id.txtBCHP_LoaiHP);
            //lấy bản ghi thứ position gán cho thành phần tương ứng
            txtBCHP_MaHP.setText(list.get(position).getMaHocPhan());
            txtBCHP_TenHP.setText(list.get(position).getTenHocPhan());
            txtBCHP_TongSoLop.setText(String.valueOf(list.get(position).getTongSoLop()));
            txtBCHP_TongSoGio.setText(String.valueOf(list.get(position).getTongSoGio()));
            txtBCHP_LoaiHP.setText(list.get(position).getLoaiHocPhan());
        }
        return convertView;
    }
}
