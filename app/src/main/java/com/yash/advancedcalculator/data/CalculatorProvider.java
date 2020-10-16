package com.yash.advancedcalculator.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CalculatorProvider extends ContentProvider {

    CalculatorDbHelper mDbhelper;

    private static final int CAL = 100;
    private static final int CAL_ID = 101;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(CalculatorContract.CalculatorEntry.CONTENT_AUTHORITY, CalculatorContract.CalculatorEntry.PATH,CAL);
        sUriMatcher.addURI(CalculatorContract.CalculatorEntry.CONTENT_AUTHORITY, CalculatorContract.CalculatorEntry.PATH+"/#",CAL_ID);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbhelper.getWritableDatabase();
        int rowAffected  = db.delete(CalculatorContract.CalculatorEntry.TABLE_NAME,null,null);
        getContext().getContentResolver().notifyChange(uri, null);
        return  rowAffected;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbhelper.getWritableDatabase();

                long id = db.insert(CalculatorContract.CalculatorEntry.TABLE_NAME, null,values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
        }


    @Override
    public boolean onCreate() {
        mDbhelper = new CalculatorDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;
        SQLiteDatabase db = mDbhelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match)
        {
            case CAL:
                cursor = db.query(CalculatorContract.CalculatorEntry.TABLE_NAME,projection,selection,selectionArgs,null,null, CalculatorContract.CalculatorEntry._ID+" DESC");break;
            case CAL_ID:
                selection = CalculatorContract.CalculatorEntry._ID + "+?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = db.query(CalculatorContract.CalculatorEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
