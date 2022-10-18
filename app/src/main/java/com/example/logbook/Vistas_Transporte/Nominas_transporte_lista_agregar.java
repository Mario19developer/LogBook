package com.example.logbook.Vistas_Transporte;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.logbook.Menu_Nominas_TPF_Lista;
import com.example.logbook.R;

public class Nominas_transporte_lista_agregar extends Fragment {

    Button btnfinrecorrido;


    public Nominas_transporte_lista_agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nominas_transporte_lista_agregar, container, false);

        btnfinrecorrido = view.findViewById(R.id.btnfinrecorrido);

        btnfinrecorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registrogalera = new Intent(getContext(), Menu_Nominas_TPF_Lista.class);
                startActivity(registrogalera);

            }
        });

        return view;
    }
}