package com.example.logbook.Cosecha_Fresa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logbook.CFModelo.Cosecha;
import com.example.logbook.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cosecha_fresa_qr extends AppCompatActivity {

    TextView txtusucgg, txtgaleracgg, txtpuestogg, txtfechareg, txtfechagalcgg, txtcodorigencgg, txtvariedadcgg, txttipoplancgg, txtqtycgg, txtlotelogiscgg;
    EditText edworkdaycgg, edcajacgg;
    TextInputLayout ti1cgg, ti2cgg;
    Button btnscannumworcgg, btnscaqrcajacgg, btnsalirmpcgg, btnguardadgalcgg;
    String nwork, caja, usuario, galera, codorigen, variedad, tipoplanta, qtycajas, lotelog = "", puesto;
    String usuario_gg, galera_gg, puesto_gg, puestoc;
    String vempleado, vcaja;

    boolean msworkday, mscaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosecha_fresa_qr);

        //Seteo de layout
        txtusucgg = findViewById(R.id.txtusucgg);
        txtgaleracgg = findViewById(R.id.txtgaleracgg);
        txtpuestogg = findViewById(R.id.txtpuestogg);
        txtfechareg = findViewById(R.id.txtfechareg);
        txtfechagalcgg = findViewById(R.id.txtfechagalcgg);
        txtcodorigencgg = findViewById(R.id.txtcodorigencgg);
        txtvariedadcgg = findViewById(R.id.txtvariedadcgg);
        txttipoplancgg = findViewById(R.id.txttipoplancgg);
        txtqtycgg = findViewById(R.id.txtqtycgg);
        edworkdaycgg = findViewById(R.id.edworkdaycgg);
        edcajacgg = findViewById(R.id.edcajacgg);
        ti1cgg = findViewById(R.id.ti1cgg);
        ti2cgg = findViewById(R.id.ti2cgg);
        btnscannumworcgg = findViewById(R.id.btnscannumworcgg);
        btnscaqrcajacgg = findViewById(R.id.btnscaqrcajacgg);
        btnsalirmpcgg = findViewById(R.id.btnsalirmpcgg);
        btnguardadgalcgg = findViewById(R.id.btnguardadgalcgg);

        edworkdaycgg.setEnabled(true);
        edcajacgg.setEnabled(true);

        //Se manda a activar el scanner para el numero de workday
        btnscannumworcgg.setOnClickListener(cOnClickListener);

        //Se manda a activar el scanner para el id de la caja
        btnscaqrcajacgg.setOnClickListener(cOnClickListener);

        //Boton guardar
        btnguardadgalcgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nwork = edworkdaycgg.getText().toString();
                edcajacgg.getText().toString();

                validacionescosecha();

                /*
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //si esto es verdadero se manda a la base de datos
                DatabaseReference reference = database.getReference("Strawberry").child("Cosecha Fresa");

                Query query = reference.orderByChild("m_ID_Caja").equalTo(edcajacgg.getText().toString()).limitToFirst(1);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    //si es correcta
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        for (DataSnapshot ds : snapshot.getChildren()){
                            Cosecha f = ds.getValue(Cosecha.class);
                            f.setA_Id(ds.getKey());
                            //cosecha.setA_Id(ds.getKey());
                            Log.e("info","------->"+f.getM_ID_Caja());
                            Toast.makeText(Cosecha_fresa_qr.this,f.getM_ID_Caja(),Toast.LENGTH_SHORT).show();
                        }


                        if (snapshot.exists()){

                            Toast.makeText(Cosecha_fresa_qr.this,"Esta caja ya fue registrada", Toast.LENGTH_SHORT).show();

                        }else {
                            nwork = edworkdaycgg.getText().toString();
                            edcajacgg.getText().toString();
                            validacionqr();
                            validacionescosecha();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("info","------->");
                    }
                });

                 */
            }
        });

        btnsalirmpcgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrardialogoregresomenu();

            }
        });

        recivirdatoscosecha();

    }

    private void mostrardialogoagregarregistro(){

        //Variable Fecha (d)
        Date d = new Date();

        //Sacamos la fecha y la hora
        SimpleDateFormat fechah = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechahora = fechah.format(d);

        //Se visualiza la fecha y hora
        txtfechareg.setText(fechahora);

        //se estancia la base de firebasa
        Cosecha cosecha = new Cosecha();

        String lotelog = "Lote Logistica";

        //se intancian parametros en variables para despues estanciarlas en la bd
        String Cat = "Cosecha Fresa"; // se agrega como parametro
        //se instancia la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //si esto es verdadero se manda a la base de datos
        DatabaseReference reference = database.getReference("Strawberry").child("Cosecha Fresa");

        //se pasan parametros a la base de datos.
        cosecha.setA_Id(reference.push().getKey());
        cosecha.setB_Categoria(Cat);
        cosecha.setC_Usuario(txtusucgg.getText().toString());
        cosecha.setD_Fecha_Registro(txtfechareg.getText().toString());
        cosecha.setE_Fecha_Galera(txtfechagalcgg.getText().toString());
        cosecha.setF_Galera(txtgaleracgg.getText().toString());
        cosecha.setG_Variedad_seleccion(txtvariedadcgg.getText().toString());
        cosecha.setH_Codigo(txtcodorigencgg.getText().toString());
        cosecha.setI_Tipo_de_plata(txttipoplancgg.getText().toString());
        cosecha.setJ_Cantidad_de_planta(txtqtycgg.getText().toString());
        cosecha.setK_Lote_logistica(lotelog);
        cosecha.setL_Num_empleado(edworkdaycgg.getText().toString());
        cosecha.setM_ID_Caja(edcajacgg.getText().toString());

        //se manda mensaje de afirmación
        Toast.makeText(Cosecha_fresa_qr.this,"Datos Guardados Exitosamente", Toast.LENGTH_SHORT).show();
        //se mandan los valores a los getter an setter
        reference.push().setValue(cosecha);

        limpiezaqr();
    }

    //descativa el boton de regreso a la pantalla 1
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //descativa el boton de retroseso
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //regresar al menu princiapal
    private void mostrardialogoregresomenu(){
        new AlertDialog.Builder(Cosecha_fresa_qr.this)
                .setTitle("Advertencia")
                .setMessage("¿Deseas regresar al menú anterior?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //getApplicationContext
                        Intent cosechafqr = new Intent(Cosecha_fresa_qr.this, Cosecha_fresa_data.class);

                        usuario_gg = txtusucgg.getText().toString();
                        galera_gg = txtgaleracgg.getText().toString();
                        puesto_gg = txtpuestogg.getText().toString();

                        cosechafqr.putExtra("da1",usuario_gg);
                        cosechafqr.putExtra("da2",galera_gg);
                        cosechafqr.putExtra("da3",puesto_gg);

                        finish();

                        startActivity(cosechafqr);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void recivirdatoscosecha() {

        Bundle extras = getIntent().getExtras();
        String d1 = extras.getString("da1");
        String d2 = extras.getString("da2");
        String d3 = extras.getString("da3");
        String d4 = extras.getString("da4");
        String d5 = extras.getString("da5");
        String d6 = extras.getString("da6");
        String d7 = extras.getString("da7");
        String d8 = extras.getString("da8");
        String d10 = extras.getString("da10");

        txtusucgg.setText(d1);
        txtgaleracgg.setText(d2);
        txtfechareg.setText(d3);
        txtfechagalcgg.setText(d4);
        txtcodorigencgg.setText(d5);
        txtvariedadcgg.setText(d6);
        txttipoplancgg.setText(d7);
        txtqtycgg.setText(d8);
        txtpuestogg.setText(d10);

    }

    private void validacionescosecha(){
        // no sea menos a 5 pero que tampoco sea mayor a 7
        // valida numero de workday
        if (edworkdaycgg.getText().toString().isEmpty()) {
            ti1cgg.setError(getResources().getString(R.string.erroreworkday));
            msworkday = false;

        } else if (edworkdaycgg.getText().length() < 4) {
            ti1cgg.setError(getResources().getString(R.string.error_invalid_emin));
            msworkday = false;

        } else if (edworkdaycgg.getText().length() > 7) {
            ti1cgg.setError(getResources().getString(R.string.error_invalid_emax));
            msworkday = false;
        }

        else{
            msworkday = true;
            ti1cgg.setErrorEnabled(false);
        }

        // valida el id de la caja, no puedes ser mayor menor a 8 caracteres
        // lectura de las 2 valores primarios SC, HC, LC
        if (edcajacgg.getText().toString().isEmpty()) {
            ti2cgg.setError(getResources().getString(R.string.errorecaja));
            mscaja = false;

        } else if (edcajacgg.getText().length() < 8) {
            ti2cgg.setError(getResources().getString(R.string.error_invalid_cmin));
            mscaja = false;

        } else if (edcajacgg.getText().length() > 8) {
            ti2cgg.setError(getResources().getString(R.string.error_invalid_cmax));
            mscaja = false;

        } else {
            mscaja = true;
            ti2cgg.setErrorEnabled(false);
        }

        //si ambos estan llenos entonces se procede a seguir ejecutando
        if (msworkday && mscaja) {

            String work = edworkdaycgg.getText().toString().trim();
            String vwork = work.substring(0,1);

            String box = edcajacgg.getText().toString().trim();
            String vbox = box.substring(0,2);

            //si el numero de workday tiene una B, S, L, H est mal tambien si inica con un 0, 1, 2, 3, 8, 9
            if (vwork.equals("H") || vwork.equals("S") || vwork.equals("L") || vwork.equals("B") || vwork.equals("0") || vwork.equals("1") || vwork.equals("2") || vwork.equals("3") || vwork.equals("9")){
                Toast.makeText(Cosecha_fresa_qr.this, "# Empleado es" + " " + vwork , Toast.LENGTH_SHORT).show();
                ti1cgg.setError(getResources().getString(R.string.error_invalid_werror));
                msworkday = false;
            }
            // si la caja tiene las letras
            else if (vbox.equals("HC") || vbox.equals("SC") || vbox.equals("LC")){
                mostrardialogoagregarregistro();
            }

            else {
                Toast.makeText(Cosecha_fresa_qr.this, "Galera es " + " " + vbox + " " + vwork , Toast.LENGTH_SHORT).show();
                ti1cgg.setError(getResources().getString(R.string.error_invalid_werror));
                ti2cgg.setError(getResources().getString(R.string.error_invalid_cerror));
                mscaja = false;
            }
        }
    }

    private void limpiezaqr() {
        edworkdaycgg.setText("");
        edcajacgg.setText("");

        edworkdaycgg.setEnabled(true);
        edcajacgg.setEnabled(true);
    }

    //se inicializa la integracion del scanner caja
    private final View.OnClickListener cOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnscannumworcgg:
                    new IntentIntegrator(Cosecha_fresa_qr.this).initiateScan();
                    edworkdaycgg.setText("");
                    break;

                case R.id.btnscaqrcajacgg:
                    new IntentIntegrator(Cosecha_fresa_qr.this).initiateScan();
                    edcajacgg.setText("");
                    break;
            }
        }
    };

    //se valida si se leyo el codigo o no pudo hacerce a lectura
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String workday = edworkdaycgg.getText().toString().trim();
        String idcaja = edworkdaycgg.getText().toString().trim();

        if (workday.equals("")){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null)
                if (result.getContents() != null){
                    edworkdaycgg.setText(result.getContents());
                }else {
                    edworkdaycgg.setText("Error de lectura # de workday");
                    //edworkdayecgg.setEnabled(true);
                }
        }else {
            IntentResult resultt = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (resultt != null)
                if (resultt.getContents()!= null){
                    edcajacgg.setText(resultt.getContents());
                } else {
                    edcajacgg.setText("");
                }
        }
    }
}