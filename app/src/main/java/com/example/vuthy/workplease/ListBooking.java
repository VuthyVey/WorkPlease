package com.example.vuthy.workplease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListBooking extends AppCompatActivity {

    private Button chanceActivityButton;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);

        configureNextButton();
    }
    private void configureNextButton(){

        Button nextButton = (Button) findViewById(R.id.bt1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListBooking.this,BookingInfo.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            }
        });
    }
}
