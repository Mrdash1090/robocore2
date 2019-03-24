package com.example.shivani.robocore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class sub_registration extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref, childref;
    Button save, finish;
    EditText subject, credit;

    //Subjects subjects;

    // ArrayList for person names
    //ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_registration);

        save = findViewById(R.id.add_sub);
        finish = findViewById(R.id.finish_sub);

        //subjects = new Subjects();

        subject = findViewById(R.id.subject);
        credit = findViewById(R.id.credit);

        database = FirebaseDatabase.getInstance();
        //ref = database.getReference("subject/abc");
        //childref = ref.child("abc");

        final String subStr = subject.getText().toString();
        final String creditStr = credit.getText().toString();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String subStr = subject.getText().toString();
                final String creditStr = credit.getText().toString();
                Intent intent=getIntent();
                String username=intent.getStringExtra("username");

                int days=0;
                double absent=0;

                if(creditStr.equals("4")){
                    days=52;
                    absent=days-(0.85*days);
                }
                else if(creditStr.equals("3")){
                    days=39;
                    absent=days-(0.85*days);

                }

                ref = database.getReference("subject/"+username);
                try {
                    ref.child(subStr).child("subjectname").setValue(subStr);
                    ref.child(subStr).child("credit").setValue(creditStr);
                    ref.child(subStr).child("daysofclasses").setValue(days);
                    ref.child(subStr).child("canbeabsent").setValue((int)absent);
                    Toast.makeText(getApplicationContext(),absent+" is added",Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error signing up", Toast.LENGTH_LONG).show();
                }
                subject.setText("");
                credit.setText("");
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home_pg.class);
                Intent user = getIntent();
                String username = intent.getStringExtra("username");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

       /* //get the reference of recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), personNames);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView*/

    }
}