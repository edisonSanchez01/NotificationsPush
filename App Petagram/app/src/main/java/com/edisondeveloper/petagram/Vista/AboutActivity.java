package com.edisondeveloper.petagram.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.edisondeveloper.petagram.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar actionBar = findViewById(R.id.action_bar);
        setSupportActionBar(actionBar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.button_back);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
}