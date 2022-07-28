package com.example.petsale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class Register extends AppCompatActivity {
    Button regbutton;
    EditText inputname,inputemail,inputaddr,inputpwd,inputphone;
    private ProgressDialog loadingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regbutton=findViewById(R.id.regpagebtn);
        inputname=findViewById(R.id.txtname);
        inputemail=findViewById(R.id.txtemail);
        inputphone=findViewById(R.id.txtphone);
        inputaddr=findViewById(R.id.txtaddr);
        inputpwd=findViewById(R.id.txtpwd);
        //loadingbar=new ProgressDialog(this);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAccount();
            }
        });
    }
//
    private void CreateAccount() {
        String name=inputname.getText().toString();
        String email=inputemail.getText().toString();
        String addr=inputaddr.getText().toString();
        String phone=inputphone.getText().toString();
        String pwd=inputpwd.getText().toString();

        if(name.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter your name..", Toast.LENGTH_LONG).show();
        }
        else if(email.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter your email..", Toast.LENGTH_LONG).show();
        }
        else if(addr.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter your address..", Toast.LENGTH_LONG).show();
        }
        else if(phone.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter your phone number..", Toast.LENGTH_LONG).show();
        }
        else if(pwd.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter your password..", Toast.LENGTH_LONG).show();
        }
            addDataToDatabase(name,email,addr,phone,pwd);

     }
    private void addDataToDatabase(String name, String email, String addr, String phone, String pwd) {

        ParseObject registerusers = new ParseObject("register");

        registerusers.put("Name", name);
        registerusers.put("Email", email);
        registerusers.put("Address", addr);
        registerusers.put("Phone", phone);
        registerusers.put("Password",pwd);

        registerusers.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(Register.this, "Data has been successfully added to Database", Toast.LENGTH_LONG).show();
                    inputname.setText("");
                    inputemail.setText("");
                    inputaddr.setText("");
                    inputphone.setText("");
                    inputpwd.setText("");
                } else {
                    // if the error is not null we will be
                    // displaying an error message to our user.
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }



}