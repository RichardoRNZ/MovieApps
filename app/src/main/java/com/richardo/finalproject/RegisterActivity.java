package com.richardo.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.richardo.finalproject.Model.User;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_login;
    private FloatingActionButton register_btn;
    private EditText register_email, register_password, register_username;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_login = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_username = findViewById(R.id.register_username);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToLoginPage);
                finish();
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkIsEmptyField();
               checkIsEmptyField();
            }
        });

    }
    private void checkIsEmptyField()
    {
        if (register_email.getText().length()>0 && register_username.getText().length()>0 && register_password.getText().length()>0)
        {
            Register();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please input email and password", Toast.LENGTH_SHORT).show();
        }
    }
    private void Register()
    {
        progressDialog.show();
        User user = new User(register_username.getText().toString(), register_email.getText().toString(), register_password.getText().toString());
        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult()!=null)
                {
                    Log.d("check", "createUserWithEmail:success");
                    Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = task.getResult().getUser();
                   if (firebaseUser!=null)
                   {
                       UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(user.getUsername()).build();
                       firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                            reload();
                           }
                       });
                   }
                   else {
                       Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
                   }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent goToLoginPage = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(goToLoginPage);
        finish();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}