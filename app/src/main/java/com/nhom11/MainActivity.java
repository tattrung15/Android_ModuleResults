package com.nhom11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nhom11.dashboard.Dashboard;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.models.BaoCaoGiangDay;
import com.nhom11.models.BaoCaoHocPhan;
import com.nhom11.models.GiangVien;
import com.nhom11.models.HocPhan;
import com.nhom11.models.LopHoc;
import com.nhom11.xem_ds_bao_cao_hoc_phan.XemDSBaoCaoHocPhan;

public class MainActivity extends AppCompatActivity {

    Button btnLogIn;
    MyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWidget();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

        databaseHelper = MyDatabaseHelper.getInstance(getApplicationContext());

        initHocPhan();
        initGiangVien();

//        initDataDemo();
    }

    private void initDataDemo() {
        LopHoc lopHoc = new LopHoc(1, "KTPM2", 80);

        databaseHelper.insertLopHoc(lopHoc);

        BaoCaoHocPhan baoCaoHocPhan = new BaoCaoHocPhan(1, "IT6029",
                5, 4, "Tự chọn");
        databaseHelper.insertBaoCaoHocPhan(baoCaoHocPhan);

        BaoCaoGiangDay baoCaoGiangDay = new BaoCaoGiangDay(1, 1,
                1, 1, 7, 8,
                8, "Lý thuyết");

        BaoCaoGiangDay baoCaoGiangDay1 = databaseHelper.insertBaoCaoGiangDay(baoCaoGiangDay);

        Log.e("------------------", baoCaoGiangDay1.toString());
    }

    private void initHocPhan() {
        if (databaseHelper.getTotalRecord(MyDatabaseHelper.TABLE_HOC_PHAN) == 0) {
            HocPhan hocPhan1 = new HocPhan("IT6029",
                    "Phát triển ứng dụng trên thiết bị di động");
            HocPhan hocPhan2 = new HocPhan("IT6030", "Phần mềm mã nguồn mở");
            HocPhan hocPhan3 = new HocPhan("IT6019", "Lập trình Java");
            databaseHelper.insertHocPhan(hocPhan1);
            databaseHelper.insertHocPhan(hocPhan2);
            databaseHelper.insertHocPhan(hocPhan3);
        }
    }

    private void initGiangVien() {
        if (databaseHelper.getTotalRecord(MyDatabaseHelper.TABLE_GIANG_VIEN) == 0) {
            GiangVien giangVien1 = new GiangVien(1, "Vũ Thị Dương",
                    "admin", "admin");
            GiangVien giangVien2 = new GiangVien(2, "Nguyễn Thái Cường",
                    "admin1", "admin");
            GiangVien giangVien3 = new GiangVien(3, "Nguyễn Đức Lưu",
                    "admin2", "admin");
            databaseHelper.insertGiangVien(giangVien1);
            databaseHelper.insertGiangVien(giangVien2);
            databaseHelper.insertGiangVien(giangVien3);
        }
    }

    private void getWidget() {
        btnLogIn = findViewById(R.id.btnLogIn);
    }
}