package com.example.shivani.robocore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;

    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        signUp = findViewById(R.id.tvRegister);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent);
            }
        });
    }
}
