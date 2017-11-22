package com.example.vuthy.workplease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button samnang, vuthy, visal, mengthong, jibou;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        samnang = (Button) findViewById(R.id.samnang);
        vuthy = (Button) findViewById(R.id.vuthy);
        visal = (Button) findViewById(R.id.visal);
        mengthong = (Button) findViewById(R.id.mengthong);
        jibou = (Button) findViewById(R.id.jibu);

        samnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        vuthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, CreateBooking.class);
                startActivity(intent);
            }
        });
        mengthong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, ListBooking.class);
                startActivity(intent);
            }
        });

        visal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
