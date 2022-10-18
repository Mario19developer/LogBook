package com.example.logbook.CFAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook.CFModelo.Cosecha;
import com.example.logbook.R;

import java.util.List;

public class CosechaAdapter extends RecyclerView.Adapter<CosechaAdapter.CosechaHolder> {
    //se agrega e
    List <Cosecha> lista;
    int layout;
    Activity activity;

    public CosechaAdapter(List<Cosecha> lista, int layout, Activity activity){

        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CosechaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //se genera la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new CosechaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CosechaAdapter.CosechaHolder holder, int position) {

        //se carga toda la informacion a las listas
        Cosecha cosechah = lista.get(position);

        //Se les pasa el parametro que se encuentra en firebase
        holder.txidcf.setText(cosechah.getA_Id()); //ID
        holder.txfcharegcf.setText(cosechah.getD_Fecha_Registro());
        holder.txuscf.setText(cosechah.getC_Usuario()); //
        holder.txfccf.setText(cosechah.getE_Fecha_Galera()); //
        holder.txgalcf.setText(cosechah.getF_Galera()); //
        holder.txvarcf.setText(cosechah.getG_Variedad_seleccion()); //
        holder.txqtycf.setText(cosechah.getJ_Cantidad_de_planta()); //
        holder.txcodorcf.setText(cosechah.getH_Codigo()); //
        holder.txtipoplancf.setText(cosechah.getI_Tipo_de_plata()); //
        holder.txnumemplcf.setText(cosechah.getL_Num_empleado()); //
        holder.txcajacf.setText(cosechah.getM_ID_Caja()); //

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class CosechaHolder extends RecyclerView.ViewHolder{
        //se agregan los elementos del layout a esta clase
        TextView txidcf, txfcharegcf, txuscf, txfccf, txgalcf, txvarcf, txqtycf, txcodorcf, txtipoplancf, txnumemplcf, txcajacf;


        public CosechaHolder(@NonNull View itemView) {
            super(itemView);

            //seteo de layout
            txidcf = itemView.findViewById(R.id.txidcf);
            txfcharegcf = itemView.findViewById(R.id.txfcharegcf);
            txuscf = itemView.findViewById(R.id.txuscf);
            txfccf = itemView.findViewById(R.id.txfccf);
            txgalcf = itemView.findViewById(R.id.txgalcf);
            txvarcf = itemView.findViewById(R.id.txvarcf);
            txqtycf = itemView.findViewById(R.id.txqtycf);
            txcodorcf = itemView.findViewById(R.id.txcodorcf);
            txtipoplancf = itemView.findViewById(R.id.txtipoplancf);
            txnumemplcf = itemView.findViewById(R.id.txnumemplcf);
            txcajacf = itemView.findViewById(R.id.txcajacf);
        }
    }
}
