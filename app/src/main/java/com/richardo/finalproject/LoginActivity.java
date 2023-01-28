package com.richardo.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    Button btn_register;
    FloatingActionButton btn_login;
    EditText email,password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_register = findViewById(R.id.register_btn);
        btn_login = findViewById(R.id.login);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToRegisterPage = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(goToRegisterPage);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void checkIsEmptyField()
    {
        if (email.getText().length()>0 && password.getText().length()>0)
        {
            Login(email.getText().toString(), password.getText().toString());
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please input email and password", Toast.LENGTH_SHORT).show();
        }
    }
    private void Login(String Email, String Password)
    {

    }
}