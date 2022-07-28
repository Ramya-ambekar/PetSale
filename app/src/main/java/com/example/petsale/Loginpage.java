package com.example.petsale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class Loginpage extends AppCompatActivity {

    EditText usernametxt,pwdtxt;
    Button signinbutton;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        usernametxt=findViewById(R.id.txtuname);
        pwdtxt=findViewById(R.id.txtpwd);
        signup=findViewById(R.id.joinnowbtn);
        signinbutton=findViewById(R.id.loginbtn);

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!usernametxt.getText().toString().isEmpty() && !pwdtxt.getText().toString().isEmpty())
            {
                ParseUser.logInInBackground(usernametxt.getText().toString(), pwdtxt.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null)
                        {
                            Intent i = new Intent(Loginpage.this,Adminhomepage.class);
                            Toast.makeText(getApplicationContext(), "Login Sucessfull", Toast.LENGTH_LONG).show();
                            startActivity(i);
                        }
                        else if(usernametxt.getText().toString().equals("ramya")&& pwdtxt.getText().toString().equals("1234")){
                            Intent intent=new Intent(Loginpage.this,Adminhomepage.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Failed to login \n Incorrect username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed to login \n Please enter username or password", Toast.LENGTH_LONG).show();

            }
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Loginpage.this,Register.class);
                startActivity(intent);
            }
        });



    }
}