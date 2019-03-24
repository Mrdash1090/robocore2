package com.example.shivani.robocore;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;

    Users user;

    Button signUp;
    EditText name, email, username, password, passwordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.signUp);

        name = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        passwordAgain = findViewById(R.id.confirmPassword);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("userDetails");

        user = new Users();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameStr = name.getText().toString();
                String emailStr = email.getText().toString();
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();
                String passwordAgainStr = passwordAgain.getText().toString();

                if ((nameStr.equals("")) || (passwordStr.equals("")) || (passwordAgainStr.equals("")) || (emailStr.equals("")) || (usernameStr.equals(""))){
                    Snackbar.make(v, "All the fields are required", Snackbar.LENGTH_LONG).show();
                }
                else if(passwordStr.compareTo(passwordAgainStr)!=0 || passwordStr.length()<8 || passwordStr.length()>16){
                    Snackbar.make(v, "Passwords doesn't match or too long/short", Snackbar.LENGTH_LONG).show();
                }
                else{
                    user.setName(nameStr);
                    user.setEmail(emailStr);
                    user.setUsername(usernameStr);
                    user.setPassword(passwordStr);
                    //user.setSubjects("false");

                    try{
                        ref.child(emailStr).setValue(user);
                        Intent signupIntent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(signupIntent);
                        Toast.makeText(getApplicationContext(), "Sign up successful", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Error signing up", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });


    }
}
