package com.richardo.finalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Profile = findViewById(R.id.profile_picture);
        username = findViewById(R.id.username);

       username.setText(firebaseUser.getDisplayName());
       if(firebaseUser.getPhotoUrl()!=null)
       {
           Glide.with(this)
                   .load(firebaseUser.getPhotoUrl())
                   .into(this.Profile);
       }
    }
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}
