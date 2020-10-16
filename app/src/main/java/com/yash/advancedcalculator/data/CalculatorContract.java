package com.yash.advancedcalculator.data;

import android.net.Uri;
import android.provider.BaseColumns;


public class CalculatorContract  {


    private CalculatorContract() {}

    public static class CalculatorEntry implements BaseColumns{

        public static final String TABLE_NAME = "history";
        public static final String _ID = BaseColumns._ID;
        public static final String INPUT = "input";
        public static final String OUTPUT = "output";
        public static final String CURRENT_TIME = "time";

        public static final String CONTENT_AUTHORITY = "com.yash.android.calculator.history";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
        public static final String PATH = "history";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH);
    }
}
