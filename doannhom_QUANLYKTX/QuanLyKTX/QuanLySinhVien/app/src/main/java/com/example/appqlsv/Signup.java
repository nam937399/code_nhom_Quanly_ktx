package com.example.appqlsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlsv.database.BD;


public class Signup extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    BD DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);
        DB = new BD(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(Signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}

