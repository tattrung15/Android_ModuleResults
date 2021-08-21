package com.nhom11.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom11.R;
import com.nhom11.models.GiangVien;
import com.nhom11.xem_ds_bao_cao_hoc_phan.XemDSBaoCaoHocPhan;

public class Dashboard extends AppCompatActivity {

    Button btnThoatTaiKhoan, btnXemDSBaoCaoHP, btnXemBaoCaoGD, btnNhapBaoCaoGD;
    TextView txtMaGiangVien, txtTenGiangVien;
    GiangVien giangVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        giangVien = intent.getParcelableExtra("giangVien");

        getWidget();

        if (giangVien != null) {
            txtMaGiangVien.setText(String.valueOf(giangVien.getMaGiangVien()));
            txtTenGiangVien.setText(giangVien.getTenGiangVien());
        }

        btnXemDSBaoCaoHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, XemDSBaoCaoHocPhan.class);
                startActivity(intent);
            }
        });

        btnThoatTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getWidget() {
        txtMaGiangVien = findViewById(R.id.txtMaGiangVien);
        txtTenGiangVien = findViewById(R.id.txtTenGiangVien);
        btnThoatTaiKhoan = findViewById(R.id.btnThoatTaiKhoan);
        btnXemDSBaoCaoHP = findViewById(R.id.btnXemDSBaoCaoHP);
        btnXemBaoCaoGD = findViewById(R.id.btnXemBaoCaoGD);
        btnNhapBaoCaoGD = findViewById(R.id.btnNhapBaoCaoGD);
    }
}