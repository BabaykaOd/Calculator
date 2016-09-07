package com.antsiferov.calculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button num0, num1, num2, num3, num4, num5,num6, num7, num8, num9, dot,
            plus, minus, times, divided, result, bracketStart;
    ImageButton backspaceFull, backspaceSmall;
    TextView ShowNum;

    String expression = "",
            history = "";

    float textSize = 34;
    boolean dotChecked = false,
            numOrActions,
            resultChecked = false,
            bracketChecked = true;

    Stack<String> bracket = new Stack<>();

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        num0 = (Button) findViewById(R.id.num0);
        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        bracketStart = (Button) findViewById(R.id.bracket);
        dot = (Button) findViewById(R.id.dot);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        times = (Button) findViewById(R.id.times);
        result = (Button) findViewById(R.id.result);
        divided = (Button) findViewById(R.id.divided);
        backspaceFull = (ImageButton) findViewById(R.id.backspaceFull);
        backspaceSmall = (ImageButton) findViewById(R.id.backspaceSmall);
        ShowNum = (TextView) findViewById(R.id.ShowNum);

        ShowNum.setTextSize(textSize);

        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        times.setOnClickListener(this);
        divided.setOnClickListener(this);
        dot.setOnClickListener(this);
        result.setOnClickListener(this);
        backspaceFull.setOnClickListener(this);
        backspaceSmall.setOnClickListener(this);
        bracketStart.setOnClickListener(this);


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private  void result() {
        if (!expression.isEmpty()) {
                if (numOrActions) {
                    //if (!bracket.isEmpty()) {
                        SortFacility calculation = new SortFacility();
                        double result;

                        result = calculation.eval(expression);
                        if (result % 1 == 0) {
                            expression += " = " + (int) result;
                        } else {
                            expression += " = " + result;
                        }
                        changeShowNum(expression);
                        ShowNum.setText(expression);
                        history += expression + "\n";
                        resultChecked = true;
                    //} else {
                      //  Toast.makeText(MainActivity.this, "Вы закрыли не все скобки.", Toast.LENGTH_LONG).show();
                    //}
                } else {
                    Toast.makeText(MainActivity.this, "Введите число или удалите действие", Toast.LENGTH_LONG).show();
                }
        } else {
            Toast.makeText(MainActivity.this, "Вы ничего не введи чтобы посчитать :)", Toast.LENGTH_LONG).show();
        }
    }

    private void getNum(String num) {
        if (resultChecked) {
            expression = "";
            ShowNum.setText("");
            changeShowNum(expression);
        }
        if (!num.equals("0")) {
            expression += num;
            changeShowNum(expression);
            ShowNum.setText(expression);
            dotChecked = false;
            resultChecked = false;
            numOrActions = true;
        } else {
            if (!expression.isEmpty()) {
                expression += "0";
                changeShowNum(expression);
                ShowNum.setText(expression);
            } else {
                expression += "0.";
                changeShowNum(expression);
                ShowNum.setText(expression);
            }
            numOrActions = true;
        }
    }

    private void getDot() {
        if (!dotChecked) {
            if (!expression.isEmpty()) {
                expression += ".";
                ShowNum.setText(expression);
                changeShowNum(expression);
                dotChecked = true;
            } else {
                expression += "0.";
                changeShowNum(expression);
                ShowNum.setText(expression);
                dotChecked = true;
            }
        } else {
            Toast.makeText(MainActivity.this, "Нельзя ввести две точки.", Toast.LENGTH_LONG).show();
        }
    }

    private void getActions(String action) {
        if (!expression.isEmpty()) {
            if (numOrActions) {
                if (!dotChecked) {
                    expression += action;
                    ShowNum.setText(expression);
                    numOrActions = false;
                } else {
                    Toast.makeText(MainActivity.this, "Незаконченное выражение, допишите число.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Действие уже выбрано.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Введите число", Toast.LENGTH_LONG).show();
        }
    }

    private void getBracket() {
        Toast.makeText(MainActivity.this, "В разработке", Toast.LENGTH_LONG).show();
    }

    private void changeShowNum(String str) {
        if (str.length() > 18) {
            ShowNum.setTextSize(30);
            ShowNum.setHeight(60);
            if (str.length() > 20) {
                ShowNum.setHeight(100);
            } else {
                ShowNum.setHeight(36);
            }
        } else {
            ShowNum.setTextSize(34);
            ShowNum.setHeight(36);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.num0:
                getNum("0");
                break;

            case R.id.num1:
                getNum("1");
                break;

            case R.id.num2:
                getNum("2");
                break;

            case R.id.num3:
                getNum("3");
                break;

            case R.id.num4:
                getNum("4");
                break;

            case R.id.num5:
                getNum("5");
                break;

            case R.id.num6:
                getNum("6");
                break;

            case R.id.num7:
                getNum("7");
                break;

            case R.id.num8:
                getNum("8");
                break;

            case R.id.num9:
                getNum("9");
                break;

            case R.id.dot:
                getDot();
                break;

            case R.id.plus:
                getActions("+");
                break;

            case R.id.minus:
                getActions("-");
                break;

            case R.id.times:
                getActions("*");
                break;

            case R.id.divided:
                getActions("/");
                break;

            case R.id.bracket:
                getBracket();
                break;

            case R.id.backspaceFull:
                expression = "";
                bracket.clear();
                ShowNum.setText("");
                break;

            case R.id.backspaceSmall:
                Toast.makeText(MainActivity.this, "Пока не работает", Toast.LENGTH_LONG).show();
                break;

            case R.id.result:
                result();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "Настройки", Toast.LENGTH_LONG).show();
                break;
            case R.id.history:
                Intent intent = new Intent(this, History.class);
                intent.putExtra("History", history);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.antsiferov.calculator/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.antsiferov.calculator/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
