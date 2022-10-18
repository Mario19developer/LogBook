package com.example.logbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.logbook.Cosecha_Fresa.Cosecha_fresa_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView txtusupsmp, txtfechapsmp, txtsempsmp, txtzonpsmp, txtpuespsmp;
    ImageButton ibingresotransporte, ibpaselista, ibreggalmp, ibvisureggalmp, ibinformacion, ibcerrarsesionmp;
    String userem, zonm, pue = "";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Variable Fecha (d)
        Date d = new Date();

        txtusupsmp = findViewById(R.id.txtusump);
        txtfechapsmp = findViewById(R.id.txtfechamp);
        txtsempsmp = findViewById(R.id.txtsemmp);
        txtzonpsmp = findViewById(R.id.txtzonmp);
        txtpuespsmp = findViewById(R.id.txtpuemp);

        ibingresotransporte = findViewById(R.id.ibingresotransporte);
        ibpaselista = findViewById(R.id.ibpaselista);
        ibreggalmp = findViewById(R.id.ibreggalmp);
        ibinformacion = findViewById(R.id.ibinformacion);
        ibcerrarsesionmp = findViewById(R.id.ibcerrarsesionmp);
        ibvisureggalmp = findViewById(R.id.ibvisureggalmp);

        //Sacamos la fecha completa
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fecc = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComString = fecc.format(d);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat feca = new SimpleDateFormat("yy");
        String fechaacom = feca.format(d);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fec = new SimpleDateFormat("ww");
        String fechacom = fec.format(d);

        txtfechapsmp.setText(fechaComString);
        txtsempsmp.setText(fechacom);

        // se inicia el layout Cosecha fresa
        ibreggalmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reggal = new Intent(MainActivity.this, Cosecha_fresa_data.class);
                userem = txtusupsmp.getText().toString();
                zonm = txtzonpsmp.getText().toString();
                pue = txtpuespsmp.getText().toString();
                reggal.putExtra("da1",userem);
                reggal.putExtra("da2",zonm);
                reggal.putExtra("da3",pue);
                startActivity(reggal);
            }
        });

        // se inicia el men principal
        ibingresotransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r1 = new Intent(MainActivity.this, Menu_Nominas_TP_Lista.class);
                startActivity(r1);
            }
        });

        // se inicia el men principal
        ibpaselista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r2 = new Intent(MainActivity.this, Menu_Nominas_Pase_Lista.class);
                startActivity(r2);
            }
        });

        ibvisureggalmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrogalera = new Intent(MainActivity.this,CFMenu.class);
                userem = txtusupsmp.getText().toString();
                zonm = txtzonpsmp.getText().toString();
                pue = txtpuespsmp.getText().toString();
                registrogalera.putExtra("da1",userem);
                registrogalera.putExtra("da2",zonm);
                registrogalera.putExtra("da3",pue);
                startActivity(registrogalera);
            }
        });

        //Cerrar Sesion
        ibcerrarsesionmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });

        getUserinfo();
    }

    private void getUserinfo() {
        String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String email = Objects.requireNonNull(dataSnapshot.child("Email").getValue()).toString();
                    String puesto = Objects.requireNonNull(dataSnapshot.child("Puesto").getValue()).toString();
                    String zona = Objects.requireNonNull(dataSnapshot.child("Zona").getValue()).toString();

                    txtusupsmp.setText(email);
                    txtzonpsmp.setText(zona);
                    txtpuespsmp.setText(puesto);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}