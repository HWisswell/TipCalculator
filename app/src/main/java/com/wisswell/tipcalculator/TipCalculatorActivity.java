package com.wisswell.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;

public class TipCalculatorActivity extends AppCompatActivity implements OnEditorActionListener,
        OnClickListener {

    //define variables for the widgets
    private TextView percentTextView;
    private EditText billAmountEditText;
    private TextView tipTextView;
    private TextView totalTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private Button resetButton;

    //define instance variable that should be saved
    private float tipPercent = .15f;
    private String billAmountEditString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);


        //get references to the widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentUpButton = (Button) findViewById(R.id.percentUpButton);
        percentDownButton = (Button) findViewById(R.id.percentDownButton);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        resetButton = (Button) findViewById(R.id.resetButton);

        //set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentDownButton.setOnClickListener(this);
        percentUpButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            switch(view.getId()) {
                case R.id.percentDownButton:
                    tipPercent = tipPercent - .01f;
                    calculateAndDisplay();
                    break;
                case R.id.percentUpButton:
                    tipPercent = tipPercent + .01f;
                    calculateAndDisplay();
                    break;
                case R.id.resetButton:
                    billAmountEditText.setText("");
                    tipPercent = .15f;
                    calculateAndDisplay();
                    break;
            }



            }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

        if(i == EditorInfo.IME_ACTION_DONE ||
                i == EditorInfo.IME_ACTION_UNSPECIFIED){
            calculateAndDisplay();
        }
        return false;
    }

    public void calculateAndDisplay() {

        billAmountEditString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountEditString.equals("")){
            billAmount = 0;
        }
        else{
            billAmount = Float.parseFloat(billAmountEditString);
        }

        //calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));


    }
}
