package com.example.logbook.Vista_Nominas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logbook.NominasModelo.Nominas;
import com.example.logbook.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Nominas_pase_lista_agregar extends Fragment{

    TextView txtusuplp, txtfechregplp, txtsemplp, txtranchoplp, txtpuestoplp;
    EditText edqrempleadoplp;
    RadioGroup rd1;
    RadioButton rbingresoplp, rbsalidaplp;
    TextInputLayout ti1plp, ti2plp, ti3plp;
    AutoCompleteTextView auzonaplp, auranplp;
    Button btnscanplp, btnguardarplp;

    String usuario_pl, rancho_pl, puesto_pl;

    String aa1;
    boolean rbingpl, rbsalpl, zonapl, ranchopl, qrpl;

    //Lista Zonas para seleccionar Ranchos
    ArrayAdapter<String> Lzona, Lhua, Llib, Lsec, Ltan, Lguz;

    //listas de llenado Rancho - Zona
    String[] Zonapa = {"Huamantla" , "El Seco" , "Libres" , "Tangancicuaro" , "Guzman"};
    String[] Hua = {"" , "San Antonio Atenco" , "El Balcón" , "Coyahualulco" , "Egipto" , "La Concepción" , "Pozo Blanco" , "San Diego I" , "San Diego II" , "San Francisco" , "Xonecuila" , "Varios"};
    String[] Lib = {"" , "Porvenir" , "Invernadero" , "Cajones " , "Campanario" , "Cañas Largas" , "Santa Julia" , "Varios"};
    String[] Sec = {"" , "La Cruz" , "Buena Vista" , "Concepción" , "El Sabinal" , "Morelos", "Victoria" , "Zimatepec" , "Varios"};
    String[] Tan = {"" , "El Moral"};
    String[] Guz = {"" , "Granaditos" , "Maravillas"};

    public Nominas_pase_lista_agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nominas_pase_lista_agregar, container, false);

        //Variable Fecha (d)
        Date d = new Date();

        txtusuplp = view.findViewById(R.id.txtusuplp);
        txtfechregplp = view.findViewById(R.id.txtfechregplp);
        txtsemplp = view.findViewById(R.id.txtsemplp);
        txtranchoplp= view.findViewById(R.id.txtranchoplp);
        txtpuestoplp = view.findViewById(R.id.txtpuestoplp);
        rd1 = view.findViewById(R.id.rd1);
        rbingresoplp = view.findViewById(R.id.rbingresoplp);
        rbsalidaplp = view.findViewById(R.id.rbsalidaplp);
        auzonaplp = view.findViewById(R.id.auzonaplp);
        auranplp = view.findViewById(R.id.auranplp);
        edqrempleadoplp = view.findViewById(R.id.edqrempleadoplp);
        btnscanplp = view.findViewById(R.id.btnscanplp);
        btnguardarplp = view.findViewById(R.id.btnguardarplp);

        Lzona = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Zonapa);
        Lhua = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Hua);
        Llib = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Lib);
        Lsec = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Sec);
        Ltan = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Tan);
        Lguz = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Guz);

        auzonaplp.setAdapter(Lzona);

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
        txtfechregplp.setText(fechahora);
        //Se visualiza la semana
        txtsemplp.setText(fechasem);

        //llamada al metodo scan
        btnscanplp.setOnClickListener(mOnClickListener);

        auzonaplp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (auzonaplp.getText().toString().trim().equals("Huamantla")){
                    auranplp.setText("");
                    auranplp.setAdapter(Lhua);
                }

                if (auzonaplp.getText().toString().trim().equals("El Seco")){
                    auranplp.setText("");
                    auranplp.setAdapter(Lsec);
                }

                if (auzonaplp.getText().toString().trim().equals("Libres")){
                    auranplp.setText("");
                    auranplp.setAdapter(Llib);
                }

                if (auzonaplp.getText().toString().trim().equals("Tangancicuaro")){
                    auranplp.setText("");
                    auranplp.setAdapter(Ltan);
                }
            }
        });

        //Valida ingreso
        //revisa el rancho y la zona
        //revisa que el numero de empleado sea correcto

        btnguardarplp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Nominas nominas = new Nominas();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Nominas_2023").child("Pase_Lista");

                String catbkpa = "Pase de lista";
                String usuplp = txtusuplp.getText().toString();
                String fechreplp = txtfechregplp.getText().toString();
                String ranchoplp = txtranchoplp.getText().toString();
                String ingresoplp = rbingresoplp.getText().toString();
                String salidaplp = rbsalidaplp.getText().toString();
                String zonaplp = auzonaplp.getText().toString();
                String ranplp = auranplp.getText().toString();
                String qrempleadoplp = edqrempleadoplp.getText().toString();
                String coordenadas = "";

                //primera validacion
                if(edqrempleadoplp.getText().toString().isEmpty()){
                    validacionesplp();
                    Toast.makeText(getContext(),"Seleccióna Ingreso o Salida", Toast.LENGTH_SHORT).show();
                }

                else {
                    //si se marca ingreso
                    if (rbingresoplp.isChecked()){
                        nominas.setB_Categoria(catbkpa);
                        nominas.setC_Usuario(usuplp);
                        nominas.setD_Fecha_Registro(fechreplp);
                        nominas.setE_Zona_Dispositivo(ranchoplp);
                        nominas.setF_Tipo_Lista(ingresoplp);
                        nominas.setG_Zona_Registro(zonaplp);
                        nominas.setH_Rancho_Registro(ranplp);
                        nominas.setI_QR_Empleado(qrempleadoplp);
                        nominas.setJ_Latitud(coordenadas);
                        nominas.setK_Longitud(coordenadas);
                        reference.push().setValue(nominas);
                        Toast.makeText(getContext(),"Bienvenido... ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(),"Datos Guardados", Toast.LENGTH_SHORT).show();

                    }
                    else if(rbsalidaplp.isChecked()){
                        nominas.setB_Categoria(catbkpa);
                        nominas.setC_Usuario(usuplp);
                        nominas.setD_Fecha_Registro(fechreplp);
                        nominas.setE_Zona_Dispositivo(ranchoplp);
                        nominas.setF_Tipo_Lista(salidaplp);
                        nominas.setG_Zona_Registro(zonaplp);
                        nominas.setH_Rancho_Registro(ranplp);
                        nominas.setI_QR_Empleado(qrempleadoplp);
                        nominas.setJ_Latitud(coordenadas);
                        nominas.setK_Longitud(coordenadas);
                        reference.push().setValue(nominas);
                        Toast.makeText(getContext(),"Hasta Luego...", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(),"Datos Guardados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void validacionesplp() {

        String rbi = rbingresoplp.getText().toString();
        String rbs = rbsalidaplp.getText().toString();
        rbingresoplp.getText().toString();

        //Seleccion de boton
        if(!rbingresoplp.isChecked() && !(rbsalidaplp.isChecked())){
            Toast.makeText(getContext(),"Seleccióna Ingreso o Salida", Toast.LENGTH_SHORT).show();
            rbingpl = false;
            rbsalpl = false;
        }
        else if (rbingresoplp.isChecked()){
            rbingpl = true;
        }
        else if(rbsalidaplp.isChecked()){
            rbsalpl = true;
        }

        //Zona
        if (auzonaplp.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"Seleccióna Zona", Toast.LENGTH_SHORT).show();
            zonapl = false;
        }else {
            zonapl = true;
        }

        //Rancho
        if (auranplp.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"Selecciona Rancho", Toast.LENGTH_SHORT).show();
            ranchopl = false;
        }else {
            ranchopl = true;
        }

        //Escaneo de QR
        if (edqrempleadoplp.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"Por favor escanea un empleado", Toast.LENGTH_SHORT).show();
            qrpl = false;
        }else if (edqrempleadoplp.getText().length() < 4) {
            Toast.makeText(getContext(),"El QR no puede ser menor a 4", Toast.LENGTH_SHORT).show();
            qrpl = false;
        } else if (edqrempleadoplp.getText().length() > 7) {
            Toast.makeText(getContext(),"El QR no puede ser mayor a 7", Toast.LENGTH_SHORT).show();
            qrpl = false;
        } else {
            qrpl = true;
            Toast.makeText(getContext(),"Escaneo correcto", Toast.LENGTH_SHORT).show();
        }

        if (zonapl && ranchopl && qrpl){

            String qrv = edqrempleadoplp.getText().toString().trim();
            String qrvv = qrv.substring(0,1);

            if (qrvv.equals("A") || qrvv.equals("B") || qrvv.equals("C") || qrvv.equals("D") || qrvv.equals("E") || qrvv.equals("F")
                    || qrvv.equals("G") || qrvv.equals("H") || qrvv.equals("I") || qrvv.equals("J") || qrvv.equals("K") || qrvv.equals("L")
                    || qrvv.equals("M") || qrvv.equals("N") || qrvv.equals("O") || qrvv.equals("P") || qrvv.equals("Q") || qrvv.equals("R")
                    || qrvv.equals("S") || qrvv.equals("T") || qrvv.equals("U") || qrvv.equals("V") || qrvv.equals("W") || qrvv.equals("X")
                    || qrvv.equals("Y") || qrvv.equals("Z")){
                Toast.makeText(getContext(),"El QR no puede tener letras", Toast.LENGTH_SHORT).show();
                qrpl = false;
            }
        }
    }

    //se llama al scanner
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnscanplp:
                    IntentIntegrator integrator = new IntentIntegrator(getActivity());
                    integrator.forSupportFragment(Nominas_pase_lista_agregar.this).initiateScan();
                    edqrempleadoplp.setText("");
                    break;
            }
        }
    };

    //se inicializa y valida si se leyo el codigo o no pudo hacerce a lectura
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null)
            if (result.getContents() != null) {
                edqrempleadoplp.setText(result.getContents());
            } else {
                edqrempleadoplp.setText("Error de lectura");
            }
    }
}