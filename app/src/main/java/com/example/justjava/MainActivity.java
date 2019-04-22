package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    boolean status, addChocolate;
    String newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //Method for quantity increment button
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 100){
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);

    }
    //Method for quantity decrement button
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);


    }

    // This method is called when we press the order button
    public void submitOrder(View view) {
        CheckBox checkBox = findViewById(R.id.checkbox_verify);
        status = checkBox.isChecked();
        CheckBox chocolate = findViewById(R.id.checkbox_chocolate);
        addChocolate = chocolate.isChecked();
        EditText customerName = findViewById(R.id.customer_name);
        newName = customerName.getText().toString();
        calculatePrice();
        createSummaryOrder();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, createSummaryOrder());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + newName);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }
        //method that calculates prices with each with each checkboxes
    private int calculatePrice() {
        int basePrice = 5;
        if (status){
            basePrice = basePrice +1;
        }
        if (addChocolate){
            basePrice = basePrice +2;
        }
        return quantity * basePrice;
    }
        //method to print the update the order list
    private String createSummaryOrder() {
        String priceMessage = "Name: " + newName + "\nAdd whipped cream? " + status + "\nAdd chocolate? " + addChocolate + "\nQuantity: " + quantity + "\nTotal is $" + calculatePrice();
        priceMessage += " \nThank you";
        return priceMessage;
    }
    // Method for updating quantity
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}
