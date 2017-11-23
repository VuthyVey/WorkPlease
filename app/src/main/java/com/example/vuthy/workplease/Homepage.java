package com.example.vuthy.workplease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Homepage extends AppCompatActivity {

    private CardView booking;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        booking = (CardView) findViewById(R.id.booking_home);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Homepage.this, CreateBooking.class);
                startActivity(intent);
            }
        });
    }
}
