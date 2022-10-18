package com.example.logbook.Cosecha_Fresa_Vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logbook.CFAdapter.CosechaAdapter;
import com.example.logbook.CFModelo.Cosecha;
import com.example.logbook.CFModelo.Cosechaservice;
import com.example.logbook.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Cosecha_fresa_lista extends Fragment {

    //se agrega la lista para llenarse
    RecyclerView rccf;

    public Cosecha_fresa_lista() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cosecha_fresa_lista, container, false);

        rccf = view.findViewById(R.id.rccf);

        //se genera el layout mostandrando un apartado el cual se replicara a todos los datos guardado
        LinearLayoutManager lmcf = new LinearLayoutManager(getActivity());
        lmcf.setOrientation(RecyclerView.VERTICAL);
        lmcf.setReverseLayout(true);
        lmcf.setStackFromEnd(true);
        rccf.setLayoutManager(lmcf);

        CosechaAdapter adapter = new CosechaAdapter(Cosechaservice.cosechas,R.layout.fresa_cosecha,getActivity());
        rccf.setAdapter(adapter);

        //se llama al metodo para traer datos de firebase
        cargardatoscffb();

        return view;
    }

    private void cargardatoscffb() {
        // se instancia la base de datos
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Strawberry").child("Cosecha Fresa");

        Query query = reference.orderByChild("m_ID_Caja").equalTo("mario.castillo@driscolls.com").limitToFirst(5);


        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Cosecha cosecha = snapshot.getValue(Cosecha.class);
                cosecha.setA_Id(snapshot.getKey());

                if (!Cosechaservice.cosechas.contains(cosecha)){
                    Cosechaservice.addcosecha(cosecha);
                }

                rccf.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Cosecha cosecha = snapshot.getValue(Cosecha.class);
                cosecha.setA_Id(snapshot.getKey());

                if (Cosechaservice.cosechas.contains(cosecha)){
                    Cosechaservice.updatecosecha(cosecha);
                }

                rccf.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                Cosecha cosecha = snapshot.getValue(Cosecha.class);
                cosecha.setA_Id(snapshot.getKey());

                if (Cosechaservice.cosechas.contains(cosecha)){
                    Cosechaservice.removecosecha(cosecha);
                }

                rccf.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}