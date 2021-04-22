package com.example.collegeproject.SqlClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlHelper extends SQLiteOpenHelper {

    private static final String NAME_DATABASE = "";

    Context context;
    public sqlHelper(Context context) {
        super(context, NAME_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createQuery = "";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
