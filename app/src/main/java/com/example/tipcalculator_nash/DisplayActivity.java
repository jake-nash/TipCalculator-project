package com.example.tipcalculator_nash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private Button backButton;
    private Intent goBack;
    private double tipAmount, totalAmount, totalPerPerson;
    private TextView tipAmountDisplay, totalAmountDisplay, totalPerPersonDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tipAmountDisplay = findViewById(R.id.tipDisplay);
        totalAmountDisplay = findViewById(R.id.totalDisplay);
        totalPerPersonDisplay = findViewById(R.id.totalPerPersonDisplay);
        Intent i = getIntent();
        goBack = new Intent(this,MainActivity.class);
        double mealCost = i.getDoubleExtra("price", 0);
        int numberPpl = i.getIntExtra("numPeople", 0);
        double tipPercent = i.getDoubleExtra("tipPercent", 0);
        tipAmount = mealCost*(tipPercent/100.);
        String tipAmountText = String.format("$%.2f", tipAmount);
        totalAmount = mealCost+tipAmount;
        String totalAmountText = String.format("$%.2f", totalAmount);
        totalPerPerson = totalAmount/numberPpl;
        String totalPerPersonText = String.format("$%.2f", totalPerPerson);

        tipAmountDisplay.setText(tipAmountText);
        totalAmountDisplay.setText(totalAmountText);
        totalPerPersonDisplay.setText(totalPerPersonText);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goBack.putExtra("","");
                startActivity(goBack);
            }
        });
    }
}