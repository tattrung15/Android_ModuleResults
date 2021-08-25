package com.nhom11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhom11.dto.BaoCaoGiangDayDTO;
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
    private static final String TONG_SO_LOP_BCHP_COLUMN = "TongSoLop";
    private static final String TONG_SO_GIO_BCHP_COLUMN = "TongSoGio";
    private static final String LOAI_HP_COLUMN = "LoaiHocPhan";

    // BaoCaoGiangDay
    private static final String MA_BCGD_COLUMN = "MaBaoCaoGiangDay";
    private static final String SO_GIO_TREN_LOP_COLUMN = "SoGioTrenLop";
    private static final String SI_SO_BCGD_COLUMN = "SiSo";
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
                    MA_LH_COLUMN + " INTEGER NOT NULL," +
                    SO_GIO_TREN_LOP_COLUMN + " REAL NOT NULL," +
                    SI_SO_BCGD_COLUMN + " INTEGER NOT NULL," +
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

    public int getTotalRecord(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }

    public int getLastId(String tableName) {
        int lastId = 0;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + tableName;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToLast()) {
            lastId = cursor.getInt(0);
        }

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return lastId;
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

    public List<HocPhan> getAllHocPhan() {
        SQLiteDatabase db = getReadableDatabase();
        List<HocPhan> hocPhans = new ArrayList<>();
        Cursor cursor = db.query(TABLE_HOC_PHAN,
                new String[]{MA_HP_COLUMN, TEN_HP_COLUMN},
                null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                hocPhans.add(new HocPhan(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return hocPhans;
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

    public List<GiangVien> getAllGiangVien() {
        SQLiteDatabase db = getReadableDatabase();
        List<GiangVien> giangViens = new ArrayList<>();
        Cursor cursor = db.query(TABLE_GIANG_VIEN,
                new String[]{MA_GV_COLUMN, TEN_GV_COLUMN, USERNAME_COLUMN, PASSWORD_COLUMN},
                null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                giangViens.add(new GiangVien(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return giangViens;
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

    public List<LopHoc> getAllLopHoc() {
        SQLiteDatabase db = getReadableDatabase();
        List<LopHoc> lopHocs = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LOP_HOC,
                new String[]{MA_LH_COLUMN, TEN_LH_COLUMN, SI_SO_COLUMN},
                null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lopHocs.add(new LopHoc(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return lopHocs;
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

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return baoCaoHocPhanDTOs;
    }

    public int updateBaoCaoHocPhan(BaoCaoHocPhan baoCaoHocPhan) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TONG_SO_LOP_BCHP_COLUMN, baoCaoHocPhan.getTongSoLop());
        values.put(TONG_SO_GIO_BCHP_COLUMN, baoCaoHocPhan.getTongSoGio());
        values.put(LOAI_HP_COLUMN, baoCaoHocPhan.getLoaiHocPhan());
        int rowEffect = db.update(TABLE_BAO_CAO_HOC_PHAN, values, MA_BCHP_COLUMN + " = ?",
                new String[]{String.valueOf(baoCaoHocPhan.getMaBaoCaoHocPhan())});

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowEffect;
    }

    public int deleteBaoCaoHocPhan(int maBaoCaoHocPhan) {
        SQLiteDatabase db = getWritableDatabase();
        int rowEffect = db.delete(TABLE_BAO_CAO_HOC_PHAN, MA_BCHP_COLUMN + " = ?", new
                String[]{String.valueOf(maBaoCaoHocPhan)});

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowEffect;
    }

    // BaoCaoGiangDay
    public BaoCaoGiangDay insertBaoCaoGiangDay(BaoCaoGiangDay baoCaoGiangDay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_GV_COLUMN, baoCaoGiangDay.getMaGiangVien());
        values.put(MA_BCHP_COLUMN, baoCaoGiangDay.getMaBaoCaoHocPhan());
        values.put(MA_LH_COLUMN, baoCaoGiangDay.getMaLop());
        values.put(SO_GIO_TREN_LOP_COLUMN, baoCaoGiangDay.getSoGioTrenLop());
        values.put(SI_SO_BCGD_COLUMN, baoCaoGiangDay.getSiSo());
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
                cursor.getInt(3), cursor.getFloat(4), cursor.getInt(5), cursor.getInt(6),
                cursor.getString(7));
    }

    public List<BaoCaoGiangDayDTO> getAllBaoCaoGD() {
        SQLiteDatabase db = getReadableDatabase();
        List<BaoCaoGiangDayDTO> baoCaoGiangDayDTOs = new ArrayList<>();
        Cursor cursor = db.query(TABLE_BAO_CAO_HOC_PHAN + " , " + TABLE_HOC_PHAN + " , " +
                        TABLE_BAO_CAO_GIANG_DAY + " , " + TABLE_GIANG_VIEN + " , " + TABLE_LOP_HOC,
                new String[]{MA_BCGD_COLUMN, TABLE_GIANG_VIEN + "." + MA_GV_COLUMN, TEN_GV_COLUMN,
                        TABLE_BAO_CAO_HOC_PHAN + "." + MA_BCHP_COLUMN,
                        TABLE_HOC_PHAN + "." + MA_HP_COLUMN, TEN_HP_COLUMN,
                        TABLE_LOP_HOC + "." + MA_LH_COLUMN, TEN_LH_COLUMN,
                        SO_GIO_TREN_LOP_COLUMN, TABLE_BAO_CAO_GIANG_DAY + "." + SI_SO_COLUMN,
                        TABLE_LOP_HOC + "." + SI_SO_COLUMN, SO_TIET_MOT_NGAY_COLUMN, LOAI_TIET_COLUMN},
                TABLE_BAO_CAO_HOC_PHAN + "." + MA_HP_COLUMN +
                        " = " + TABLE_HOC_PHAN + "." + MA_HP_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_BCHP_COLUMN +
                        " = " + TABLE_BAO_CAO_HOC_PHAN + "." + MA_BCHP_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_GV_COLUMN +
                        " = " + TABLE_GIANG_VIEN + "." + MA_GV_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_LH_COLUMN +
                        " = " + TABLE_LOP_HOC + "." + MA_LH_COLUMN,
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                baoCaoGiangDayDTOs.add(new BaoCaoGiangDayDTO(cursor.getInt(0), cursor.getInt(1),
                        cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                        cursor.getString(5), cursor.getInt(6), cursor.getString(7),
                        cursor.getInt(8), cursor.getInt(9), cursor.getInt(10),
                        cursor.getInt(11), cursor.getString(12)));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return baoCaoGiangDayDTOs;
    }

    public int updateBaoCaoGiangDay(BaoCaoGiangDay baoCaoGiangDay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_GV_COLUMN, baoCaoGiangDay.getMaGiangVien());
        values.put(MA_BCHP_COLUMN, baoCaoGiangDay.getMaBaoCaoHocPhan());
        values.put(MA_LH_COLUMN, baoCaoGiangDay.getMaLop());
        values.put(SO_GIO_TREN_LOP_COLUMN, baoCaoGiangDay.getSoGioTrenLop());
        values.put(SI_SO_BCGD_COLUMN, baoCaoGiangDay.getSiSo());
        values.put(SO_TIET_MOT_NGAY_COLUMN, baoCaoGiangDay.getSoTietMotNgay());
        values.put(LOAI_TIET_COLUMN, baoCaoGiangDay.getLoaiTiet());
        int rowEffect = db.update(TABLE_BAO_CAO_GIANG_DAY, values, MA_BCGD_COLUMN + " = ?",
                new String[]{String.valueOf(baoCaoGiangDay.getMaBaoCaoGiangDay())});

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowEffect;
    }

    public int deleteBaoCaoGiangDay(int maBaoCaoGiangDay) {
        SQLiteDatabase db = getWritableDatabase();
        int rowEffect = db.delete(TABLE_BAO_CAO_GIANG_DAY, MA_BCGD_COLUMN + " = ?", new
                String[]{String.valueOf(maBaoCaoGiangDay)});

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return rowEffect;
    }

    public boolean checkExistsBCGD(int maBCHP, int maGiangVien, int maLopHoc) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_BAO_CAO_HOC_PHAN + " , " + TABLE_BAO_CAO_GIANG_DAY +
                        " , " + TABLE_GIANG_VIEN + " , " + TABLE_LOP_HOC,
                new String[]{MA_BCGD_COLUMN, TABLE_GIANG_VIEN + "." + MA_GV_COLUMN, TEN_GV_COLUMN,
                        TABLE_BAO_CAO_HOC_PHAN + "." + MA_BCHP_COLUMN,
                        TABLE_LOP_HOC + "." + MA_LH_COLUMN, TEN_LH_COLUMN,
                        SO_GIO_TREN_LOP_COLUMN, TABLE_BAO_CAO_GIANG_DAY + "." + SI_SO_COLUMN,
                        TABLE_LOP_HOC + "." + SI_SO_COLUMN, SO_TIET_MOT_NGAY_COLUMN, LOAI_TIET_COLUMN},
                TABLE_BAO_CAO_GIANG_DAY + "." + MA_BCHP_COLUMN +
                        " = " + TABLE_BAO_CAO_HOC_PHAN + "." + MA_BCHP_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_GV_COLUMN +
                        " = " + TABLE_GIANG_VIEN + "." + MA_GV_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_LH_COLUMN +
                        " = " + TABLE_LOP_HOC + "." + MA_LH_COLUMN + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_BCHP_COLUMN + " = ?" + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_GV_COLUMN + " = ?" + " AND " +
                        TABLE_BAO_CAO_GIANG_DAY + "." + MA_LH_COLUMN + " = ?",
                new String[]{String.valueOf(maBCHP), String.valueOf(maGiangVien), String.valueOf(maLopHoc)},
                null, null, null);

        if (ENV.compareTo("DEV") != 0) {
            db.close();
        }

        return cursor != null && cursor.moveToFirst();
    }
}
