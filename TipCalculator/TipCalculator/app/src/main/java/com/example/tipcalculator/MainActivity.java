package com.example.tipcalculator;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, TextView.OnEditorActionListener {

    //Define variables for input
    private EditText billAmount;
    private TextView percent;
    private Button addPercent;
    private Button minusPercent;
    private TextView tipTotal;
    private TextView total;

    private float percentChangeAmount = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = (EditText)
                findViewById(R.id.billAmount);
        percent = (TextView)
                findViewById(R.id.tipPercent);
        addPercent = (Button)
                findViewById(R.id.addPercent);
        minusPercent = (Button)
                findViewById(R.id.minusPercent);
        tipTotal = (TextView)
                findViewById(R.id.tipAmount);
        total = (TextView)
                findViewById(R.id.totalAmount);

        billAmount.setOnEditorActionListener(this);
        addPercent.setOnClickListener(this);
        minusPercent.setOnClickListener(this);

        calculateAndDisplay();
    }

    public void calculateAndDisplay(){

        String billAmountString =
                billAmount.getText().toString();
        float billAmountFloat;
        if (billAmountString.equals("")){
            billAmountFloat = 0;
        }
        else{
            billAmountFloat = Float.parseFloat(billAmountString);
        }

        float tipAmount = billAmountFloat * percentChangeAmount;
        float totalAmount = billAmountFloat + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTotal.setText(currency.format(tipAmount));
        total.setText(currency.format(totalAmount));
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percent.setText(percentFormat.format(percentChangeAmount));



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.addPercent:
                percentChangeAmount = percentChangeAmount + .01f;
                calculateAndDisplay();
                break;
            case R.id.minusPercent:
                percentChangeAmount = percentChangeAmount - .01f;
                calculateAndDisplay();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }
}
