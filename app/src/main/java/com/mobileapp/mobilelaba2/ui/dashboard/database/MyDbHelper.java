package com.mobileapp.mobilelaba2.ui.dashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MyConstants.DROP_TABLE);
        onCreate(db);
    }

    public void addStudent(StudentCourses studentCourses){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.COLUMN_NAME_PIB, studentCourses.getPib());
        cv.put(MyConstants.COLUMN_NAME_NAME, studentCourses.getName());
        cv.put(MyConstants.COLUMN_NAME_GRADE, studentCourses.getGrade());
        cv.put(MyConstants.COLUMN_NAME_ADDRESS, studentCourses.getAdress());

        db.insert(MyConstants.TABLE_NAME, null, cv);
        db.close();
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyConstants.TABLE_NAME, MyConstants.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(studentId)});
        db.close();
    }

    public StudentCourses getStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(MyConstants.TABLE_NAME,
                new String[] {MyConstants.COLUMN_NAME_ID, MyConstants.COLUMN_NAME_PIB,
                        MyConstants.COLUMN_NAME_NAME, MyConstants.COLUMN_NAME_GRADE,
                        MyConstants.COLUMN_NAME_ADDRESS},
                MyConstants.COLUMN_NAME_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        StudentCourses studentCourses = new StudentCourses(Integer.parseInt(cursor.getString(0)),
                 cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
        db.close();
        return studentCourses;
    }

    public List<StudentCourses> getAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<StudentCourses> studentCoursesList = new ArrayList<>();

        String selectAllStudents = "SELECT * FROM " + MyConstants.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);

        if(cursor.moveToFirst()){
            do {
                StudentCourses studentCourses = new StudentCourses();
                studentCourses.setId(Integer.parseInt(cursor.getString(0)));
                studentCourses.setPib(cursor.getString(1));
                studentCourses.setName(cursor.getString(2));
                studentCourses.setGrade(cursor.getString(3));
                studentCourses.setAdress(cursor.getString(4));
                studentCoursesList.add(studentCourses);
            } while(cursor.moveToNext());
        }
        return studentCoursesList;
    }

    public List<StudentCourses> getStudentsAbove60() {
        List<StudentCourses> studentsAbove60 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MyConstants.TABLE_NAME +
                " WHERE (" + MyConstants.COLUMN_NAME_GRADE + ") > 60" +
                " AND (" + MyConstants.COLUMN_NAME_PIB + ") = 'Кондратюк Ю.В.'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StudentCourses studentCourses = new StudentCourses();
                studentCourses.setId(Integer.parseInt(cursor.getString(0)));
                studentCourses.setPib(cursor.getString(1));
                studentCourses.setName(cursor.getString(2));
                studentCourses.setGrade(cursor.getString(3));
                studentCourses.setAdress(cursor.getString(4));
                studentsAbove60.add(studentCourses);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentsAbove60;
    }

    public double getAverageGrade() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT AVG(" + MyConstants.COLUMN_NAME_GRADE + ") FROM " + MyConstants.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        double averageGrade = 0;

        if (cursor != null && cursor.moveToFirst()) {
            averageGrade = cursor.getDouble(0);
            cursor.close();
        }

        return averageGrade;
    }

}
