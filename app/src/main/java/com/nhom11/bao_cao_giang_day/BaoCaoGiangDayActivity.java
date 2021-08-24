package com.nhom11.bao_cao_giang_day;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.R;
import com.nhom11.chi_tiet_bao_cao_hoc_phan.CustomSpinner;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.dto.BaoCaoGiangDayDTO;
import com.nhom11.models.BaoCaoGiangDay;
import com.nhom11.models.GiangVien;
import com.nhom11.models.HocPhan;
import com.nhom11.models.LopHoc;
import com.nhom11.utils.CustomAlertDialog;

import java.util.ArrayList;

public class BaoCaoGiangDayActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    ArrayList<BaoCaoGiangDayDTO> baoCaoGiangDayDTOs;
    ArrayList<GiangVien> giangViens;
    ArrayList<HocPhan> hocPhans;
    ArrayList<LopHoc> lopHocs;
    EditText editSoGioTrenLop, editSiSo, editSoTietMotNgay;
    Button btnThem, btnSua;
    ListView listView;
    ArrayAdapter<BaoCaoGiangDay> baoCaoGiangDayArrayAdapter;
    Spinner spinnerGiangVien, spinnerHocPhan, spinnerTenLop, spinnerLoaiTietHoc;
    CustomSpinnerGiangVien customSpinnerGiangVien;
    CustomSpinnerLopHoc customSpinnerLopHoc;
    CustomSpinner customSpinnerHocPhan;

    MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_giang_day);

        databaseHelper = new MyDatabaseHelper(getApplicationContext());

        getWidgets();
        loadDataSpinner();
        setListeners();
    }

    private void getWidgets() {
        topAppBar = findViewById(R.id.topAppBarBCGD);
        editSoGioTrenLop = findViewById(R.id.editSoGioTrenLopBCGD);
        editSiSo = findViewById(R.id.editSiSoBCGD);
        editSoTietMotNgay = findViewById(R.id.editSoTietMotNgayBCGD);
        btnThem = findViewById(R.id.btnThemBCGD);
        btnSua = findViewById(R.id.btnSuaBCGD);
        listView = findViewById(R.id.lvBCGD);

        spinnerGiangVien = findViewById(R.id.spinnerGiangVienBCGD);
        spinnerHocPhan = findViewById(R.id.spinnerTenHocPhanBCGD);
        spinnerTenLop = findViewById(R.id.spinnerTenLopBCGD);
        spinnerLoaiTietHoc = findViewById(R.id.spinnerLoaiTietHocBCGD);
    }

    public void loadDataSpinner() {
        giangViens = (ArrayList<GiangVien>) databaseHelper.getAllGiangVien();
        customSpinnerGiangVien = new CustomSpinnerGiangVien(this,
                R.layout.bao_cao_hp_custom_spinner, giangViens);
        spinnerGiangVien.setAdapter(customSpinnerGiangVien);

        hocPhans = (ArrayList<HocPhan>) databaseHelper.getAllHocPhan();
        customSpinnerHocPhan = new CustomSpinner(this,
                R.layout.bao_cao_hp_custom_spinner, hocPhans);
        spinnerHocPhan.setAdapter(customSpinnerHocPhan);

        lopHocs = (ArrayList<LopHoc>) databaseHelper.getAllLopHoc();
        customSpinnerLopHoc = new CustomSpinnerLopHoc(this,
                R.layout.bao_cao_hp_custom_spinner, lopHocs);
        spinnerTenLop.setAdapter(customSpinnerLopHoc);

        ArrayList<String> loaiTietHocs = new ArrayList<>();
        loaiTietHocs.add("Lý thuyết");
        loaiTietHocs.add("Thực hành");
        ArrayAdapter<String> loaiTietHocAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, loaiTietHocs);
        spinnerLoaiTietHoc.setAdapter(loaiTietHocAdapter);
    }


    private void setListeners() {
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.optionMenuExit) {
                    AlertDialog alertDialog = CustomAlertDialog.buildAlertDialogExit(BaoCaoGiangDayActivity.this);
                    alertDialog.show();
                    return true;
                }
                return false;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }
}