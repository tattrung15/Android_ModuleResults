package com.nhom11.ds_bao_cao_hoc_phan;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.R;
import com.nhom11.chi_tiet_bao_cao_hoc_phan.ChiTietBaoCaoHocPhan;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.utils.CustomAlertDialog;

import java.util.List;

public class DSBaoCaoHocPhan extends AppCompatActivity {

    MaterialToolbar topAppBar = null;
    ListView listView = null;
    BaoCaoHocPhanAdapter adapter = null;
    List<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs = null;
    MyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_bao_cao_hoc_phan);

        databaseHelper = new MyDatabaseHelper(getApplicationContext());

        baoCaoHocPhanDTOs = databaseHelper.getAllBaoCaoHP();

        getElements();

        adapter = new BaoCaoHocPhanAdapter(this, baoCaoHocPhanDTOs);
        listView.setAdapter(adapter);

        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        baoCaoHocPhanDTOs = databaseHelper.getAllBaoCaoHP();
        adapter = new BaoCaoHocPhanAdapter(this, baoCaoHocPhanDTOs);
        listView.setAdapter(adapter);
    }

    protected void getElements() {
        topAppBar = findViewById(R.id.topAppBar_DSBCHP);
        listView = findViewById(R.id.listView_DSBCHP);
    }

    protected void setListeners() {
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.optionMenuExit) {
                    AlertDialog alertDialog = CustomAlertDialog.buildAlertDialogExit(DSBaoCaoHocPhan.this);
                    alertDialog.show();
                    return true;
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaoCaoHocPhanDTO baoCaoHocPhanDTO = baoCaoHocPhanDTOs.get(i);
                Intent intent = new Intent(DSBaoCaoHocPhan.this, ChiTietBaoCaoHocPhan.class);
                intent.putExtra("baoCaoHocPhanDTO", baoCaoHocPhanDTO);
                startActivity(intent);
            }
        });
    }
}