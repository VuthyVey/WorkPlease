package com.example.vuthy.workplease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button login_bt;
    private Button reg_bt;
    private EditText phone_input;
    private EditText pass_input;
    private String userphone = "081225255";
    private String userPass = "1234";
    private String truedisplay = "Login Success";
    private String faildisplay = "Phone Number or Password incorrect";
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: Starting.");

        login_bt = findViewById(R.id.loginButton);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked login_bt.");
                String phone = ((EditText) findViewById(R.id.phoneInput)).getText().toString();
                boolean phone_check = userphone.equals(phone);
                String pass = ((EditText) findViewById(R.id.passInput)).getText().toString();
                boolean pass_check = userPass.equals(pass);
                if (phone_check && pass_check) {
                    intent = new Intent(Login.this, Homepage.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), truedisplay, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), faildisplay, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}