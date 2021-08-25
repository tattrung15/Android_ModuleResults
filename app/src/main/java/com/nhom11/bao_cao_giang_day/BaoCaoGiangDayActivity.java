package com.nhom11.bao_cao_giang_day;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.nhom11.R;
import com.nhom11.database.MyDatabaseHelper;
import com.nhom11.dto.BaoCaoGiangDayDTO;
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.models.BaoCaoGiangDay;
import com.nhom11.models.GiangVien;
import com.nhom11.models.LopHoc;
import com.nhom11.utils.CustomAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class BaoCaoGiangDayActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    List<BaoCaoGiangDayDTO> baoCaoGiangDayDTOs;
    ArrayList<GiangVien> giangViens;
    ArrayList<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs;
    ArrayList<LopHoc> lopHocs;
    ArrayList<String> loaiTietHocs;
    EditText editSoGioTrenLop, editSiSo, editSoTietMotNgay;
    Button btnThem, btnSua;
    ListView listView;
    BaoCaoGiangDayAdapter baoCaoGiangDayAdapter;
    Spinner spinnerGiangVien, spinnerHocPhan, spinnerLopHoc, spinnerLoaiTietHoc;
    CustomSpinnerGiangVien customSpinnerGiangVien;
    CustomSpinnerLopHoc customSpinnerLopHoc;
    CustomSpinnerHocPhan customSpinnerHocPhan;

    MyDatabaseHelper databaseHelper;

    BaoCaoGiangDayDTO baoCaoGiangDayDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao_giang_day);

        databaseHelper = new MyDatabaseHelper(getApplicationContext());

        getWidgets();
        loadDataSpinner();
        loadDataListView();
        setListeners();
    }

    private void loadDataListView() {
        baoCaoGiangDayDTOs = databaseHelper.getAllBaoCaoGD();
        baoCaoGiangDayAdapter = new BaoCaoGiangDayAdapter(this,
                R.layout.item_listview_bao_cao_giang_day, baoCaoGiangDayDTOs);
        listView.setAdapter(baoCaoGiangDayAdapter);
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
        spinnerLopHoc = findViewById(R.id.spinnerTenLopBCGD);
        spinnerLoaiTietHoc = findViewById(R.id.spinnerLoaiTietHocBCGD);
    }

    public void loadDataSpinner() {
        giangViens = (ArrayList<GiangVien>) databaseHelper.getAllGiangVien();
        customSpinnerGiangVien = new CustomSpinnerGiangVien(this,
                R.layout.bao_cao_hp_custom_spinner, giangViens);
        spinnerGiangVien.setAdapter(customSpinnerGiangVien);

        baoCaoHocPhanDTOs = (ArrayList<BaoCaoHocPhanDTO>) databaseHelper.getAllBaoCaoHP();
        customSpinnerHocPhan = new CustomSpinnerHocPhan(this,
                R.layout.bao_cao_hp_custom_spinner, baoCaoHocPhanDTOs);
        spinnerHocPhan.setAdapter(customSpinnerHocPhan);

        lopHocs = (ArrayList<LopHoc>) databaseHelper.getAllLopHoc();
        customSpinnerLopHoc = new CustomSpinnerLopHoc(this,
                R.layout.bao_cao_hp_custom_spinner, lopHocs);
        spinnerLopHoc.setAdapter(customSpinnerLopHoc);

        loaiTietHocs = new ArrayList<>();
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
                if (editSoGioTrenLop.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Số giờ trên lớp không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editSiSo.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Sĩ số không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editSoTietMotNgay.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Số tiết một ngày không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                float soGioTrenLop = Float.parseFloat(editSoGioTrenLop.getText().toString());
                int siSo = Integer.parseInt(editSiSo.getText().toString());
                int soTietMotNgay = Integer.parseInt(editSoTietMotNgay.getText().toString());

                LopHoc lopHoc = (LopHoc) spinnerLopHoc.getSelectedItem();
                if (lopHoc.getSiSo() < siSo) {
                    Toast.makeText(getBaseContext(), "Sĩ số không vượt quá " + lopHoc.getSiSo(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                GiangVien giangVien = (GiangVien) spinnerGiangVien.getSelectedItem();
                BaoCaoHocPhanDTO baoCaoHocPhanDTO = (BaoCaoHocPhanDTO) spinnerHocPhan.getSelectedItem();

                if (databaseHelper.checkExistsBCGD(baoCaoHocPhanDTO.getMaBaoCaoHocPhan(),
                        giangVien.getMaGiangVien(), lopHoc.getMaLop())) {
                    AlertDialog alertDialogExists = CustomAlertDialog.buildAlertDialogExists(BaoCaoGiangDayActivity.this);
                    alertDialogExists.show();
                    return;
                }

                int increaseId = databaseHelper.getLastId(MyDatabaseHelper.TABLE_BAO_CAO_GIANG_DAY) + 1;
                BaoCaoGiangDay baoCaoGiangDay = new BaoCaoGiangDay(increaseId,
                        giangVien.getMaGiangVien(), baoCaoHocPhanDTO.getMaBaoCaoHocPhan(),
                        lopHoc.getMaLop(), soGioTrenLop, siSo, soTietMotNgay,
                        spinnerLoaiTietHoc.getSelectedItem().toString());

                try {
                    BaoCaoGiangDay baoCaoGiangDayNew = databaseHelper.insertBaoCaoGiangDay(baoCaoGiangDay);
                    BaoCaoGiangDayDTO baoCaoGiangDayDTO = new BaoCaoGiangDayDTO(baoCaoGiangDayNew.getMaBaoCaoGiangDay(),
                            baoCaoGiangDayNew.getMaGiangVien(), giangVien.getTenGiangVien(),
                            baoCaoGiangDayNew.getMaBaoCaoHocPhan(), baoCaoHocPhanDTO.getMaHocPhan(),
                            baoCaoHocPhanDTO.getTenHocPhan(), baoCaoGiangDayNew.getMaLop(),
                            lopHoc.getTenLop(), soGioTrenLop, siSo, lopHoc.getSiSo(), soTietMotNgay,
                            baoCaoGiangDayNew.getLoaiTiet());

                    if (baoCaoGiangDayNew != null) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công",
                                Toast.LENGTH_SHORT).show();
                        baoCaoGiangDayDTOs.add(baoCaoGiangDayDTO);
                        baoCaoGiangDayAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editSoGioTrenLop.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Số giờ trên lớp không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editSiSo.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Sĩ số không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editSoTietMotNgay.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Số tiết một ngày không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                float soGioTrenLop = Float.parseFloat(editSoGioTrenLop.getText().toString());
                int siSo = Integer.parseInt(editSiSo.getText().toString());
                int soTietMotNgay = Integer.parseInt(editSoTietMotNgay.getText().toString());

                LopHoc lopHoc = (LopHoc) spinnerLopHoc.getSelectedItem();
                if (lopHoc.getSiSo() < siSo) {
                    Toast.makeText(getBaseContext(), "Sĩ số không vượt quá " + lopHoc.getSiSo(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                GiangVien giangVien = (GiangVien) spinnerGiangVien.getSelectedItem();
                BaoCaoHocPhanDTO baoCaoHocPhanDTO = (BaoCaoHocPhanDTO) spinnerHocPhan.getSelectedItem();

                if (baoCaoGiangDayDTO.getMaGiangVien() != giangVien.getMaGiangVien() ||
                        baoCaoGiangDayDTO.getMaHocPhan().compareTo(baoCaoHocPhanDTO.getMaHocPhan()) != 0 ||
                        baoCaoGiangDayDTO.getMaLop() != lopHoc.getMaLop()) {
                    if (databaseHelper.checkExistsBCGD(baoCaoHocPhanDTO.getMaBaoCaoHocPhan(),
                            giangVien.getMaGiangVien(), lopHoc.getMaLop())) {
                        AlertDialog alertDialogExists = CustomAlertDialog.buildAlertDialogExists(BaoCaoGiangDayActivity.this);
                        alertDialogExists.show();
                        return;
                    }
                }

                baoCaoGiangDayDTO.setMaBaoCaoHocPhan(baoCaoHocPhanDTO.getMaBaoCaoHocPhan());
                baoCaoGiangDayDTO.setMaGiangVien(giangVien.getMaGiangVien());
                baoCaoGiangDayDTO.setMaHocPhan(baoCaoHocPhanDTO.getMaHocPhan());
                baoCaoGiangDayDTO.setMaLop(lopHoc.getMaLop());
                baoCaoGiangDayDTO.setTenGiangVien(giangVien.getTenGiangVien());
                baoCaoGiangDayDTO.setTenHocPhan(baoCaoHocPhanDTO.getTenHocPhan());
                baoCaoGiangDayDTO.setTenLop(lopHoc.getTenLop());
                baoCaoGiangDayDTO.setSoGioTrenLop(soGioTrenLop);
                baoCaoGiangDayDTO.setSiSoThucTe(siSo);
                baoCaoGiangDayDTO.setSiSoCoDinh(lopHoc.getSiSo());
                baoCaoGiangDayDTO.setSoTietMotNgay(soTietMotNgay);
                baoCaoGiangDayDTO.setLoaiTiet(spinnerLoaiTietHoc.getSelectedItem().toString());

                BaoCaoGiangDay baoCaoGiangDay = new BaoCaoGiangDay(baoCaoGiangDayDTO.getMaBaoCaoGiangDay(),
                        baoCaoGiangDayDTO.getMaGiangVien(), baoCaoGiangDayDTO.getMaBaoCaoHocPhan(),
                        baoCaoGiangDayDTO.getMaLop(), baoCaoGiangDayDTO.getSoGioTrenLop(),
                        baoCaoGiangDayDTO.getSiSoThucTe(), baoCaoGiangDayDTO.getSoTietMotNgay(),
                        baoCaoGiangDayDTO.getLoaiTiet());

                int rowEffect = databaseHelper.updateBaoCaoGiangDay(baoCaoGiangDay);
                if (rowEffect >= 0) {
                    Toast.makeText(getApplicationContext(), "Cập nhập thành công",
                            Toast.LENGTH_SHORT).show();
                    baoCaoGiangDayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Cập nhập thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                baoCaoGiangDayDTO = baoCaoGiangDayDTOs.get(i);

                int positionSpinnerGV = 0;
                for (int j = 0; j < giangViens.size(); j++) {
                    if (giangViens.get(j).getMaGiangVien().compareTo(baoCaoGiangDayDTO.getMaGiangVien()) == 0) {
                        positionSpinnerGV = j;
                        break;
                    }
                }
                spinnerGiangVien.setSelection(positionSpinnerGV);

                int positionSpinnerHP = 0;
                for (int j = 0; j < baoCaoHocPhanDTOs.size(); j++) {
                    if (baoCaoHocPhanDTOs.get(j).getMaHocPhan()
                            .compareTo(baoCaoGiangDayDTO.getMaHocPhan()) == 0) {
                        positionSpinnerHP = j;
                        break;
                    }
                }
                spinnerHocPhan.setSelection(positionSpinnerHP);

                int positionSpinnerLH = 0;
                for (int j = 0; j < lopHocs.size(); j++) {
                    if (lopHocs.get(j).getMaLop() == baoCaoGiangDayDTO.getMaLop()) {
                        positionSpinnerLH = j;
                        break;
                    }
                }
                spinnerLopHoc.setSelection(positionSpinnerLH);

                int positionSpinnerLTH = 0;
                for (int j = 0; j < loaiTietHocs.size(); j++) {
                    if (loaiTietHocs.get(j).compareTo(baoCaoGiangDayDTO.getLoaiTiet()) == 0) {
                        positionSpinnerLTH = j;
                        break;
                    }
                }
                spinnerLoaiTietHoc.setSelection(positionSpinnerLTH);

                editSoGioTrenLop.setText(String.valueOf(baoCaoGiangDayDTO.getSoGioTrenLop()));
                editSiSo.setText(String.valueOf(baoCaoGiangDayDTO.getSiSoThucTe()));
                editSoTietMotNgay.setText(String.valueOf(baoCaoGiangDayDTO.getSoTietMotNgay()));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaoCaoGiangDayDTO baoCaoGiangDayDTO = baoCaoGiangDayDTOs.get(i);

                int themeResId = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;
                AlertDialog.Builder b = new AlertDialog.Builder(BaoCaoGiangDayActivity.this, themeResId);
                b.setTitle("Xóa dữ liệu");
                b.setMessage("Bạn có đồng ý xóa dữ liệu không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            int rowEffect = databaseHelper.deleteBaoCaoGiangDay(baoCaoGiangDayDTO.getMaBaoCaoGiangDay());
                            if (rowEffect >= 1) {
                                Toast.makeText(getApplicationContext(), "Xóa thành công",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Xóa thất bại",
                                        Toast.LENGTH_SHORT).show();
                            }
                            baoCaoGiangDayDTOs.remove(i);
                            baoCaoGiangDayAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Xóa thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
                return true;
            }
        });
    }
}