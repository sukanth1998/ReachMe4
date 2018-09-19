package com.example.sukanthchandrasekar.reachme4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SamplePhone extends AppCompatActivity {
    EditText phone;
    Button proceedphone;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_phone);
        phone = findViewById(R.id.phone);
        proceedphone = findViewById(R.id.proceedphone);
        sharedPreferences = getSharedPreferences("ReachMe", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("userKey")){
            startActivity(new Intent(SamplePhone.this,MainActivity.class));
            finish();
        }
        proceedphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("userKey",phone.getText().toString());
                editor.putBoolean("login",true);
                startActivity(new Intent(SamplePhone.this,MainActivity.class));
                finish();
            }
        });
    }
}
