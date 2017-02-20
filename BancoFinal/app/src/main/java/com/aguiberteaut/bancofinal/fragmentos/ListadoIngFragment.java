package com.aguiberteaut.bancofinal.fragmentos;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aguiberteaut.bancofinal.MainActivity;
import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.adaptadores.Adaptador_Lista_Ingresos;
import com.aguiberteaut.bancofinal.dao.BD;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

import java.util.List;

public class ListadoIngFragment extends Fragment {
    private View viewRoot;
    MainActivity mainActivity;
    BD bd;
    private List<Movimiento> ingreso;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        bd = new BD(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ingreso = bd.getIngresos(mainActivity.numCuenta);
        viewRoot = inflater.inflate(R.layout.fragment_listadoing, container, false);

        RecyclerView rvIngresos = (RecyclerView) viewRoot.findViewById(R.id.rvListadoIng);
        rvIngresos.setHasFixedSize(true);
        LinearLayoutManager llManager = new LinearLayoutManager(viewRoot.getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvIngresos.setLayoutManager(llManager);

        Adaptador_Lista_Ingresos ing = new Adaptador_Lista_Ingresos(viewRoot.getContext(), ingreso);
        rvIngresos.setAdapter(ing);
        return  viewRoot;
    }

}
