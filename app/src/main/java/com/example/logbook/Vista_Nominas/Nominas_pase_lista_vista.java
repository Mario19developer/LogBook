package com.example.logbook.Vista_Nominas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logbook.R;


public class Nominas_pase_lista_vista extends Fragment {

    public Nominas_pase_lista_vista() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nominas_pase_lista_vista, container, false);
    }
}