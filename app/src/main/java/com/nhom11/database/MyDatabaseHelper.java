package com.nhom11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhom11.dto.BaoCaoHocPhanDTO;
import com.nhom11.models.BaoCaoGiangDay;
import com.nhom11.models.BaoCaoHocPhan;
import com.nhom11.models.GiangVien;
import com.nhom11.models.HocPhan;
import com.nhom11.models.LopHoc;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String ENV = "DEV";

    private static final String TAG = "MyDatabaseHelper";

    private static final String DATABASE_NAME = "MODULE_RESULTS_DB";

    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_HOC_PHAN = "HocPhan";
    public static final String TABLE_GIANG_VIEN = "GiangVien";
    public static final String TABLE_LOP_HOC = "LopHoc";
    public static final String TABLE_BAO_CAO_HOC_PHAN = "BaoCaoHocPhan";
    public static final String TABLE_BAO_CAO_GIANG_DAY = "BaoCaoGiangDay";

    // HocPhan
    private static final String MA_HP_COLUMN = "MaHocPhan";
    private static final String TEN_HP_COLUMN = "TenHocPhan";

    // GiangVien
    private static final String MA_GV_COLUMN = "MaGiangVien";
    private static final String TEN_GV_COLUMN = "TenGiangVien";
    private static final String USERNAME_COLUMN = "Username";
    private static final String PASSWORD_COLUMN = "Password";

    // LopHoc
    private static final String MA_LH_COLUMN = "MaLop";
    private static final String TEN_LH_COLUMN = "TenLop";
    private static final String SI_SO_COLUMN = "SiSo";

    // BaoCaoHocPhan
    private static final String MA_BCHP_COLUMN = "MaBaoCaoHocPhan";
    private static final String TONG_SO_LOP_BCHP_COLUMN = "TongSoLopBCHP";
    private static final String TONG_SO_GIO_BCHP_COLUMN = "TongSoGioBCHP";
    private static final String LOAI_HP_COLUMN = "LoaiHocPhan";

    // BaoCaoGiangDay
    private static final String MA_BCGD_COLUMN = "MaBaoCaoGiangDay";
    private static final String TONG_SO_LOP_BCGD_COLUMN = "TongSoLopBCGD";
    private static final String TONG_SO_GIO_BCGD_COLUMN = "TongSoGioBCGD";
    private static final String SO_TIET_MOT_NGAY_COLUMN = "SoTietMotNgay";
    private static final String LOAI_TIET_COLUMN = "LoaiTiet";

    private static final String CREATE_HOC_PHAN_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HOC_PHAN + " (" +
                    MA_HP_COLUMN + " TEXT PRIMARY KEY NOT NULL," +
                    TEN_HP_COLUMN + " TEXT NOT NULL" +
                    ")";

    private static final String CREATE_GIANG_VIEN_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_GIANG_VIEN + " (" +
                    MA_GV_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TEN_GV_COLUMN + " TEXT NOT NULL," +
                    USERNAME_COLUMN + " TEXT UNIQUE NOT NULL," +
                    PASSWORD_COLUMN + " TEXT NOT NULL" +
                    ")";

    private static final String CREATE_LOP_HOC_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_LOP_HOC + " (" +
                    MA_LH_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TEN_LH_COLUMN + " TEXT UNIQUE NOT NULL," +
                    SI_SO_COLUMN + " TEXT NOT NULL" +
                    ")";

    private static final String CREATE_BAO_CAO_HOC_PHAN_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BAO_CAO_HOC_PHAN + " (" +
                    MA_BCHP_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    MA_HP_COLUMN + " TEXT UNIQUE," +
                    TONG_SO_LOP_BCHP_COLUMN + " INTEGER NOT NULL," +
                    TONG_SO_GIO_BCHP_COLUMN + " REAL NOT NULL," +
                    LOAI_HP_COLUMN + " TEXT NOT NULL," +
                    "FOREIGN KEY(" + MA_HP_COLUMN + ")REFERENCES " +
                    TABLE_HOC_PHAN + "(" + MA_HP_COLUMN + ")" +
                    ")";

    private static final String CREATE_BAO_CAO_GIANG_DAY_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BAO_CAO_GIANG_DAY + " (" +
                    MA_BCGD_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    MA_GV_COLUMN + " INTEGER NOT NULL," +
                    MA_BCHP_COLUMN + " INTEGER NOT NULL," +
                    MA_LH_COLUMN + " INTEGER UNIQUE," +
                    TONG_SO_LOP_BCGD_COLUMN + " INTEGER NOT NULL," +
                    TONG_SO_GIO_BCGD_COLUMN + " REAL NOT NULL," +
                    SO_TIET_MOT_NGAY_COLUMN + " INTEGER NOT NULL," +
                    LOAI_TIET_COLUMN + " TEXT NOT NULL," +
                    "FOREIGN KEY(" + MA_LH_COLUMN + ")REFERENCES " +
                    TABLE_LOP_HOC + "(" + MA_LH_COLUMN + ")," +
                    "FOREIGN KEY(" + MA_GV_COLUMN + ")REFERENCES " +
                    TABLE_GIANG_VIEN + "(" + MA_GV_COLUMN + ")," +
                    "FOREIGN KEY(" + MA_BCHP_COLUMN + ")REFERENCES " +
                    TABLE_BAO_CAO_HOC_PHAN + "(" + MA_BCHP_COLUMN + ")" +
                    ")";

    private static MyDatabaseHelper sInstance;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MyDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e(TAG, "onCreate: ");
        try {
            sqLiteDatabase.execSQL(CREATE_HOC_PHAN_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_GIANG_VIEN_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_LOP_HOC_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_BAO_CAO_HOC_PHAN_TABLE_SQL);
            sqLiteDatabase.execSQL(CREATE_BAO_CAO_GIANG_DAY_TABLE_SQL);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BAO_CAO_GIANG_DAY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BAO_CAO_HOC_PHAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOP_HOC);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GIANG_VIEN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HOC_PHAN);
        onCreate(sqLiteDatabase);
    }

    public int getTotalRecord(String TABLE_NAME) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }

    // HocPhan
    public boolean insertHocPhan(HocPhan hocPhan) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_HP_COLUMN, hocPhan.getMaHocPhan());
        values.put(TEN_HP_COLUMN, hocPhan.getTenHocPhan());
        long rowId = db.insert(TABLE_HOC_PHAN, null, values);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowId != -1;
    }

    // GiangVien
    public boolean insertGiangVien(GiangVien giangVien) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_GV_COLUMN, giangVien.getMaGiangVien());
        values.put(TEN_GV_COLUMN, giangVien.getTenGiangVien());
        values.put(USERNAME_COLUMN, giangVien.getUsername());
        values.put(PASSWORD_COLUMN, giangVien.getPassword());
        long rowId = db.insert(TABLE_GIANG_VIEN, null, values);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowId != -1;
    }

    public GiangVien getGiangVienByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        GiangVien giangVien = null;
        Cursor cursor = db.query(TABLE_GIANG_VIEN, new String[]{MA_GV_COLUMN,
                        TEN_GV_COLUMN, USERNAME_COLUMN, PASSWORD_COLUMN},
                USERNAME_COLUMN + " = ?",
                new String[]{username}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            giangVien = new GiangVien(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
            cursor.close();
        }

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return giangVien;
    }

    // LopHoc
    public LopHoc insertLopHoc(LopHoc lopHoc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_LH_COLUMN, lopHoc.getTenLop());
        values.put(SI_SO_COLUMN, lopHoc.getSiSo());
        long rowId = db.insert(TABLE_LOP_HOC, null, values);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        if (rowId <= -1) {
            return null;
        }

        SQLiteDatabase dbRead = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LOP_HOC;
        Cursor cursor = dbRead.rawQuery(selectQuery, null);
        cursor.moveToLast();

        return new LopHoc(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
    }

    // BaoCaoHocPhan
    public BaoCaoHocPhan insertBaoCaoHocPhan(BaoCaoHocPhan baoCaoHocPhan) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_HP_COLUMN, baoCaoHocPhan.getMaHocPhan());
        values.put(TONG_SO_LOP_BCHP_COLUMN, baoCaoHocPhan.getTongSoLop());
        values.put(TONG_SO_GIO_BCHP_COLUMN, baoCaoHocPhan.getTongSoGio());
        values.put(LOAI_HP_COLUMN, baoCaoHocPhan.getLoaiHocPhan());
        long rowId = db.insert(TABLE_BAO_CAO_HOC_PHAN, null, values);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        if (rowId <= -1) {
            return null;
        }

        SQLiteDatabase dbRead = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BAO_CAO_HOC_PHAN;
        Cursor cursor = dbRead.rawQuery(selectQuery, null);
        cursor.moveToLast();

        return new BaoCaoHocPhan(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                cursor.getFloat(3), cursor.getString(4));
    }

    public List<BaoCaoHocPhanDTO> getAllBaoCaoHP() {
        SQLiteDatabase db = getReadableDatabase();
        List<BaoCaoHocPhanDTO> baoCaoHocPhanDTOs = new ArrayList<>();
        Cursor cursor = db.query(TABLE_BAO_CAO_HOC_PHAN + " , " + TABLE_HOC_PHAN,
                new String[]{MA_BCHP_COLUMN, TABLE_HOC_PHAN + "." + MA_HP_COLUMN, TEN_HP_COLUMN,
                        TONG_SO_LOP_BCHP_COLUMN, TONG_SO_GIO_BCHP_COLUMN, LOAI_HP_COLUMN},
                TABLE_BAO_CAO_HOC_PHAN + "." + MA_HP_COLUMN +
                        " = " + TABLE_HOC_PHAN + "." + MA_HP_COLUMN,
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                baoCaoHocPhanDTOs.add(
                        new BaoCaoHocPhanDTO(cursor.getInt(0), cursor.getString(1),
                                cursor.getString(2), cursor.getInt(3), cursor.getFloat(4),
                                cursor.getString(5)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return baoCaoHocPhanDTOs;
    }

    // BaoCaoGiangDay
    public BaoCaoGiangDay insertBaoCaoGiangDay(BaoCaoGiangDay baoCaoGiangDay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_GV_COLUMN, baoCaoGiangDay.getMaGiangVien());
        values.put(MA_BCHP_COLUMN, baoCaoGiangDay.getMaBaoCaoHocPhan());
        values.put(MA_LH_COLUMN, baoCaoGiangDay.getMaLop());
        values.put(TONG_SO_LOP_BCGD_COLUMN, baoCaoGiangDay.getTongSoLop());
        values.put(TONG_SO_GIO_BCGD_COLUMN, baoCaoGiangDay.getTongSoGio());
        values.put(SO_TIET_MOT_NGAY_COLUMN, baoCaoGiangDay.getSoTietMotNgay());
        values.put(LOAI_TIET_COLUMN, baoCaoGiangDay.getLoaiTiet());
        long rowId = db.insert(TABLE_BAO_CAO_GIANG_DAY, null, values);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        if (rowId <= -1) {
            return null;
        }

        SQLiteDatabase dbRead = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BAO_CAO_GIANG_DAY;
        Cursor cursor = dbRead.rawQuery(selectQuery, null);
        cursor.moveToLast();

        return new BaoCaoGiangDay(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                cursor.getInt(3), cursor.getInt(4), cursor.getFloat(5), cursor.getInt(6),
                cursor.getString(7));
    }
}
