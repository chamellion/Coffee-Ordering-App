package com.example.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view){
        int numberOfCoffees = 2 ;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    public void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }public void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
