package com.richardo.finalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.richardo.finalproject.R;

public class DashboardActivity extends AppCompatActivity {

    private ImageView Profile;
    private TextView username;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Button button_logout;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Profile = findViewById(R.id.profile_picture);
        username = findViewById(R.id.username);
        button_logout = findViewById(R.id.btn_logout);
       username.setText(firebaseUser.getDisplayName());
       if(firebaseUser.getPhotoUrl()!=null)
       {
           Glide.with(this)
                   .load(firebaseUser.getPhotoUrl())
                   .into(this.Profile);
       }
       else{
           Glide.with(this)
                   .load(R.drawable.ic_baseline_person_2_24)
                   .into(Profile);
       }
       button_logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               firebaseAuth.signOut();
               startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
           }
       });
    }

    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
