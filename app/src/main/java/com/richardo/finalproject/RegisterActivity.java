package com.richardo.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToLoginPage);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent goToLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(goToLoginPage);
        finish();
    }
}