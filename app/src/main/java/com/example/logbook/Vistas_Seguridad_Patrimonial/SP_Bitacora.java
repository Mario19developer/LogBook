package com.example.logbook.Vistas_Seguridad_Patrimonial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.logbook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SP_Bitacora extends AppCompatActivity {

    TextView txtusub, txtfecregb, txtsemb, txtzonb, txtpueb;
    EditText edfecin, edfecsal, ednumeroemb, ednombreempb, edcompb, edplacasb, edtempb,  edvisitadob, edotromotvib;
    Spinner spzonb, spranb, spranpb, spmotvib;
    Button btnscanb, btnbusb, btnguardarb;

    //conexion a firebase
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_bitacora);

        //Variable Fecha (d)
        Date d = new Date();

        txtusub = findViewById(R.id.txtusub); //usuario
        txtfecregb = findViewById(R.id.txtfecregb); //fecha registro
        txtsemb = findViewById(R.id.txtsemb); // semana
        txtzonb = findViewById(R.id.txtzonb); //zona
        txtpueb = findViewById(R.id.txtpueb); // puesto
        edfecin = findViewById(R.id.edfecin); //fecha ingreso
        edfecsal = findViewById(R.id.edfecsal); //fecha salida
        ednumeroemb = findViewById(R.id.ednumeroemb);
        ednombreempb = findViewById(R.id.ednombreempb); // nombre empleado
        edcompb = findViewById(R.id.edcompb); // compañia
        edplacasb = findViewById(R.id.edplacasb); // placas
        edtempb = findViewById(R.id.edtempb); // temperatura
        edvisitadob = findViewById(R.id.edvisitadob); // visita
        edotromotvib = findViewById(R.id.edotromotvib); // motivo visita
        spzonb = findViewById(R.id.spzonb); // zona
        spranb = findViewById(R.id.spranb); // rancho de recepcion
        spranpb = findViewById(R.id.spranpb); //  rancho de origen
        spmotvib = findViewById(R.id.spmotvib); // motivo visita
        btnscanb = findViewById(R.id.btnscanb); // escanear
        btnbusb = findViewById(R.id.btnbusb); // buscar
        btnguardarb = findViewById(R.id.btnguardarb); // gardar registro

        //Sacamos la fecha completa
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MM/yyyy");
        String fechaComString = fecc.format(d);

        SimpleDateFormat feca = new SimpleDateFormat("yy");
        String fechaacom = feca.format(d);

        SimpleDateFormat fec = new SimpleDateFormat("ww");
        String fechacom = fec.format(d);

        txtfecregb.setText(fechaComString);
        txtsemb.setText(fechaacom+fechacom);
        edfecin.setText(fechaComString);
        edfecsal.setText(fechaComString);

        //llamada al metodo scan
        btnscanb.setOnClickListener(mOnClickListener);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recivirdatosb();

    }

    private void recivirdatosb() {
        Bundle extras = getIntent().getExtras();
        String d1 = extras.getString("da1");
        String d2 = extras.getString("da2");
        String d3 = extras.getString("da3");
        txtusub.setText(d1);
        txtzonb.setText(d2);
        txtpueb.setText(d3);
    }

    //se valida si se leyo el codigo o no pudo hacerce a lectura
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
            if (result.getContents() != null){
                ednumeroemb.setText(result.getContents());
            }else {
                ednumeroemb.setText("Error de lectura");
            }
    }

    //Integracion del scanner
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnscanb:
                    new IntentIntegrator(SP_Bitacora.this).initiateScan();
                    ednumeroemb.setText("");
                    break;
            }
        }
    };
}