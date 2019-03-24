package com.example.shivani.robocore;

import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Subjects {

    FirebaseDatabase database;
    DatabaseReference ref;

    public void mainCalculation()
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("");
    }

}
