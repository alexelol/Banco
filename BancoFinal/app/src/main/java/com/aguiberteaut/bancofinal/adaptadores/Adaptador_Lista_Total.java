package com.aguiberteaut.bancofinal.adaptadores;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aguiberteaut.bancofinal.MainActivity;
import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

import java.util.List;

public class Adaptador_Lista_Total extends RecyclerView.Adapter<Adaptador_Lista_Total.ViewHolder>{
    private List<Movimiento> total;
    private Context context;
    private View viewRoot;
    private ViewHolder viewHolder;

    public Adaptador_Lista_Total(Context context, List tot){
        total = tot;
        this.context =context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCodigo, tvConcepto, tvCantidad, tvTipo;
        public ViewHolder (View v){
            super(v);
            tvCodigo = (TextView) v.findViewById(R.id.tvCodigo);
            tvConcepto = (TextView) v.findViewById(R.id.tvConcepto);
            tvCantidad = (TextView) v.findViewById(R.id.tvCantidad);
            tvTipo = (TextView) v.findViewById(R.id.tvTipo);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewRoot = LayoutInflater.from(context).inflate(R.layout.item_total, parent, false);
        viewHolder = new ViewHolder(viewRoot);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int i = total.get(position).getIdMov();
        holder.tvCodigo.setText(String.valueOf(i));
        holder.tvConcepto.setText(total.get(position).getConcepto());
        double d = total.get(position).getCantidad();
        holder.tvCantidad.setText(String.valueOf(d)+"€");
        if (total.get(position).getTipoMov()== 1){
            holder.tvTipo.setText("Ingreso");
        }else {
            holder.tvTipo.setText("Gasto");
        }
    }

    @Override
    public int getItemCount() {

        return total.size();
    }
}