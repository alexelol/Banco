package com.aguiberteaut.bancofinal.adaptadores;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

import java.util.List;

public class Adaptador_Lista_Ingresos extends RecyclerView.Adapter<Adaptador_Lista_Ingresos.ViewHolder>{
    private List<Movimiento> ingresos;
    private Context context;
    private View viewRoot;
    private ViewHolder viewHolder;

    public Adaptador_Lista_Ingresos(Context context, List ing){
        ingresos = ing;
        this.context =context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodigo, tvConcepto, tvCantidad;
        public ViewHolder (View v){
            super(v);
            tvCodigo = (TextView) v.findViewById(R.id.tvCodigo);
            tvConcepto = (TextView) v.findViewById(R.id.tvConcepto);
            tvCantidad = (TextView) v.findViewById(R.id.tvCantidad);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewRoot = LayoutInflater.from(context).inflate(R.layout.item_movimiento, parent, false);
        viewHolder = new ViewHolder(viewRoot);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int i = ingresos.get(position).getIdMov();
        holder.tvCodigo.setText(String.valueOf(i));
        holder.tvConcepto.setText(ingresos.get(position).getConcepto());
        double d = ingresos.get(position).getCantidad();
        holder.tvCantidad.setText(String.valueOf(d)+"€");
    }

    @Override
    public int getItemCount() {

        return ingresos.size();
    }
}