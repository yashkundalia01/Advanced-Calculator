package com.yash.advancedcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.yash.advancedcalculator.data.CalculatorContract;

public class HistoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    CalculatorAdapter ca;
    ActionBar actionBar;
    ConstraintLayout constraintLayout;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        actionBar = getSupportActionBar();
        ListView ListView = (ListView) findViewById(R.id.list_view);
        constraintLayout = findViewById(R.id.c_layout_history_activity);
        ca = new CalculatorAdapter(this, null);
        TextView tv =findViewById(R.id.empty_view);
        ListView.setEmptyView(tv);
        ListView.setAdapter(ca);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String colour = sharedPrefs.getString("theme_key","Black");
        Log.i("The colour is",colour);
        int numberpadAndOtherColour,other;

        switch(colour){
            case "Blue":{
                numberpadAndOtherColour = ContextCompat.getColor(HistoryActivity.this,R.color.Blue_Th_number_pad);
                other = ContextCompat.getColor(HistoryActivity.this,R.color.Blue_Th_other_pad);
                break;
            }case "Orange":{
                numberpadAndOtherColour = ContextCompat.getColor(HistoryActivity.this,R.color.Orange_Th_number_pad);
                other = ContextCompat.getColor(HistoryActivity.this,R.color.Orange_Th_other_pad);
                break;
            }
            case "Purple":{
                numberpadAndOtherColour = ContextCompat.getColor(HistoryActivity.this,R.color.Purple_Th_number_pad);
                other = ContextCompat.getColor(HistoryActivity.this,R.color.Purple_Th_other_pad);
                break;
            } default:{
                numberpadAndOtherColour = ContextCompat.getColor(HistoryActivity.this,R.color.Black_Th_number_pad);
                other = ContextCompat.getColor(HistoryActivity.this,R.color.Black_Th_other_pad);
            }
        }
        String hexColour = String.format("#%06X", (0xFFFFFF & numberpadAndOtherColour));
        Log.i("The colour", hexColour);
        ColorDrawable cd = new ColorDrawable(Color.parseColor(hexColour));
        actionBar.setBackgroundDrawable(cd);
        constraintLayout.setBackgroundColor(numberpadAndOtherColour);
        getLoaderManager().initLoader(1, null, this);
    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.delete_all == id)
        {
            getContentResolver().delete(CalculatorContract.CalculatorEntry.CONTENT_URI, null, null);
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {CalculatorContract.CalculatorEntry._ID, CalculatorContract.CalculatorEntry.INPUT, CalculatorContract.CalculatorEntry.OUTPUT, CalculatorContract.CalculatorEntry.CURRENT_TIME};
        return new CursorLoader(this,CalculatorContract.CalculatorEntry.CONTENT_URI, projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ca.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ca.swapCursor(null);
    }
}