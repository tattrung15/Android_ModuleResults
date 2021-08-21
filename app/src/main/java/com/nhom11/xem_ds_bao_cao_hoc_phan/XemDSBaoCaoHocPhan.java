package com.nhom11.xem_ds_bao_cao_hoc_phan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.nhom11.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.models.HocPhan;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class XemDSBaoCaoHocPhan extends AppCompatActivity {
    MaterialToolbar topAppBar = null;
    ListView listView = null;
    BaoCaoHocPhanAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_ds_bao_cao_hoc_phan);
        getElements();
        fakeListViewData(); // xóa dòng này để làm data thật
        setListeners();
    }

    protected void getElements() {
        topAppBar = (MaterialToolbar) findViewById(R.id.topAppBar_DSBCHP);
        listView = (ListView) findViewById(R.id.listView_DSBCHP);
    }

    protected void setListeners() {
        topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    protected void fakeListViewData() {
        try {
            listView = (ListView) findViewById(R.id.listView_DSBCHP);
            adapter = new BaoCaoHocPhanAdapter(this, this.generateFakeBcaoHphan());
            listView.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    protected BaoCaoHocPhanDTO[] generateFakeBcaoHphan() throws Exception {
        BaoCaoHocPhanDTO baoCaoHocPhans[] = new BaoCaoHocPhanDTO[10];

        for (int i = 0; i < 10; i++) {
            BaoCaoHocPhanDTO bcaoHocPhan = new BaoCaoHocPhanDTO();
            bcaoHocPhan.setTongSoLop(i + 10);
            bcaoHocPhan.setMaHocPhan("MaHocPhan " + i);
            bcaoHocPhan.setTenHocPhan("TenHocPhan " + i);

            baoCaoHocPhans[i] = bcaoHocPhan;
        }

        return baoCaoHocPhans;
    }
}