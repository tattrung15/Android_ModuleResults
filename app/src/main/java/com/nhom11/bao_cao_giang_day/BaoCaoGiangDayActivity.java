package com.nhom11.bao_cao_giang_day;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nhom11.R;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.models.BaoCaoGiangDay;

import java.util.ArrayList;

public class BaoCaoGiangDayActivity extends AppCompatActivity {

    ArrayList<BaoCaoGiangDay> baoCaoGiangDays;
    BaoCaoGiangDay baoCaoGiangDay;
    String arrGiangVien[] = {"Vũ Thị Dương", "Hà Mạnh Đào", "Nguyễn Mạnh Cường", "Đỗ Tuấn Sơn"};
    String arrTenHocPhan[] = {"Android", "Java nâng cao", "ASP.NET", "PHP"};
    String arrTenLop[] = {"KTPM1", "KTPM2", "KTPM3"};
    String arrLoaiTietHoc[] = {"Lý thuyết", "Thực hành", "Kết hợp"};
    EditText editTongLopBCGD, editSoGioBCGD, editSiSoBCGD, editSoTietBCGD;
    Button btnThemBCGD, btnSuaBCGD, btnThemBaoCaoBCGD;
    ListView lvBCGD;
    ArrayAdapter<BaoCaoGiangDay> adapterBaoCaoGiangDay;
    Spinner spinnerGiangVienBCGD, spinnerTenHocPhanBCGD, spinnerTenLopBCGD, spinnerLoaiTietHocBCGD;

    MyDatabaseHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_giang_day);

        widgets();
        loadData();
        onProcess();
    }

    private void widgets() {
        editTongLopBCGD = findViewById(R.id.editTongLopBCGD);
        editSoGioBCGD = findViewById(R.id.editSoGioBCGD);
        editSiSoBCGD = findViewById(R.id.editSiSoBCGD);
        editSoTietBCGD = findViewById(R.id.editSoTietBCGD);

        btnThemBCGD = findViewById(R.id.btnThemBCGD);
        btnSuaBCGD = findViewById(R.id.btnSuaBCGD);
        btnThemBaoCaoBCGD = findViewById(R.id.btnThemBaoCaoBCGD);
        lvBCGD = findViewById(R.id.lvBCGD);
        spinnerGiangVienBCGD = findViewById(R.id.spinnerGiangVienBCGD);
        spinnerTenHocPhanBCGD = findViewById(R.id.spinnerTenHocPhanBCGD);
        spinnerTenLopBCGD = findViewById(R.id.spinnerTenLopBCGD);
        spinnerLoaiTietHocBCGD = findViewById(R.id.spinnerLoaiTietHocBCGD);

        baoCaoGiangDays = new ArrayList<>();

        ArrayAdapter adapterGiangVien = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrGiangVien);
        ArrayAdapter adapterTenHocPhan = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrTenHocPhan);
        ArrayAdapter adapterTenLop = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrTenLop);
        ArrayAdapter adapterLoaiTietHoc = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrLoaiTietHoc);
        spinnerGiangVienBCGD.setAdapter(adapterGiangVien);
        spinnerTenHocPhanBCGD.setAdapter(adapterTenHocPhan);
        spinnerTenLopBCGD.setAdapter(adapterTenLop);
        spinnerLoaiTietHocBCGD.setAdapter(adapterLoaiTietHoc);
    }

    public void loadData() {
        if (sqLiteHelper == null) {
            sqLiteHelper = MyDatabaseHelper.getInstance(BaoCaoGiangDayActivity.this);
        }

        if(baoCaoGiangDays.size() == 0){
            baoCaoGiangDays.add(new BaoCaoGiangDay(1,2,3,4,5,3,5,"Lý thuyết"));
            baoCaoGiangDays.add(new BaoCaoGiangDay(2,1,3,4,5,3,5,"Thực hành"));
            baoCaoGiangDays.add(new BaoCaoGiangDay(3,4,3,4,5,3,5,"Kết hợp"));

            for (int i = 0; i < baoCaoGiangDays.size(); i++) {
                BaoCaoGiangDay baoCaoGiangDay1 = baoCaoGiangDays.get(i);
                sqLiteHelper.insertBaoCaoGiangDay(baoCaoGiangDay1);
            }
        }

        adapterBaoCaoGiangDay = new ArrayAdapter<BaoCaoGiangDay>(BaoCaoGiangDayActivity.this, R.layout.support_simple_spinner_dropdown_item, baoCaoGiangDays);
        lvBCGD.setAdapter(adapterBaoCaoGiangDay);

    }


    private void onProcess() {
        btnThemBCGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        btnSuaBCGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        lvBCGD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                baoCaoGiangDay = baoCaoGiangDays.get(position);

                AlertDialog alertDialog = new AlertDialog.Builder(BaoCaoGiangDayActivity.this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn xóa không")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                                Toast.makeText(BaoCaoGiangDayActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(BaoCaoGiangDayActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();

                alertDialog.show();
                return false;
            }
        });
    }
}