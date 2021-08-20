package com.nhom11.xem_ds_bao_cao_hoc_phan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nhom11.R;
import com.google.android.material.appbar.MaterialToolbar;

public class XemDSBaoCaoHocPhan extends AppCompatActivity {
    MaterialToolbar topAppBar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ds_bao_cao_hoc_phan);
        getElements();
        setListeners();
    }

    protected void getElements() {
        topAppBar = findViewById(R.id.topAppBar);
    }

    protected void setListeners() {
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}