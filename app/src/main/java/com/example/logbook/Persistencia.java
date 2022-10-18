package com.example.logbook;

import com.google.firebase.database.FirebaseDatabase;

public class Persistencia extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
