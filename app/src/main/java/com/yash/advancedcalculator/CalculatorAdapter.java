package com.yash.advancedcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.yash.advancedcalculator.data.CalculatorContract;

public class CalculatorAdapter extends CursorAdapter {

    Context c;
    public CalculatorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        c = context;
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        int inputColumnId = cursor.getColumnIndex(CalculatorContract.CalculatorEntry.INPUT);
        int outputColumnId = cursor.getColumnIndex(CalculatorContract.CalculatorEntry.OUTPUT);
        int dateColumnId = cursor.getColumnIndex(CalculatorContract.CalculatorEntry.CURRENT_TIME);

        ConstraintLayout constraintLayout = view.findViewById(R.id.constraint_layout_history);
        TextView ip = view.findViewById(R.id.ip);
        TextView op = view.findViewById(R.id.op);
        TextView dateAndTime = view.findViewById(R.id.date_and_time);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(c);
        String colour = sharedPrefs.getString("theme_key","Black");
        Log.i("The colour is",colour);
        int numberpadAndOtherColour,other;

        switch(colour){
            case "Blue":{
                numberpadAndOtherColour = ContextCompat.getColor(c,R.color.Blue_Th_number_pad);
                other = ContextCompat.getColor(c,R.color.Blue_Th_other_pad);
                dateAndTime.setTextColor(Color.CYAN);
                break;
            }case "Orange":{
                numberpadAndOtherColour = ContextCompat.getColor(c,R.color.Orange_Th_number_pad);
                other = ContextCompat.getColor(c,R.color.Orange_Th_other_pad);
                dateAndTime.setTextColor(numberpadAndOtherColour);
                dateAndTime.setTextColor(Color.GREEN);
                break;
            }
            case "Purple":{
                numberpadAndOtherColour = ContextCompat.getColor(c,R.color.Purple_Th_number_pad);
                other = ContextCompat.getColor(c,R.color.Purple_Th_other_pad);
                dateAndTime.setTextColor(numberpadAndOtherColour);
                dateAndTime.setTextColor(Color.MAGENTA);
                break;
            } default:{
                numberpadAndOtherColour = ContextCompat.getColor(c,R.color.Black_Th_number_pad);
                other = ContextCompat.getColor(c,R.color.Black_Th_other_pad);
                dateAndTime.setTextColor(Color.parseColor("#0C6EF1"));
            }
        }
        (view.findViewById(R.id.list_view_history)).setBackgroundColor(other);
        constraintLayout.setBackgroundColor(numberpadAndOtherColour);
        ip.setText(cursor.getString(inputColumnId));
        op.setText(cursor.getString(outputColumnId));
        dateAndTime.setText(cursor.getString(dateColumnId));

    }
}
