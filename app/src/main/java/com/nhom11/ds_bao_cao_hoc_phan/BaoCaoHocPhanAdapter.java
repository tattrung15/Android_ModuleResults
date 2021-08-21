package com.nhom11.ds_bao_cao_hoc_phan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhom11.R;

import com.nhom11.dto.BaoCaoHocPhanDTO;

import java.util.List;

public class BaoCaoHocPhanAdapter extends ArrayAdapter<BaoCaoHocPhanDTO> {

    List<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs = null;

    public BaoCaoHocPhanAdapter(@NonNull Context context, List<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs) {
        super(context, R.layout.item_ds_bcao_hphan, baoCaoHocPhanDTOs);
        this.baoCaoHocPhanDTOs = baoCaoHocPhanDTOs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.item_ds_bcao_hphan, parent, false);

        TextView txtTenHphan = itemView.findViewById(R.id.txtTenHP_DSBCHPItem);
        TextView txtMaHphan = itemView.findViewById(R.id.txtMaHP_DSBCHPItem);
        TextView txtSoLop = itemView.findViewById(R.id.txtSoLop_DSBCHPItem);

        BaoCaoHocPhanDTO baoCaoHocPhanDTO = baoCaoHocPhanDTOs.get(position);
        txtTenHphan.setText(baoCaoHocPhanDTO.getTenHocPhan());
        txtMaHphan.setText(baoCaoHocPhanDTO.getMaHocPhan());
        txtSoLop.setText(String.valueOf(baoCaoHocPhanDTO.getTongSoLop()));

        return itemView;
    }
}
