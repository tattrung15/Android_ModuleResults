package com.nhom11.chi_tiet_bao_cao_hoc_phan;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.R;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.models.HocPhan;
import com.nhom11.utils.CustomAlertDialog;

import java.util.ArrayList;

public class ChiTietBaoCaoHocPhan extends AppCompatActivity {

    ListView listView;
    MaterialToolbar topAppBar;
    CustomListview adapterBCHP = null;
    CustomSpinner adapterHocPhan = null;
    ArrayList<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs = null;
    ArrayList<HocPhan> hocPhans = null;
    Spinner spinnerLoaiHP = null, spinnerHP = null;

    MyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao_hoc_phan);
        databaseHelper = new MyDatabaseHelper(getApplicationContext());

        getWidget();

        setListeners();
        setSpinnerHocPhan();

        baoCaoHocPhanDTOs = (ArrayList<BaoCaoHocPhanDTO>) databaseHelper.getAllBaoCaoHP();
        adapterBCHP = new CustomListview(this, R.layout.bao_cao_hp_custom_listview, baoCaoHocPhanDTOs);
        listView.setAdapter(adapterBCHP);
    }

    private void setSpinnerHocPhan() {
        hocPhans = (ArrayList<HocPhan>) databaseHelper.getAllHocPhan();
        adapterHocPhan = new CustomSpinner(this, R.layout.bao_cao_hp_custom_spinner, hocPhans);
        adapterHocPhan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHP.setAdapter(adapterHocPhan);
    }

    private void getWidget() {
        topAppBar = findViewById(R.id.topAppBar_CTBCHP);
        spinnerLoaiHP = findViewById(R.id.spinnerBCHP_LoaiHP);
        spinnerHP = findViewById(R.id.spinnerBCHP_HP);
        listView = findViewById(R.id.lvBCHP_BaoCaoGiangDay);

        ArrayList<String> arrLHP = new ArrayList<>();
        arrLHP.add("Bắt buộc");
        arrLHP.add("Tự chọn");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrLHP);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiHP.setAdapter(arrayAdapter);
    }

    private void setListeners() {
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.optionMenuExit) {
                    AlertDialog alertDialog = CustomAlertDialog.buildAlertDialogExit(ChiTietBaoCaoHocPhan.this);
                    alertDialog.show();
                    return true;
                }
                return false;
            }
        });
    }
}