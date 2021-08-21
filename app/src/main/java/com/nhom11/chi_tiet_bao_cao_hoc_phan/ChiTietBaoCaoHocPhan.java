package com.nhom11.chi_tiet_bao_cao_hoc_phan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.nhom11.R;

import java.util.ArrayList;
import com.nhom11.models.HocPhan;
import com.nhom11.models.BaoCaoHocPhan;
public class ChiTietBaoCaoHocPhan extends AppCompatActivity {
    ListView lv;
    Button btnBack;
    BaoCaoHocPhan baoCaoHocPhan1,baoCaoHocPhan2;
    HocPhan hp1,hp2;
    ArrayAdapter<BaoCaoHocPhan> adapter1 = null, adapter2 = null;
    ArrayList<BaoCaoHocPhan> arrBaoCaoHP = null;
    ArrayList<HocPhan> arrHP = null;
    Spinner spinnerLoaiHP = null, spinnerHP=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao_hoc_phan);
        init();
        baoCaoHocPhan1 = new BaoCaoHocPhan(1,"IT01",3,4,"Lý thuyết");
        baoCaoHocPhan1 =new BaoCaoHocPhan(1,"IT01",3,4,"Thực hành");
        hp1 = new HocPhan("IT01","Android");
        hp2 = new HocPhan("IT02","PHP");
        //1. Tạo ArrayList object
        arrBaoCaoHP = new ArrayList<BaoCaoHocPhan>();
        arrBaoCaoHP.add(baoCaoHocPhan1);
        arrBaoCaoHP.add(baoCaoHocPhan2);
        arrHP = new ArrayList<HocPhan>();
        arrHP.add(hp1);
        arrHP.add(hp2);
        //2. Gán Data source vào ArrayAdapter
        adapter1 = new CustomListview(this, R.layout.bao_cao_hp_custom_listview, arrBaoCaoHP);
        adapter2 = new CustomSpinner(this, R.layout.bao_cao_hp_custom_spinner, arrHP);
        //3. Đưa Data source vào ListView
        lv.setAdapter(adapter1);
        //4. Đưa Data source vào Spinner
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHP.setAdapter(adapter2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void init(){
        ArrayList<String> arrLHP = new ArrayList<>();
        spinnerLoaiHP = findViewById(R.id.spinnerBCHP_LoaiHP);
        spinnerHP = findViewById(R.id.spinnerBCHP_HP);
        arrLHP.add("Lý thuyết");
        arrLHP.add("Thực hành");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrLHP);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiHP.setAdapter(arrayAdapter);
        lv = (ListView) findViewById(R.id.lvBCHP_BaoCaoGiangDay);
        btnBack = findViewById(R.id.btnBack);
    }
}