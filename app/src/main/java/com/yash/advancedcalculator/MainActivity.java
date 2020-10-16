package com.yash.advancedcalculator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.yash.advancedcalculator.data.CalculatorContract;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class MainActivity extends AppCompatActivity  {
    boolean deg = false, rad = true;

    private CalAsyncTask cat;
    private Stack<String> previous;
    Vibrator v;
    ActionBar actionBar;
    String process = "";
    String process2 = "";
    ConstraintLayout constraintLayout;
    EditText in, out;
    ImageView delete,history;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b00,bplus,bmin,bmul,bdevide,bpercantage,bsin,bcos,btan,blog,bequal,broot,bpow,bfect,brestart,bdegree,bradian,bdot,breStart,breEnd;

    /*b00 ,b0 to b9 are Buttons from 1 to 9
    * bplus = +, bmin = -, bmul = x, bdevide = /, bpercantage = %
    * bsin = sin(), bcos = cos(), btan = tan()
    * blog = log(), bequal = '=', bfect = !, bpow = ^
    * breStart = (, breEnd = )
    *
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        previous = new Stack<String>();
        v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        delete = findViewById(R.id.delete);
        history = findViewById(R.id.history);
        constraintLayout = findViewById(R.id.constraint_layout);
        breStart = findViewById(R.id.bstart);
        breEnd = findViewById(R.id.bend);
        b00 = findViewById(R.id.b00);
        bdot = findViewById(R.id.bdot);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        bplus = findViewById(R.id.bplus);
        bmin = findViewById(R.id.bmin);
        bmul = findViewById(R.id.bx);
        bdevide = findViewById(R.id.bdevide);
        bpercantage = findViewById(R.id.bpercentage);
        bsin = findViewById(R.id.bsin);
        bcos = findViewById(R.id.bcos);
        btan = findViewById(R.id.btan);
        blog = findViewById(R.id.blog);
        bequal = findViewById(R.id.bequal);
        broot = findViewById(R.id.broot);
        bpow = findViewById(R.id.bpow);
        bfect = findViewById(R.id.bfect);
        brestart = findViewById(R.id.brestart);
        bdegree = findViewById(R.id.bdeg);
        bradian = findViewById(R.id.bradian);
        in = findViewById(R.id.calculate);
        out = findViewById(R.id.ans);
        setColour();
        out.setTextSize(28);
        in.setTextSize(34);
        in.setMovementMethod(new ScrollingMovementMethod());

        previous.push("ABC");
        try {
            breStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( process.isEmpty() || (!previous.peek().equals("1") && !previous.peek().equals("2") && !previous.peek().equals("3") && !previous.peek().equals("4") && !previous.peek().equals("5") &&
                            !previous.peek().equals("6") && !previous.peek().equals("7") && !previous.peek().equals("8") && !previous.peek().equals("9") && !previous.peek().equals("0") && !previous.peek().equals("00"))){
                        previous.push("(");
                        process = in.getText().toString() + "(";
                        in.setText(process);
                    }
                }
            });
            breEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (previous.peek().equals(")") ||previous.peek().equals("!") || previous.peek().equals("1") || previous.peek().equals("2") || previous.peek().equals("3") || previous.peek().equals("4") || previous.peek().equals("5") ||
                            previous.peek().equals("6") || previous.peek().equals("7") || previous.peek().equals("8") || previous.peek().equals("9") || previous.peek().equals("0")) {
                        previous.push(")");
                        process = in.getText().toString() + ")";
                        in.setText(process); }
                }
            });
            b00.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!process.isEmpty()) {
                        if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                        previous.push("00");
                        process = in.getText().toString() + "00";
                        in.setText(process);}
                    }
                }
            });
            b0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")") && !previous.peek().equals("!")){
                    previous.push("0");
                    process = in.getText().toString() + 0;
                    in.setText(process);}
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")") && !previous.peek().equals("!")){
                    previous.push("1");
                    process = in.getText().toString() + 1;
                    in.setText(process);}
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")") && !previous.peek().equals("!")){
                    previous.push("2");
                    process = in.getText().toString() + 2;
                    in.setText(process);}
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                    previous.push("3");
                    process = in.getText().toString() + 3;
                    in.setText(process);}
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")") && !previous.peek().equals("!")){
                    previous.push("4");
                    process = in.getText().toString() + 4;
                    in.setText(process);}
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                    previous.push("5");
                    process = in.getText().toString() + 5;
                    in.setText(process);}
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                    previous.push("6");
                    process = in.getText().toString() + 6;
                    in.setText(process);}
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                    previous.push("7");
                    process = in.getText().toString() + 7;
                    in.setText(process);}
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                    previous.push("8");
                    process = in.getText().toString() + 8;
                    in.setText(process);}
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!previous.peek().equals(")")  && !previous.peek().equals("!")){
                        previous.push("9");
                        process = in.getText().toString() + 9;
                        in.setText(process);
                    }
                }
            });
            bplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") && !previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"+";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '+';
                        }
                        in.setText(process);
                        previous.push("+");
                    }
                }
            });
            bmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!previous.peek().equals(".") && !previous.peek().equals("log") && !previous.peek().equals("√")) {
                        if (previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"-";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '-';
                        }
                        in.setText(process);
                        previous.push("-");
                    }
                }
            });
            bmul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") && !previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"x";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + 'x';
                        }
                        in.setText(process);
                        previous.push("x");
                    }
                }
            });
            bdevide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") &&!previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"/";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '/';
                        }
                        in.setText(process);
                        previous.push("/");
                    }

                }
            });
            bpercantage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") &&!previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"%";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '%';
                        }
                        in.setText(process);
                        previous.push("%");
                    }

                }
            });
            bfect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") && !previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("!") ||previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            previous.pop();
                            process = process.substring(0, process.length() - 1)+"!";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '!';
                        }
                        in.setText(process);
                        previous.push("!");
                    }
                }
            });
            broot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!previous.peek().equals("^")&& !previous.peek().equals(")")&& !previous.peek().equals(".") && (process.isEmpty() || previous.peek().equals("+") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("%") || previous.peek().equals("("))){
                        previous.push("√");
                        process = in.getText().toString() + '√';
                        in.setText(process);
                    }
                }
            });
            bpow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".") && !previous.peek().equals("(") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log")) {
                        if (previous.peek().equals("!") || previous.peek().equals("+") || previous.peek().equals("%") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("^") || previous.peek().equals("√")) {
                            process = process.substring(0, process.length() - 1)+"^";
                            Log.i("YASHYASH", process);
                        } else{
                            process = in.getText().toString() + '^';
                        }
                        in.setText(process);
                        previous.push("^");
                    }

                }
            });
            brestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = "";
                    in.setText(process);
                    out.setText("");
                    previous.clear();
                    previous.push("ABC");
                    out.setTextSize(28);
                    in.setTextSize(34);
                }
            });
            bsin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((process.isEmpty() || previous.peek().equals("+") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("%") || previous.peek().equals("(") || previous.peek().equals("^"))&& !previous.peek().equals(")") && !previous.peek().equals(".") &&!previous.peek().equals("^") && !previous.peek().equals("√") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log"))
                    {
                        previous.push("sin");
                        process = in.getText().toString() + "sin";
                        in.setText(process);
                    }

                }
            });
            blog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((process.isEmpty() || previous.peek().equals("+") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("%") || previous.peek().equals("(") || previous.peek().equals("^"))&&!previous.peek().equals(")") && !previous.peek().equals(".") && !previous.peek().equals("^") && !previous.peek().equals("√") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log"))
                    {
                        previous.push("log");
                        process = in.getText().toString() + "log";
                        in.setText(process);
                    }

                }
            });
            bcos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (( process.isEmpty() ||previous.peek().equals("+") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("%") || previous.peek().equals("(") || previous.peek().equals("^"))&&!previous.peek().equals(")") && !previous.peek().equals(".") &&!previous.peek().equals("^") && !previous.peek().equals("√") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log"))
                    {
                        previous.push("cos");
                        process = in.getText().toString() + "cos";
                        in.setText(process);
                    }

                }
            });
            btan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((process.isEmpty() || previous.peek().equals("+") || previous.peek().equals("-") || previous.peek().equals("x") || previous.peek().equals("/") || previous.peek().equals("%") || previous.peek().equals("(") || previous.peek().equals("^"))&&!previous.peek().equals(")") && !previous.peek().equals(".") &&!previous.peek().equals("^") && !previous.peek().equals("√") && !previous.peek().equals("tan") && !previous.peek().equals("cos") && !previous.peek().equals("sin") && !previous.peek().equals("log"))
                    {
                        previous.push("tan");
                        process = in.getText().toString() + "tan";
                        in.setText(process);
                    }

                }
            });
            bdot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!process.isEmpty() && !previous.peek().equals(".")&& !previous.peek().equals("(") && !previous.peek().equals(")")&&!previous.peek().equals("sin")&& !previous.peek().equals("cos") && !previous.peek().equals("tan")&& !previous.peek().equals("log")) {
                        previous.push(".");
                        process = in.getText().toString() + '.';
                        in.setText(process);
                    }
                }
            });
        }catch (Exception e){}

    }

    @SuppressLint("ResourceAsColor")
    void setColour(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String colour = sharedPrefs.getString("theme_key","Black");
        Log.i("The colour is",colour);
        int numberpadAndOtherColour,other,in_out_and_constraint,actionBarColor;

        switch(colour){
            case "Blue":{
                in_out_and_constraint = ContextCompat.getColor(MainActivity.this,R.color.Blue_Th_number_pad);
                actionBarColor = ContextCompat.getColor(MainActivity.this,R.color.Blue_Th_other_pad);
                numberpadAndOtherColour = R.drawable.blue_other_pad;
                other = R.drawable.blue_number_pad;
                break;
            }case "Orange":{
                in_out_and_constraint = ContextCompat.getColor(MainActivity.this,R.color.Orange_Th_number_pad);
                actionBarColor = ContextCompat.getColor(MainActivity.this,R.color.Orange_Th_other_pad);
                numberpadAndOtherColour = R.drawable.orange_other_pad;
                other = R.drawable.orange_number_pad;
                break;
            }
            case "Purple":{
                in_out_and_constraint = ContextCompat.getColor(MainActivity.this,R.color.Purple_Th_number_pad);
                actionBarColor = ContextCompat.getColor(MainActivity.this,R.color.Purple_Th_other_pad);
                numberpadAndOtherColour = R.drawable.purple_other_pad;
                other = R.drawable.purple_number_pad;
                break;
            } default:{
                in_out_and_constraint = ContextCompat.getColor(MainActivity.this,R.color.Black_Th_number_pad);
                actionBarColor = ContextCompat.getColor(MainActivity.this,R.color.Black_Th_other_pad);
                numberpadAndOtherColour = R.drawable.black_other_pad;
                other = R.drawable.black_number_pad;
            }
        }
        String hexColour = String.format("#%06X", (0xFFFFFF & actionBarColor));
        Log.i("The colour is",hexColour);
        ColorDrawable cd = new ColorDrawable(Color.parseColor(hexColour));
        actionBar.setBackgroundDrawable(cd);

        constraintLayout.setBackgroundColor(in_out_and_constraint);
        in.setBackgroundColor(in_out_and_constraint);
        out.setBackgroundColor(in_out_and_constraint);


        b0.setBackgroundResource(numberpadAndOtherColour);
        b00.setBackgroundResource(numberpadAndOtherColour);
        b1.setBackgroundResource(numberpadAndOtherColour);
        b2.setBackgroundResource(numberpadAndOtherColour);
        b3.setBackgroundResource(numberpadAndOtherColour);
        b4.setBackgroundResource(numberpadAndOtherColour);
        b5.setBackgroundResource(numberpadAndOtherColour);
        b6.setBackgroundResource(numberpadAndOtherColour);
        b7.setBackgroundResource(numberpadAndOtherColour);
        b8.setBackgroundResource(numberpadAndOtherColour);
        b9.setBackgroundResource(numberpadAndOtherColour);
        bplus.setBackgroundResource(numberpadAndOtherColour);
        bmul.setBackgroundResource(numberpadAndOtherColour);
        bdevide.setBackgroundResource(numberpadAndOtherColour);
        bmin.setBackgroundResource(numberpadAndOtherColour);
        bpercantage.setBackgroundResource(numberpadAndOtherColour);
        bdot.setBackgroundResource(numberpadAndOtherColour);
        delete.setBackgroundResource(numberpadAndOtherColour);
        history.setBackgroundResource(numberpadAndOtherColour);

        btan.setBackgroundResource(other);
        bsin.setBackgroundResource(other);
        bcos.setBackgroundResource(other);
        blog.setBackgroundResource(other);
        bfect.setBackgroundResource(other);
        broot.setBackgroundResource(other);
        bpow.setBackgroundResource(other);
        breStart.setBackgroundResource(other);
        breEnd.setBackgroundResource(other);
        bdegree.setBackgroundResource(other);
        bradian.setBackgroundResource(other);
        bequal.setBackgroundResource(other);

        brestart.setBackgroundResource(R.drawable.btnc);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setColour();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.history_of_cal)
        {
            Intent i = new Intent(MainActivity.this,HistoryActivity.class);
            startActivity(i);
        }
        if (id == R.id.setting){
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }
        return true;
    }

    public void remove(View view)
    {
        out.setTextSize(28);
        in.setTextSize(34);
        v.vibrate(50);
        try
        {
            if (previous.size() != 1 && !previous.peek().equals("ABC"))
                previous.pop();
            if (process.charAt(process.length()-1) == 'n' || process.charAt(process.length()-1) == 'g' || process.charAt(process.length()-1) == 's')
                process = process.substring(0, process.length()-3);
            else
                process = process.substring(0, process.length() - 1 );
        }
        catch (Exception e)
        {

        }
        in.setText(process);
    }

    public void answer(View view)
    {
        out.setText("");
        out.setTextSize(34);
        in.setTextSize(28);
        v.vibrate(50);
        cat = new CalAsyncTask();
        if (!process.isEmpty())
        {
            int lastIndex = process.length()-1;
            if (process.charAt(lastIndex) == '+' || process.charAt(lastIndex) == '-' || process.charAt(lastIndex) == '/' || process.charAt(lastIndex) == 'x' || process.charAt(lastIndex) == '%' || process.charAt(lastIndex) == '^' || process.charAt(lastIndex) == '√'){
                out.setText("ERROR");
            }
            try {
                if (process.charAt(lastIndex) == ')' && process.charAt(lastIndex - 1) == '(') {
                    out.setText("ERROR");
                }
                if (process.charAt(lastIndex) == '(' && process.charAt(lastIndex - 1) == ')') {
                    out.setText("ERROR");
                }
            }catch (Exception e){}
            if(process.indexOf(')') < process.indexOf('(')){
                out.setText("ERROR");
            }
            if (process.contains("(")){
                if (!process.contains(")")){
                    out.setText("ERROR");
                }
            }
            if (process.contains(")")){
                if (!process.contains("(")){
                    out.setText("ERROR");
                }
            }
            for (int i = 0; i < process.length()-1; i++){
                char f = process.charAt(i);
                char l = process.charAt(i+1);

                if ((f == '(' && l == ')') || (f == ')' && l == '(')){
                    out.setText("ERROR");
                    break;
                }
            }
            int lcount=0,fcount=0;
            for (int i = 0; i < process.length(); i++){
                if (process.charAt(i) == '('){
                    fcount++;
                }else if (process.charAt(i) == ')'){
                    lcount++;
                }
            }
            if (lcount != fcount){
                out.setText("ERROR");
            }

            if(process.charAt(lastIndex) != 'n' && process.charAt(lastIndex) != 's' && process.charAt(lastIndex) != 'g'){

                if (!out.getText().toString().equals("ERROR")){
                    process2 = process;
                    cat.execute(process);
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)

    public void deg(View view)
    {
        v.vibrate(50);
        deg = true;
        rad = false;
        bdegree.setTextColor(0xFF03DAC5);
        bradian.setTextColor(Color.WHITE);
    }

    @SuppressLint("ResourceAsColor")
    public void red(View view)
    {
        v.vibrate(50);
        rad = true;
        deg = false;
        bradian.setTextColor(0xFF03DAC5);
        bdegree.setTextColor(Color.WHITE);
    }

    public void history(View v){
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    private static String currentDateAndTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("LLL dd, yyyy  HH:mm:ss");
        Date d = new Date();
        return df.format(d);
    }

    private class CalAsyncTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String ans;
            try {
                Calculator c = new Calculator(deg);
                ans = c.calculate(strings[0]);
            }catch (Exception e){ ans = "Calculation Timeout";}
            return ans;
        }

        @Override
        protected void onPostExecute(String s) {
            if (!process.isEmpty())
            {
                String ans = s;

                try {
                        if (!ans.contains(".") && ans.length() <= 12)
                        {out.setText("= "+ans+"");}
                        else if (ans.contains("."))
                        {
                            if (ans.indexOf(".") <= 12)
                                out.setText("= "+ans+"");
                            else {
                                double answer = Double.parseDouble(ans);
                                ans = answer+"";
                                out.setText("= "+ans+"");
                            }
                        }
                        else{
                            double answer = Double.parseDouble(ans);
                            ans = answer+"";
                            out.setText("= "+ans+"");
                        }

                        if (ans.contains("√") || ans.contains("%") || ans.contains("^") || ans.contains("(") || ans.contains(")") || ans.contains("+") || ans.contains("x") || ans.contains("/") || ans.contains("!") || ans.contains("sin") || ans.contains("cos") || ans.contains("tan") || ans.contains("log"))
                        {
                            ans = "ERROR!";
                            out.setText(ans);
                        }

                    }catch (Exception e){
                        ans = "Calculation Timeout";
                        out.setText(ans+"");
                    }


                if (!s.isEmpty()){
                    String ip = process;
                    String op = "= "+ans;
                    String dateAndTime = currentDateAndTime();
                    ContentValues values = new ContentValues();
                    values.put(CalculatorContract.CalculatorEntry.INPUT, ip);
                    values.put(CalculatorContract.CalculatorEntry.OUTPUT, op);
                    values.put(CalculatorContract.CalculatorEntry.CURRENT_TIME, dateAndTime);
                    getContentResolver().insert(CalculatorContract.CalculatorEntry.CONTENT_URI,values);
                }

            }
        }
    }

}
