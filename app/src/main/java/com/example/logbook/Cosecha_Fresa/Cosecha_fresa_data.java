package com.example.logbook.Cosecha_Fresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.logbook.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cosecha_fresa_data extends AppCompatActivity {

    //Validaciones de editext vacios
    //Validacion de qr lote logistica
    //

    //Variables para seteo
    TextView txtusucg, txtfechrecg, txtscg, txtgalcg, txtpuecg;
    EditText edfechcoscg, edlotelogcg;
    TextInputLayout ti1cg, ti2cg, ti3cg, ti4cg, ti5cg, ti6cg;
    AutoCompleteTextView aucodorcg, auvarcg, autipoplancg, auqtyplantacg;
    Button btnsclogcg, btnsigcg;

    String usuario, fechareg, fechagalera, galera, codorigen, variedad, tipoplanta, qtycajas, lotelog = "", puecg;

    boolean mscodorig, msvar, mstipoplan, msqty, mslotelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosecha_fresa_data);

        txtusucg = findViewById(R.id.txtusucg);
        txtfechrecg = findViewById(R.id.txtfechrecg);
        txtscg = findViewById(R.id.txtscg);
        txtgalcg = findViewById(R.id.txtgalcg);
        txtpuecg = findViewById(R.id.txtpuecg);

        edfechcoscg = findViewById(R.id.edfechcoscg);

        ti1cg = findViewById(R.id.ti1cg);
        ti2cg = findViewById(R.id.ti2cg);
        ti3cg = findViewById(R.id.ti3cg);
        ti4cg = findViewById(R.id.ti4cg);
        ti5cg = findViewById(R.id.ti5cg);

        aucodorcg = findViewById(R.id.aucodorcg);
        auvarcg = findViewById(R.id.auvarcg);
        autipoplancg = findViewById(R.id.autipoplancg);
        auqtyplantacg = findViewById(R.id.auqtyplantacg);

        btnsigcg = findViewById(R.id.btnsigcg);

        //Variable Fecha (d)
        Date d = new Date();

        //Sacamos la fecha y la hora
        SimpleDateFormat fechah = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechahora = fechah.format(d);

        //Sacamos la fecha
        SimpleDateFormat semana = new SimpleDateFormat("ww");
        String fechasem = semana.format(d);

        //Sacamos la fecha
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MM/yyyy");
        String fechaCom = fecc.format(d);

        //Se visualiza la fecha y hora
        txtfechrecg.setText(fechahora);
        //Se visualiza la semana
        txtscg.setText(fechasem);
        //se visualiza la fecha
        edfechcoscg.setText(fechaCom);

        String [] items1 = { "", "CLPORV22", "CSCRUZ22", "CSVICT22", "CSVIST22", "CTATEN22", "CTEGIP22", "CTFRAN22"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter<String> itemAdapter1 = new ArrayAdapter<>(Cosecha_fresa_data.this, R.layout.s_dropdown_item,items1);
        aucodorcg.setAdapter(itemAdapter1);

        //Lista de Variedad---------------------------------------
        String [] items2 = {"", "DAYANA","FC023.011","FC024.064","ITZEL","LILIANA","MARQUIS","MINERVA","VERONICA","XARENI","YUNUEN"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter2 = new ArrayAdapter<>(Cosecha_fresa_data.this, R.layout.s_dropdown_item,items2);
        auvarcg.setAdapter(itemAdapter2);

        //Lista de Variedad---------------------------------------
        String [] items3 = {"","VERDE","RAPADA"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter3 = new ArrayAdapter<>(Cosecha_fresa_data.this, R.layout.s_dropdown_item,items3);
        autipoplancg.setAdapter(itemAdapter3);

        //Lista de Variedad---------------------------------------
        String [] items4 = {"","100","200","300","400","500","600","700","800","900"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter4 = new ArrayAdapter<>(Cosecha_fresa_data.this, R.layout.s_dropdown_item,items4);
        auqtyplantacg.setAdapter(itemAdapter4);

        btnsigcg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valicaionesdata();

            }
        });

        recivirdatosuser();
    }

    private void recivirdatosuser() {
        Bundle extras = getIntent().getExtras();
        String d1 = extras.getString("da1");
        String d2 = extras.getString("da2");
        String d3 = extras.getString("da3");
        txtusucg.setText(d1);
        txtgalcg.setText(d2);
        txtpuecg.setText(d3);
    }

    private void valicaionesdata(){

        // Verifica si el codigo esta vacio
        if (aucodorcg.getText().toString().isEmpty()) {
            ti2cg.setError(getResources().getString(R.string.vacio));
            mscodorig = false;
        }else {
            mscodorig = true;
            ti2cg.setErrorEnabled(false);
        }


        // Verifica si la variedad esta vacia
        if (auvarcg.getText().toString().isEmpty()) {
            ti3cg.setError(getResources().getString(R.string.vacio));
            msvar = false;
        }else {
            msvar = true;
            ti3cg.setErrorEnabled(false);
        }


        // Verifica si el tipo de planta esta vacio
        if (autipoplancg.getText().toString().isEmpty()) {
            ti4cg.setError(getResources().getString(R.string.vacio));
            mstipoplan = false;
        }else {
            mstipoplan = true;
            ti4cg.setErrorEnabled(false);
        }


        // Verifica si la cantidad esta vacia
        if (auqtyplantacg.getText().toString().isEmpty()) {
            ti5cg.setError(getResources().getString(R.string.vacio));
            msqty = false;
        }else {
            msqty = true;
            ti5cg.setErrorEnabled(false);
        }


        /*
        // Verifica si el lote logistica esta esta vacio
        if (edlotelogcg.getText().toString().isEmpty()) {
            ti6cg.setError(getResources().getString(R.string.errorelotelog));
            mslotelog = false;
        }else {
            mslotelog = true;
        }
         */

        /*else if (edlotelogcg.getText().length() < 6) {
            ti6cg.setError(getResources().getString(R.string.errorcworkday));
            mslotelog = false;
        }else  {
            mslotelog = true;
            ti6cg.setErrorEnabled(false);
        }*/

        if (mscodorig && msvar && mstipoplan && msqty){
            enviar_datos();
            finish();
        }

    }

    private void enviar_datos(){

        Intent cosechaqr = new Intent(Cosecha_fresa_data.this, Cosecha_fresa_qr.class);
        usuario = txtusucg.getText().toString(); //
        fechareg = txtfechrecg.getText().toString(); //
        fechagalera = edfechcoscg.getText().toString(); //
        galera = txtgalcg.getText().toString(); //
        codorigen = aucodorcg.getText().toString(); //
        variedad = auvarcg.getText().toString(); //
        tipoplanta = autipoplancg.getText().toString(); //
        qtycajas = auqtyplantacg.getText().toString(); //
        puecg = txtpuecg.getText().toString();

        cosechaqr.putExtra("da1",usuario);
        cosechaqr.putExtra("da2",galera);
        cosechaqr.putExtra("da3",fechareg);
        cosechaqr.putExtra("da4",fechagalera);
        cosechaqr.putExtra("da5",codorigen);
        cosechaqr.putExtra("da6",variedad);
        cosechaqr.putExtra("da7",tipoplanta);
        cosechaqr.putExtra("da8",qtycajas);
        cosechaqr.putExtra("da10",puecg);

        startActivity(cosechaqr);
    }
}