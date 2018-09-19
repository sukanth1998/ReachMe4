package com.example.sukanthchandrasekar.reachme4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextView display;
    SharedPreferences sharedPreferences;
    String userKey = "Not registered...";
    EditText registername, registerdob, registeremailid;
    Button updatedetails;
    RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences = getSharedPreferences("ReachMe", Context.MODE_PRIVATE);
        display=(TextView)findViewById(R.id.display);
        registerdob = (EditText) findViewById(R.id.dob);
        registeremailid = (EditText)findViewById(R.id.registeremailid);
        registername = (EditText)findViewById(R.id.registername);
        updatedetails = (Button) findViewById(R.id.updatedetailsbutton);
        gender = (RadioGroup) findViewById(R.id.genderradiogroup);
        if (sharedPreferences.contains("userKey")){
            userKey = sharedPreferences.getString("userKey","Default");
        }else {
        }
        display.setText("Registered Number - "+userKey);
        updatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = registername.getText().toString();
                String d = registerdob.getText().toString();
                String e = registeremailid.getText().toString();
                RadioButton radioButton = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
                String g = radioButton.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child(getString(R.string.firebase_path)).child(getString(R.string.userDetails)).child(userKey).child("name").setValue(n);
                databaseReference.child(getString(R.string.firebase_path)).child(getString(R.string.userDetails)).child(userKey).child("dob").setValue(d);
                databaseReference.child(getString(R.string.firebase_path)).child(getString(R.string.userDetails)).child(userKey).child("gender").setValue(g);
                databaseReference.child(getString(R.string.firebase_path)).child(getString(R.string.userDetails)).child(userKey).child("emailid").setValue(e);
                Toast.makeText(getApplicationContext(),"Added...",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this,SelectServices.class));
                finish();
            }
        });

    }
}
