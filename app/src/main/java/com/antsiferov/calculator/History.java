package com.antsiferov.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class History extends AppCompatActivity implements View.OnClickListener {

    TextView showHistory;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        showHistory = (TextView) findViewById(R.id.showHistory);
        close = (Button) findViewById(R.id.close);

        Intent intent = getIntent();
        String history = intent.getStringExtra("History");
        showHistory.setText(history);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                //onDestroy();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
