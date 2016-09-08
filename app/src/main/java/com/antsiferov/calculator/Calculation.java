package com.antsiferov.calculator;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Бабайка on 08.09.2016.
 */
public class Calculation {

    private String expression = "";
    private boolean resultChecked = false;
    private boolean dotChecked = false;
    private boolean numOrActions = true;

    private static final String TAG = "myLogs";

    // Главный ввод данных
    public void get(String str) {

        Pattern num = Pattern.compile("[0-9]");
        Pattern action = Pattern.compile("[+\\-/*]");
        Matcher n = num.matcher(str);
        Matcher a = action.matcher(str);


        if(n.matches()) {
            get_num(str);
            Log.d(TAG, "Функция get/get_num");
        } else if(a.matches()) {
            get_actions(str);
            Log.d(TAG, "Функция get/get_action");
        } else if(str.equals(".")) {
            get_dot();
        }

    }

    private String get_num(String str) {

        if (resultChecked) {
            expression = "";
        }
        if (!str.equals("0")) {
            expression += str;
            dotChecked = false;
            resultChecked = false;
            numOrActions = true;
        } else {
            if (!expression.isEmpty()) {
                expression += "0";
            } else {
                expression += "0.";
            }
            numOrActions = true;
        }
        return expression;
    }

    private String get_actions(String action) {
        if (!expression.isEmpty()) {
            if (numOrActions) {
                if (!dotChecked) {
                    expression += action;
                    Log.d(TAG, "Добавить знак" + action);
                    numOrActions = false;
                } else {
                    return "Незаконченное выражение, допишите число.";
                }
            } else {
                return "Действие уже выбрано.";
            }
        } else {
            return "Введите число";
        }
        Log.d(TAG, "Вернуть выражение" + expression);
        return expression;
    }

    public String result() {
        if (!expression.isEmpty()) {
            if (numOrActions) {
                //if (!bracket.isEmpty()) {
                SortFacility calculation = new SortFacility();
                double result;

                result = calculation.eval(expression);
                Log.d(TAG, "Результат = " + result);
                if (result % 1 == 0) {
                    expression += "=" + (int) result;
                } else {
                    expression += "=" + result;
                }
                resultChecked = true;
                //} else {
                //  Toast.makeText(MainActivity.this, "Вы закрыли не все скобки.", Toast.LENGTH_LONG).show();
                //}
            } else {
                Log.d(TAG, "Введите число или удалите действие");
                return "Введите число или удалите действие";
            }
        } else {
            Log.d(TAG, "Вы ничего не введи чтобы посчитать :)");
            return "Вы ничего не введи чтобы посчитать :)";
        }
        Log.d(TAG, "Вернуть выражение: " + expression);
        return expression;
    }

    private String get_dot() {
        if (!dotChecked) {
            if (!expression.isEmpty()) {
                expression += ".";
                dotChecked = true;
                return expression;
            } else {
                expression += "0.";
                dotChecked = true;
                return expression;
            }
        } else {
            return "Нельзя ввести две точки.";
        }
    }

    public String show_expression() {
        return expression;
    }

    public void clear_expression() {
        expression = "";
        resultChecked = false;
        dotChecked = false;
        numOrActions = true;
    }

}
