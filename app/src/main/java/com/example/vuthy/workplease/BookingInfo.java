package com.example.vuthy.workplease;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class BookingInfo extends AppCompatActivity {

    public static Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);

        configureBackButton();
    }
    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
            }
        });
    }
    public void dialogevent(View view){
    confirm = (Button)findViewById(R.id.BookingConfirm);
    confirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder atldial = new AlertDialog.Builder(BookingInfo.this);
            AlertDialog.Builder builder = atldial.setMessage("Are you sure to confirm the booking?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(BookingInfo.this, Homepage.class));
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
                            Toast.makeText(getApplicationContext(), "You had sucessfully confirmed the booking", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "You had canceled the booking", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
            AlertDialog alert=atldial.create();
            alert.setTitle("Confirm booking information");
            alert.show();
        }
        });
    }
}
