package com.example.logbook.Cosecha_Fresa_Vistas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class Cosecha_fresa_agregar extends Fragment {

    TextView txtitcf, txtusucf, txtfechrecf, txtscf, txtgalcf, txtpuecf;
    EditText edfechcoscf, edqtyplantacf, edlotelogcf, edworkdaycf, edcajacf;

    //Material desing
    TextInputLayout ti1, ti2, ti3, ti4, ti5, ti6, ti7, ti8;
    AutoCompleteTextView aucodorcf, auvarcf, autipoplancf;


    Button btnsclogcf, btnnworkdaycf, btnsccajacf, btnguardargcf;

    public Cosecha_fresa_agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cosecha_fresa_agregar, container, false);

        //Variable Fecha (d)
        Date d = new Date();

        txtitcf = view.findViewById(R.id.txtitcf);
        txtusucf = view.findViewById(R.id.txtusucf);
        txtfechrecf = view.findViewById(R.id.txtfechrecf);
        txtscf = view.findViewById(R.id.txtscf);
        txtgalcf = view.findViewById(R.id.txtgalcf);
        txtpuecf = view.findViewById(R.id.txtpuecf);

        //Editext
        edfechcoscf = view.findViewById(R.id.edfechcoscf);
        edqtyplantacf = view.findViewById(R.id.edqtyplantacf);
        edlotelogcf = view.findViewById(R.id.edlotelogcf);
        edworkdaycf = view.findViewById(R.id.edworkdaycf);
        edcajacf = view.findViewById(R.id.edcajacf);

        //uatocomplete
        aucodorcf = view.findViewById(R.id.aucodorcf);
        auvarcf = view.findViewById(R.id.auvarcf);
        autipoplancf = view.findViewById(R.id.autipoplancf);

        //textinput
        ti1 = view.findViewById(R.id.ti1);
        ti2 = view.findViewById(R.id.ti2);
        ti3 = view.findViewById(R.id.ti3);
        ti4 = view.findViewById(R.id.ti4);
        ti5 = view.findViewById(R.id.ti5);
        ti6 = view.findViewById(R.id.ti6);
        ti7 = view.findViewById(R.id.ti7);
        ti8 = view.findViewById(R.id.ti8);

        //Botones
        btnsclogcf = view.findViewById(R.id.btnsclogcf);
        btnnworkdaycf = view.findViewById(R.id.btnnworkdaycf);
        btnsccajacf = view.findViewById(R.id.btnsccajacf);
        btnguardargcf = view.findViewById(R.id.btnguardargcf);

        //Sacamos la fecha y la hora
        SimpleDateFormat fechah = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechahora = fechah.format(d);

        //Sacamos la fecha
        SimpleDateFormat fecc = new SimpleDateFormat("dd/MM/yyyy");
        String fechaCom = fecc.format(d);

        //Se visualiza la fecha y hora
        txtfechrecf.setText(fechahora);
        //se visualiza la fecha
        edfechcoscf.setText(fechaCom);

        //Lista de codigo de origen
        String [] items1 = {"Mario", "Jordi", "Jorge", "Uziel", "Joseline"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter1 = new ArrayAdapter<>(getActivity(), R.layout.s_dropdown_item,items1);
        aucodorcf.setAdapter(itemAdapter1);

        //Lista de Variedad---------------------------------------
        String [] items2 = {"Dayana","FC023.011","FC024.064","Itzel","Liliana","Marquis","Minerva","Verónica","Xareni","Yunuen"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter2 = new ArrayAdapter<>(getActivity(), R.layout.s_dropdown_item,items2);
        auvarcf.setAdapter(itemAdapter2);

        //Lista de Variedad---------------------------------------
        String [] items3 = {"","Verde","Rapada"};
        //Sea gregar el array adapter y se especifica la ubicacion del Variedad
        ArrayAdapter <String> itemAdapter3 = new ArrayAdapter<>(getActivity(), R.layout.s_dropdown_item,items3);
        autipoplancf.setAdapter(itemAdapter3);

        /*//se mostrara el texto en el titulo en cuanto este se seleccione
        auvarcf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtitcf.setText((String)parent.getItemAtPosition(position));
            }
        });*/

        //llamada al metodo scan
        btnsclogcf.setOnClickListener(mOnClickListener);
        btnnworkdaycf.setOnClickListener(mOnClickListener);


        btnguardargcf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //se estancia la base de firebasa
                Cosecha cosecha = new Cosecha();

                //se intancian parametros en variables para despues estanciarlas en la bd
                String Cat = "Cosecha Fresa"; // se agrega como parametro

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                //si esto es verdadero se manda a la base de datos
                DatabaseReference reference = database.getReference("Strawberry").child("Cosecha Fresa");

                //se pasan parametros a la base de datos.
                cosecha.setA_Id(reference.push().toString());
                cosecha.setB_Categoria(Cat);
                cosecha.setC_Usuario(txtusucf.getText().toString());
                cosecha.setD_Fecha_Registro(txtfechrecf.getText().toString());
                cosecha.setE_Fecha_Galera(edfechcoscf.getText().toString());
                cosecha.setG_Variedad_seleccion(auvarcf.getText().toString());
                cosecha.setH_Codigo(aucodorcf.getText().toString());
                cosecha.setI_Tipo_de_plata(autipoplancf.getText().toString());
                cosecha.setJ_Cantidad_de_planta(edqtyplantacf.getText().toString());
                cosecha.setK_Lote_logistica(edlotelogcf.getText().toString());
                cosecha.setL_Num_empleado(edlotelogcf.getText().toString());
                cosecha.setM_ID_Caja(edlotelogcf.getText().toString());

                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                //si esto es verdadero se manda a la base de datos
                //DatabaseReference reference = database.getReference("Strawberry").child("Cosecha Fresa");
                //se manda mensaje de afirmación
                Toast.makeText(Cosecha_fresa_agregar.this.getActivity(),"Datos Guardados Exitosamente", Toast.LENGTH_SHORT).show();
                //se mandan los valores a los getter an setter
                reference.push().setValue(cosecha);

            }
        });

        //agrega los datos del usuario
        recivirdatoscf();

        return view;
    }

    private void recivirdatoscf() {
        Bundle extras = getActivity().getIntent().getExtras();
        String d1 = extras.getString("da1");
        String d2 = extras.getString("da2");
        String d3 = extras.getString("da3");
        txtusucf.setText(d1);
        txtgalcf.setText(d2);
        txtpuecf.setText(d3);
    }


    private View.OnClickListener r = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnnworkdaycf:
                    IntentIntegrator integrator = new IntentIntegrator(getActivity());
                    integrator.forSupportFragment(Cosecha_fresa_agregar.this).initiateScan();
                    edworkdaycf.setText("");
                    break;
            }
        }
    };

    //se llama al scanner
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnsclogcf:
                    IntentIntegrator integrator = new IntentIntegrator(getActivity());
                    integrator.forSupportFragment(Cosecha_fresa_agregar.this).initiateScan();
                    edlotelogcf.setText("");
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
            if (result.getContents() != null){
                edlotelogcf.setText(result.getContents());
            }else {
                edlotelogcf.setText("Error de lectura");
            }
    }
}