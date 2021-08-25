package com.nhom11.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.R;
import com.nhom11.bao_cao_giang_day.BaoCaoGiangDayActivity;
import com.nhom11.chi_tiet_bao_cao_hoc_phan.ChiTietBaoCaoHocPhan;
import com.nhom11.ds_bao_cao_hoc_phan.DSBaoCaoHocPhan;
import com.nhom11.models.GiangVien;
import com.nhom11.utils.CustomAlertDialog;

public class Dashboard extends AppCompatActivity {

    MaterialToolbar topAppBarDashboard;
    MenuItem optionMenuExit;
    Button btnThoatTaiKhoan, btnDSBaoCaoHocPhan, btnBaoCaoHocPhan, btnBaoCaoGiangDay;
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

        setListeners();
    }

    private void setListeners() {
        topAppBarDashboard.setNavigationOnClickListener(v -> {
            finish();
        });

        topAppBarDashboard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.optionMenuExit) {
                    AlertDialog alertDialog = CustomAlertDialog.buildAlertDialogExit(Dashboard.this);
                    alertDialog.show();
                    return true;
                }
                return false;
            }
        });

        btnDSBaoCaoHocPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, DSBaoCaoHocPhan.class);
                startActivity(intent);
            }
        });

        btnBaoCaoHocPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, ChiTietBaoCaoHocPhan.class);
                startActivity(intent);
            }
        });

        btnBaoCaoGiangDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, BaoCaoGiangDayActivity.class);
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
        topAppBarDashboard = findViewById(R.id.topAppBarDashboard);
        optionMenuExit = findViewById(R.id.optionMenuExit);
        txtMaGiangVien = findViewById(R.id.txtMaGiangVien);
        txtTenGiangVien = findViewById(R.id.txtTenGiangVien);
        btnThoatTaiKhoan = findViewById(R.id.btnThoatTaiKhoan);
        btnDSBaoCaoHocPhan = findViewById(R.id.btnDSBaoCaoHocPhan);
        btnBaoCaoHocPhan = findViewById(R.id.btnBaoCaoHocPhan);
        btnBaoCaoGiangDay = findViewById(R.id.btnBaoCaoGiangDay);
    }
}