package com.example.managestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManagement.db";
    private static final int DATABASE_VERSION = 1;

    // Table Sinhvien
    private static final String TABLE_SINHVIEN = "Sinhvien";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_IDNGANH = "idNganh";

    // Table Nganh
    private static final String TABLE_NGANH = "Nganh";
    private static final String COLUMN_NAME_NGANH = "nameNganh";

    private static final String TABLE_SINHVIEN_CREATE =
            "CREATE TABLE " + TABLE_SINHVIEN + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_IDNGANH + " INTEGER, " +
                    "FOREIGN KEY (" + COLUMN_IDNGANH + ") REFERENCES " + TABLE_NGANH + "(" + COLUMN_IDNGANH + ")" +
                    ");";

    private static final String TABLE_NGANH_CREATE =
            "CREATE TABLE " + TABLE_NGANH + " (" +
                    COLUMN_IDNGANH + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_NGANH + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_SINHVIEN_CREATE);
        db.execSQL(TABLE_NGANH_CREATE);
    }

    public boolean addSinhVien(String name, String date, String gender, String address, int idNganh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_IDNGANH, idNganh);

        long result = db.insert(TABLE_SINHVIEN, null, values);
        db.close();
        return result != -1;
    }

    // Method to add Nganh
//    public boolean addNganh(String nameNganh) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_NGANH, nameNganh);
//
//        long result = db.insert(TABLE_NGANH, null, values);
//        db.close();
//        return result != -1;
//    }

    // Method to get all SinhVien
    public ArrayList<Student> getAllSinhVien() {
        ArrayList<Student> sinhVienList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SINHVIEN, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                int idNganh = cursor.getInt(cursor.getColumnIndex(COLUMN_IDNGANH));
                Student student = new Student(id, name, date, gender, address, idNganh);
                sinhVienList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sinhVienList;
    }

    // Method to update SinhVien
    public boolean updateSinhVien(int id, String name, String date, String gender, String address, int idNganh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_IDNGANH, idNganh);

        int result = db.update(TABLE_SINHVIEN, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // Method to update Nganh
//    public boolean updateNganh(int idNganh, String nameNganh) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME_NGANH, nameNganh);
//
//        int result = db.update(TABLE_NGANH, values, COLUMN_IDNGANH + " = ?", new String[]{String.valueOf(idNganh)});
//        db.close();
//        return result > 0;
//    }

    // Method to delete SinhVien
    public boolean deleteSinhVien(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_SINHVIEN, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // Method to delete Nganh
//    public boolean deleteNganh(int idNganh) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        int result = db.delete(TABLE_NGANH, COLUMN_IDNGANH + " = ?", new String[]{String.valueOf(idNganh)});
//        db.close();
//        return result > 0;
//    }

    // Method to validate SinhVien login
    public boolean validateSinhVienLogin(int id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SINHVIEN + " WHERE " + COLUMN_ID + " = ? AND " + COLUMN_NAME + " = ?", new String[]{String.valueOf(id), name});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean addNganh(String tenNganh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NGANH, tenNganh);

        long result = db.insert(TABLE_NGANH, null, contentValues);
        return result != -1;
    }

    public boolean updateNganh(int nganhId, String tenNganh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NGANH, tenNganh);

        int result = db.update(TABLE_NGANH, contentValues, COLUMN_IDNGANH + "=?", new String[]{String.valueOf(nganhId)});
        return result > 0;
    }

    public boolean deleteNganh(int nganhId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NGANH, COLUMN_IDNGANH + "=?", new String[]{String.valueOf(nganhId)});
        return result > 0;
    }

    public ArrayList<Nganh> getAllNganhs() {
        ArrayList<Nganh> nganhList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NGANH, null);

        if (cursor.moveToFirst()) {
            do {
                int nganhId = cursor.getInt(cursor.getColumnIndex(COLUMN_IDNGANH));
                String tenNganh = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NGANH));
                Nganh nganh = new Nganh(nganhId, tenNganh);
                nganhList.add(nganh);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return nganhList;
    }

    public static String getNganhNameById(int idNganh, DatabaseHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME_NGANH + " FROM " + TABLE_NGANH + " WHERE " + COLUMN_IDNGANH + " = ?", new String[]{String.valueOf(idNganh)});

        String nameNganh = null;
        if (cursor.moveToFirst()) {
            nameNganh = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NGANH));
        }

        cursor.close();
        db.close();
        return nameNganh;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGANH);
        onCreate(db);
    }
}
