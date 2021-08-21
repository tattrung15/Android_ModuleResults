package com.nhom11.xem_ds_bao_cao_hoc_phan;

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

import java.util.ArrayList;

public class BaoCaoHocPhanAdapter extends ArrayAdapter<BaoCaoHocPhanDTO> {
    BaoCaoHocPhanDTO[] listBcaoHphan = null;

    public BaoCaoHocPhanAdapter(@NonNull Context context, BaoCaoHocPhanDTO[] listBcaoHphan) {
        super(context, R.layout.item_xem_ds_bcao_hphan, listBcaoHphan);
        this.listBcaoHphan = listBcaoHphan;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.item_xem_ds_bcao_hphan, parent, false);

        TextView txtTenHphan = itemView.findViewById(R.id.txtTenHP_DSBCHPItem);
        TextView txtMaHphan = itemView.findViewById(R.id.txtMaHP_DSBCHPItem);
        TextView txtSoLop = itemView.findViewById(R.id.txtSoLop_DSBCHPItem);

        BaoCaoHocPhanDTO bcaoHphan = listBcaoHphan[position];
        txtTenHphan.setText(bcaoHphan.getTenHocPhan());
        txtMaHphan.setText(bcaoHphan.getMaHocPhan());
        txtSoLop.setText(String.valueOf(bcaoHphan.getTongSoLop()));

        return itemView;
    }
}
