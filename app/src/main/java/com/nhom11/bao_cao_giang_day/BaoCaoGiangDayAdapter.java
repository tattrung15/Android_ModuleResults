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
import com.nhom11.dto.BaoCaoGiangDayDTO;

import java.util.List;

public class BaoCaoGiangDayAdapter extends ArrayAdapter<BaoCaoGiangDayDTO> {

    Activity context;
    int layoutID;
    List<BaoCaoGiangDayDTO> list;

    public BaoCaoGiangDayAdapter(Activity context, int layoutID, List<BaoCaoGiangDayDTO> list) {
        super(context, layoutID, list);
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_listview_bao_cao_giang_day, null);
        if (list.size() > 0) {

            BaoCaoGiangDayDTO baoCaoGiangDayDTO = list.get(position);

            TextView txtTenHocPhan = convertView.findViewById(R.id.txtBCGD_TenHP);
            TextView txtMaHocPhan = convertView.findViewById(R.id.txtBCGD_MaHP);
            TextView txtTenLop = convertView.findViewById(R.id.txtBCGD_TenLop);
            TextView txtGiangVien = convertView.findViewById(R.id.txtBCGD_GiangVien);
            TextView txtSoGioTrenLop = convertView.findViewById(R.id.txtBCGD_SoGioTrenLop);
            TextView txtSiSo = convertView.findViewById(R.id.txtBCGD_SiSo);
            TextView txtSoTietMotNgay = convertView.findViewById(R.id.txtBCGD_SoTietMotNgay);
            TextView txtLoaiTietHoc = convertView.findViewById(R.id.txtBCGD_LoaiTietHoc);

            String siSo = baoCaoGiangDayDTO.getSiSoThucTe() + "/" + baoCaoGiangDayDTO.getSiSoCoDinh();

            txtTenHocPhan.setText(baoCaoGiangDayDTO.getTenHocPhan());
            txtMaHocPhan.setText(baoCaoGiangDayDTO.getMaHocPhan());
            txtTenLop.setText(baoCaoGiangDayDTO.getTenLop());
            txtGiangVien.setText(baoCaoGiangDayDTO.getTenGiangVien());
            txtSoGioTrenLop.setText(String.valueOf(baoCaoGiangDayDTO.getSoGioTrenLop()));
            txtSiSo.setText(siSo);
            txtSoTietMotNgay.setText(String.valueOf(baoCaoGiangDayDTO.getSoTietMotNgay()));
            txtLoaiTietHoc.setText(baoCaoGiangDayDTO.getLoaiTiet());
        }
        return convertView;
    }
}
