package com.nta.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMath;
    private TextView tvResult;

    private int[] idButton = {
            R.id.btn0,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot,
            R.id.btnPlus, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
            R.id.btnOpen, R.id.btnClose,
            R.id.btnC, R.id.btnResult
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
    }
    private void connectView() {
        tvMath = (TextView) findViewById(R.id.tvMath);
        tvResult = (TextView) findViewById(R.id.tvResult);

        for (int i = 0; i < idButton.length; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
        init();
    }

    private void init() {
        tvMath.setText("");
        tvResult.setText("");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        for (int i = 0; i < idButton.length - 2; i++) {
            if (id == idButton[i]) {
                String text = ((Button) findViewById(id)).getText().toString();
                if (tvMath.getText().toString().trim().equals("|")) {
                    tvMath.setText("");
                }
                tvMath.append(text);
                return;
            }
        }

        // clear screen
        if (id == R.id.btnC) {
            init();
            return;
        }

        // calculation
        if (id == R.id.btnResult) {
            cal();
        }
    }

    private void cal() {
        String math = tvMath.getText().toString().trim();
        if (math.length() > 0) {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();
            // check error
            if (error != null) {
                tvResult.setText(error);
            } else { // show result
                tvResult.setText(result);
            }
        }
    }
}