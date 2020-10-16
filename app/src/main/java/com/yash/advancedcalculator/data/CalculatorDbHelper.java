package com.yash.advancedcalculator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yash.advancedcalculator.data.CalculatorContract.CalculatorEntry;

public class CalculatorDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "history.db";
    private static final int DATABASE_VERSION = 1;
    public CalculatorDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE "+CalculatorEntry.TABLE_NAME+"("+CalculatorEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CalculatorEntry.INPUT+" TEXT NOT NULL, "+CalculatorEntry.OUTPUT+" TEXT NOT NULL, "+CalculatorEntry.CURRENT_TIME+" TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
