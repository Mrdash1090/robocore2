package com.example.shivani.robocore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;

    TextView signUp;

    Button login;

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = findViewById(R.id.tvRegister);

        password = findViewById(R.id.etPassword);
        email = findViewById(R.id.etUserName);

        login = findViewById(R.id.btnLogin);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("userDetails");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String usernameStr = email.getText().toString();
                        String passwordStr = password.getText().toString();
                        List<String> keys  = new ArrayList<>();
                        int flag = 0;
                        for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                            keys.add(keyNode.getKey());
                            Users user = keyNode.getValue(Users.class);
                            Log.d("Error",usernameStr);
                            if(user.getEmail().equals(usernameStr) && user.getPassword().equals(passwordStr)){
                                flag=1;
                                Intent loginIntent = new Intent(getApplicationContext(), sub_registration.class);
                                loginIntent.putExtra("username", usernameStr);
                                startActivity(loginIntent);
                                finish();
                            }
                        }
                        if(flag==0){
                            Snackbar.make(v,"Invalid email or password", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
