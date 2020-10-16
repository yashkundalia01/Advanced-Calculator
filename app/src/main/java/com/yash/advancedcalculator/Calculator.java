package com.yash.advancedcalculator;

import java.math.BigDecimal;

public class Calculator {

    boolean deg;
    public Calculator(boolean angle){
        deg = angle;
    }
    public static int pre(char s) {
        if (s == '+' || s == '-') {
            return 2;
        } else if (s == 'x') {
            return 3;
        }
        else if (s == '√') {
            return 7;
        }
        else if (s == '!') {
            return 6;
        }
        else if (s == '/' || s == '%') {
            return 4;
        } else if (s == '^' || s == 's' || s == 'c' || s == 't' || s == 'l') {
            return 5;
        }
        return 0;
    }

    public String calculate(String ss) {
        if (!ss.contains("+") && !ss.contains("-") &&!ss.contains("x") &&!ss.contains("/") &&!ss.contains("sin") &&!ss.contains("cos") &&!ss.contains("tan") &&!ss.contains("log")&&!ss.contains("!") &&!ss.contains("^")&&!ss.contains("%") &&!ss.contains("√") )
            return ss;
        String s = ss;
        boolean flag = true;
        int b = 0, a = 0;
        while (flag) {
            int index = 0, temppre = 0;
            double n1 = 0, n2 = 0, res = 0;
            String temp = "", before = "", after = "";
            int check = 0;
            if (s.contains("(") && s.contains(")"))
            {
                int startingIndex = s.indexOf("("), endIndex = 0;
                for (int i = startingIndex+1; i < s.length(); i++)
                {
                    if (s.charAt(i) == ')')
                    {
                        if(check == 0)
                        {endIndex = i;break;}
                        else
                            check--;
                    }
                    else if (s.charAt(i) == '(')
                    {
                        check++;
                    }
                }
                String cal = s.substring(startingIndex + 1, endIndex);
                cal = calculate(cal);
                s = s.substring(0, startingIndex) + cal + s.substring(endIndex + 1, s.length());
                if (s.contains("(") && s.contains(")"))
                {
                    s = calculate(s);
                }
            }
            if (s.contains("x") || s.contains("/") || s.contains("+") || s.contains("-") || s.contains("%") || s.contains("sin") || s.contains("cos") || s.contains("log") || s.contains("tan") || s.contains("^") || s.contains("√") || s.contains("!"))
            {
                for (int i = 0; i < s.length(); i++)
                {
                    if (s.contains("sin")) {
                        index = s.indexOf('i') - 1;
                        break;
                    } else if (s.contains("cos")) {
                        index = s.indexOf('c');
                        break;
                    } else if (s.contains("tan")) {
                        index = s.indexOf('t');
                        break;
                    } else if (s.contains("log")) {
                        index = s.indexOf('l');
                        break;
                    } else {
                        if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '.'
                                || (s.charAt(i) == '-' && i == 0)) {
                            continue;
                        } else if (pre(s.charAt(i)) > temppre) {
                            temppre = pre(s.charAt(i));
                            index = i;
                        }
                    }
                }
                try {
                    for (int j = index - 1; j >= 0 && ((s.charAt(j) >= '0' && s.charAt(j) <= '9') || s.charAt(j) == 'π') || s.charAt(j) == '.'
                            || (s.charAt(j) == '-' && j == 0); j--) {
                        temp = temp + s.charAt(j);
                        b = j;
                    }
                } catch (Exception e) {

                }
                try {
                    for (int j = temp.length() - 1; j >= 0; j--) {
                        before = before + temp.charAt(j);
                    }
                    if (!s.contains("sin") && !s.contains("cos") && !s.contains("tan") && !s.contains("log"))
                        for (int k = index + 1; (k < s.length()) && ((s.charAt(k) >= '0' && s.charAt(k) <= '9') || s.charAt(k) == 'π')
                                || s.charAt(k) == '.' || (s.charAt(k) == '-' && k == index + 1); k++) {
                            after = after + s.charAt(k);
                            a = k;
                        }
                    else {
                        for (int k = index + 3; (k < s.length()) && ((s.charAt(k) >= '0' && s.charAt(k) <= '9') || s.charAt(k) == 'π')
                                || s.charAt(k) == '.' || (s.charAt(k) == '-' && k == index + 3); k++) {
                            after = after + s.charAt(k);
                            a = k;
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    n1 = Double.parseDouble(before);

                } catch (Exception e) {
                    if (before.equals("π"))
                    {
                        n1 = Math.PI;
                    }
                    else if(before.contains("π"))
                    {
                        String t="";
                        for (int i = 0; i < before.length() && before.charAt(i) != 'π'; i++)
                        {
                            t = t + before.charAt(i);
                        }
                        double num1 = Double.parseDouble(t);
                        n1 = num1 * Math.PI;
                    }
                }
                try {
                    n2 = Double.parseDouble(after);
                } catch (Exception e) {

                    if (after.equals("π"))
                    {
                        n2 = Math.PI;
                    }
                    else if(after.contains("π"))
                    {
                        String t="";
                        for (int i = 0; i < after.length() && after.charAt(i) != 'π'; i++)
                        {
                            t = t + after.charAt(i);
                        }
                        double num2 = Double.parseDouble(t);
                        n2 = num2 * Math.PI;
                    }
                }
                String r="";
                switch (s.charAt(index)) {

                    case '+':
                        res = n1 + n2;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '-':
                        res = n1 - n2;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case 'x':
                        res = n1 * n2;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '%' :
                        res = n1*n2*0.01;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '/':
                        if (n2 == 0)
                            break;
                        else
                            res = n1 / n2;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '^':
                        res = Math.pow(n1, n2);
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '!':
                        double f = 1;
                        for (int m = 1; m <= n1; m++) {
                            f = f * m;
                        }
                        a = index;
                        res = f;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case '√':
                        b = index;
                        res = Math.sqrt(n2);
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case 's':
                        if (deg)
                            n2 = n2*(Math.PI)/180;
                        res = Math.sin(n2);
                        b = index;
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        break;
                    case 'c':
                        if (deg)
                            n2 = n2*(Math.PI)/180;
                        res = Math.cos(n2);
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        b = index;
                        break;
                    case 't':
                        if (deg)
                            n2 = n2*(Math.PI)/180;
                        res = Math.tan(n2);
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        b = index;
                        break;
                    case 'l':
                        res = Math.log10(n2);
                        r = BigDecimal.valueOf(res).toPlainString() + "";
                        b = index;
                        break;
                    case 'π':
                        res = Math.PI * n1;

                }
                if (b != 0 || a != 0)
                    s = s.substring(0, b) + r + s.substring(a + 1, s.length());

                else
                    break;
                for (int k = 0; k < s.length(); k++) {
                    if (s.charAt(k) == '-' && k == 0) {
                        flag = false;
                    } else if ( s.charAt(k) == '%' || s.charAt(k) == 'x' || s.charAt(k) == '+' || s.charAt(k) == '-' || s.charAt(k) == '/' || s.charAt(k) == '^' || s.charAt(k) == '!' || s.contains("sin") || s.contains("tan") || s.contains("log")|| s.contains("cos") ) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            }
            else
                break;
        }
        return s;
    }

}
