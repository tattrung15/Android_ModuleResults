package com.nhom11.chi_tiet_bao_cao_hoc_phan;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

        btnBCHP_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baoCaoHocPhanDTO != null) {

                    if (editBCHPTongLop.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getBaseContext(), "Tổng số lớp không được để trống",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (editBCHPSoGio.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getBaseContext(), "Tổng số giờ không được để trống",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    baoCaoHocPhanDTO.setTongSoLop(Integer.parseInt(editBCHPTongLop.getText().toString()));
                    baoCaoHocPhanDTO.setTongSoGio(Float.parseFloat(editBCHPSoGio.getText().toString()));
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
    }
}