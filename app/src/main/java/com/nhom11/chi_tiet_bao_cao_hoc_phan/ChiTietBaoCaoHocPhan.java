package com.nhom11.chi_tiet_bao_cao_hoc_phan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.models.BaoCaoHocPhan;
import com.nhom11.models.HocPhan;
import com.nhom11.utils.CustomAlertDialog;

import java.util.ArrayList;

public class ChiTietBaoCaoHocPhan extends AppCompatActivity {

    ListView listView;
    EditText editBCHPTongLop, editBCHPSoGio;
    Button btnBCHP_Them, btnBCHP_Sua;
    MaterialToolbar topAppBar;
    CustomListview adapterBCHP = null;
    CustomSpinner adapterHocPhan = null;
    ArrayList<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs = null;
    ArrayList<HocPhan> hocPhans = null;
    ArrayList<String> arrLoaiHocPhan = null;
    Spinner spinnerLoaiHP = null, spinnerHP = null;
    BaoCaoHocPhanDTO baoCaoHocPhanDTO = null;

    MyDatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bao_cao_hoc_phan);
        databaseHelper = new MyDatabaseHelper(getApplicationContext());

        getWidget();

        setListeners();
        setSpinnerHocPhan();

        Intent intent = getIntent();
        if (!intent.hasExtra("baoCaoHocPhanDTO")) {
            initListView();
        } else {
            btnBCHP_Them.setEnabled(false);
            btnBCHP_Them.setClickable(false);
            baoCaoHocPhanDTO = intent.getParcelableExtra("baoCaoHocPhanDTO");
            initListViewFromIntent();
            fillForm();
        }
    }

    private void fillForm() {
        int positionSpinnerHP = 0;
        for (int i = 0; i < hocPhans.size(); i++) {
            if (hocPhans.get(i).getMaHocPhan().compareTo(baoCaoHocPhanDTO.getMaHocPhan()) == 0) {
                positionSpinnerHP = i;
                break;
            }
        }
        spinnerHP.setSelection(positionSpinnerHP);
        spinnerHP.setEnabled(false);
        spinnerHP.setClickable(false);

        int positionSpinnerLHP = 0;
        for (int i = 0; i < arrLoaiHocPhan.size(); i++) {
            if (arrLoaiHocPhan.get(i).compareTo(baoCaoHocPhanDTO.getLoaiHocPhan()) == 0) {
                positionSpinnerLHP = i;
                break;
            }
        }
        spinnerLoaiHP.setSelection(positionSpinnerLHP);

        editBCHPTongLop.setText(String.valueOf(baoCaoHocPhanDTO.getTongSoLop()));
        editBCHPSoGio.setText(String.valueOf(baoCaoHocPhanDTO.getTongSoGio()));
    }

    private void initListView() {
        baoCaoHocPhanDTOs = (ArrayList<BaoCaoHocPhanDTO>) databaseHelper.getAllBaoCaoHP();
        adapterBCHP = new CustomListview(this, R.layout.bao_cao_hp_custom_listview, baoCaoHocPhanDTOs);
        listView.setAdapter(adapterBCHP);
    }

    private void initListViewFromIntent() {
        baoCaoHocPhanDTOs = new ArrayList<>();
        baoCaoHocPhanDTOs.add(baoCaoHocPhanDTO);
        adapterBCHP = new CustomListview(this, R.layout.bao_cao_hp_custom_listview, baoCaoHocPhanDTOs);
        listView.setAdapter(adapterBCHP);
        listView.setEnabled(false);
        listView.setClickable(false);
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
        btnBCHP_Them = findViewById(R.id.btnBCHP_Them);
        btnBCHP_Sua = findViewById(R.id.btnBCHP_Sua);
        editBCHPTongLop = findViewById(R.id.editBCHPTongLop);
        editBCHPSoGio = findViewById(R.id.editBCHPSoGio);

        arrLoaiHocPhan = new ArrayList<>();
        arrLoaiHocPhan.add("Bắt buộc");
        arrLoaiHocPhan.add("Tự chọn");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrLoaiHocPhan);
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

        btnBCHP_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editBCHPTongLop.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Tổng số lớp không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editBCHPSoGio.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getBaseContext(), "Tổng số giờ không hợp lệ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int tongSoLop = Integer.parseInt(editBCHPTongLop.getText().toString());
                float tongSoGio = Float.parseFloat(editBCHPSoGio.getText().toString());

                int increaseId = databaseHelper.getLastId(MyDatabaseHelper.TABLE_BAO_CAO_HOC_PHAN) + 1;
                HocPhan hocPhan = (HocPhan) spinnerHP.getSelectedItem();
                BaoCaoHocPhan baoCaoHocPhan = new BaoCaoHocPhan(increaseId, hocPhan.getMaHocPhan(),
                        tongSoLop, tongSoGio, spinnerLoaiHP.getSelectedItem().toString());

                try {
                    BaoCaoHocPhan baoCaoHocPhanNew = databaseHelper.insertBaoCaoHocPhan(baoCaoHocPhan);
                    BaoCaoHocPhanDTO baoCaoHocPhanDTONew = new BaoCaoHocPhanDTO(baoCaoHocPhanNew.getMaBaoCaoHocPhan(),
                            baoCaoHocPhanNew.getMaHocPhan(), hocPhan.getTenHocPhan(), tongSoLop,
                            tongSoGio, spinnerLoaiHP.getSelectedItem().toString());

                    if (baoCaoHocPhanNew != null) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công",
                                Toast.LENGTH_SHORT).show();
                        baoCaoHocPhanDTOs.add(baoCaoHocPhanDTONew);
                        adapterBCHP.notifyDataSetChanged();
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

        btnBCHP_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baoCaoHocPhanDTO != null) {

                    if (editBCHPTongLop.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getBaseContext(), "Tổng số lớp không hợp lệ",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (editBCHPSoGio.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getBaseContext(), "Tổng số giờ không hợp lệ",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int tongSoLop = Integer.parseInt(editBCHPTongLop.getText().toString());
                    float tongSoGio = Float.parseFloat(editBCHPSoGio.getText().toString());

                    if (tongSoLop <= 0 || tongSoGio <= 0) {
                        Toast.makeText(getBaseContext(), "Dữ liệu không hợp lệ",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    baoCaoHocPhanDTO.setTongSoLop(tongSoLop);
                    baoCaoHocPhanDTO.setTongSoGio(tongSoGio);
                    baoCaoHocPhanDTO.setLoaiHocPhan(spinnerLoaiHP.getSelectedItem().toString());

                    BaoCaoHocPhan baoCaoHocPhan = new BaoCaoHocPhan(baoCaoHocPhanDTO.getMaBaoCaoHocPhan(),
                            baoCaoHocPhanDTO.getMaHocPhan(), baoCaoHocPhanDTO.getTongSoLop(),
                            baoCaoHocPhanDTO.getTongSoGio(), baoCaoHocPhanDTO.getLoaiHocPhan());

                    int rowEffect = databaseHelper.updateBaoCaoHocPhan(baoCaoHocPhan);
                    if (rowEffect >= 0) {
                        Toast.makeText(getApplicationContext(), "Cập nhập thành công",
                                Toast.LENGTH_SHORT).show();
                        adapterBCHP.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Cập nhập thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                baoCaoHocPhanDTO = baoCaoHocPhanDTOs.get(i);

                int positionSpinnerHP = 0;
                for (int j = 0; j < hocPhans.size(); j++) {
                    if (hocPhans.get(j).getMaHocPhan().compareTo(baoCaoHocPhanDTO.getMaHocPhan()) == 0) {
                        positionSpinnerHP = j;
                        break;
                    }
                }
                spinnerHP.setSelection(positionSpinnerHP);

                int positionSpinnerLHP = 0;
                for (int j = 0; j < arrLoaiHocPhan.size(); j++) {
                    if (arrLoaiHocPhan.get(j).compareTo(baoCaoHocPhanDTO.getLoaiHocPhan()) == 0) {
                        positionSpinnerLHP = j;
                        break;
                    }
                }
                spinnerLoaiHP.setSelection(positionSpinnerLHP);

                editBCHPTongLop.setText(String.valueOf(baoCaoHocPhanDTO.getTongSoLop()));
                editBCHPSoGio.setText(String.valueOf(baoCaoHocPhanDTO.getTongSoGio()));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                BaoCaoHocPhanDTO baoCaoHocPhanDTO = baoCaoHocPhanDTOs.get(i);

                int themeResId = android.R.style.Theme_DeviceDefault_Light_Dialog_Alert;
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietBaoCaoHocPhan.this, themeResId);
                b.setTitle("Xóa dữ liệu");
                b.setMessage("Bạn có đồng ý xóa dữ liệu không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            int rowEffect = databaseHelper.deleteBaoCaoHocPhan(baoCaoHocPhanDTO.getMaBaoCaoHocPhan());
                            if (rowEffect >= 1) {
                                Toast.makeText(getApplicationContext(), "Xóa thành công",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Xóa thất bại",
                                        Toast.LENGTH_SHORT).show();
                            }
                            baoCaoHocPhanDTOs.remove(i);
                            adapterBCHP.notifyDataSetChanged();
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