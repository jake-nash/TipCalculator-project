package com.example.tipcalculator_nash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double tipPercent;
    private Button clearButton, calculateButton;
    private Intent i;
    private EditText price, numPeople, customTip;
    private RadioGroup radioGroup;
    private RadioButton percent_10, percent_15, percent_20, percent_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        percent_10 = findViewById(R.id.radio_10_percent);
        percent_15 = findViewById(R.id.radio_15_percent);
        percent_20 = findViewById(R.id.radio_20_percent);
        percent_custom = findViewById(R.id.radio_custom_tip);
        price = findViewById(R.id.priceOfMeal);
        numPeople = findViewById(R.id.numberPeople);
        customTip = findViewById(R.id.custom_percent);

        /*
         * customTip is not editable until percent_custom radio button clicked
         */
        
        clearButton = findViewById(R.id.resetButton);
        calculateButton = findViewById(R.id.calculateButton);
        i = new Intent(this, DisplayActivity.class);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price.setText("");
                numPeople.setText("");
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.valueOf(price.getText().toString());
                }
                catch (Exception e) {
                    showErrorAlert("\nEnter a number for \"price\"", R.id.priceOfMeal);
                    return;
                }
                i.putExtra("price", Double.valueOf(price.getText().toString()));
                try {
                    int b = Integer.valueOf(numPeople.getText().toString());
                }
                catch (Exception e) {
                    showErrorAlert("\nEnter a whole number for \"number of people\"", R.id.numberPeople);
                    return;
                }
                i.putExtra("numPeople", Integer.valueOf(numPeople.getText().toString()));

                if (percent_10.isChecked())
                    tipPercent = 10;
                else if (percent_15.isChecked())
                    tipPercent = 15;
                else if (percent_20.isChecked())
                    tipPercent = 20;
                else if (percent_custom.isChecked()) {
                    try {
                        double c = Double.valueOf(customTip.getText().toString());
                    }
                    catch (Exception e) {
                        showErrorAlert("Enter a number for \"custom tip\"", R.id.custom_percent);
                        return;
                    }
                    tipPercent = Double.valueOf(customTip.getText().toString());
                }
                else {
                    showErrorAlert("\nChoose a tip percentage", R.id.radioGroup);
                    return;
                }

                i.putExtra("tipPercent", tipPercent);
                startActivity(i);
            }
        });
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
//
//        @Override
//        public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//            switch (v.getId()) {
//                case R.id.txtAmount:
//                case R.id.txtPeople:
//                case R.id.txtTipOther:
//            }
//            return false;
//        }
//
//    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String inputCost = price.getText().toString();
        String inputNumPpl = numPeople.getText().toString();
        String inputCustomTip = customTip.getText().toString();
        outState.putString("cost", inputCost);
        outState.putString("numPpl", inputNumPpl);
        outState.putString("customTip", inputCustomTip);
        outState.putInt("checkedRadioButton", radioGroup.getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String inputCost = savedInstanceState.getString("cost");
        price.setText(inputCost);
        String inputNumPpl = savedInstanceState.getString("numPpl");
        numPeople.setText(inputNumPpl);
        String inputCustomTip = savedInstanceState.getString("customTip");
        customTip.setText(inputCustomTip);
        int radioID = savedInstanceState.getInt("checkedRadioButton");
        radioGroup.check(radioID);
    }
}