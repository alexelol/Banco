package com.aguiberteaut.bancofinal.fragmentos;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aguiberteaut.bancofinal.MainActivity;
import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.adaptadores.Adaptador_Lista_Ingresos;
import com.aguiberteaut.bancofinal.adaptadores.Adaptador_Lista_Total;
import com.aguiberteaut.bancofinal.dao.BD;
import com.aguiberteaut.bancofinal.entidades.Cuenta;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

import java.util.List;

public class ListadoTotalFragment extends Fragment {
    private View viewRoot;
    MainActivity mainActivity;
    BD bd;
    private List<Movimiento> total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        bd = new BD(mainActivity);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        total = bd.getMovimientosByCuenta(mainActivity.numCuenta);
        viewRoot = inflater.inflate(R.layout.fragment_listado_total, container, false);

        RecyclerView rvIngresos = (RecyclerView) viewRoot.findViewById(R.id.rvListadoTotal);
        rvIngresos.setHasFixedSize(true);
        LinearLayoutManager llManager = new LinearLayoutManager(viewRoot.getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvIngresos.setLayoutManager(llManager);

        Adaptador_Lista_Total ing = new Adaptador_Lista_Total(viewRoot.getContext(), total);
        rvIngresos.setAdapter(ing);
        return viewRoot;
    }

}