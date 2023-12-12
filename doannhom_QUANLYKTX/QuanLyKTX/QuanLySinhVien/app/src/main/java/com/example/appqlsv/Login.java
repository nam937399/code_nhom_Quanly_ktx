package com.example.appqlsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appqlsv.database.BD;


//import com.example.myapplication.database.BD;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button btnlogin,btnresign;
    BD DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.sigin);
        btnresign= (Button) findViewById(R.id.signup);
        DB = new BD(this);

        btnresign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("tendn",username.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
