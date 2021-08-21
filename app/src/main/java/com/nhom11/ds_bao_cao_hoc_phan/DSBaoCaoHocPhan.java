package com.nhom11.ds_bao_cao_hoc_phan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhom11.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.utils.CustomAlertDialog;

import java.util.List;

public class DSBaoCaoHocPhan extends AppCompatActivity {

    MenuItem topAppBarDSBaoCaoHP;
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

        listView = findViewById(R.id.listView_DSBCHP);
        adapter = new BaoCaoHocPhanAdapter(this, baoCaoHocPhanDTOs);
        listView.setAdapter(adapter);

        setListeners();
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

            }
        });
    }
}