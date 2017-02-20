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
import com.aguiberteaut.bancofinal.adaptadores.Adaptador_Lista_Gastos;
import com.aguiberteaut.bancofinal.dao.BD;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

import java.util.List;

public class ListadoGastosFragment extends Fragment {
    private View viewRoot;
    MainActivity mainActivity;
    BD bd;
    private List<Movimiento> gasto;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        bd = new BD(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gasto = bd.getGastos(mainActivity.numCuenta);
        viewRoot = inflater.inflate(R.layout.fragment_listadogastos, container, false);

        RecyclerView rvGastos = (RecyclerView) viewRoot.findViewById(R.id.rvListadoGastos);
        rvGastos.setHasFixedSize(true);
        LinearLayoutManager llManager = new LinearLayoutManager(viewRoot.getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGastos.setLayoutManager(llManager);

        Adaptador_Lista_Gastos gastitos = new Adaptador_Lista_Gastos(viewRoot.getContext(), gasto);
        rvGastos.setAdapter(gastitos);
        return  viewRoot;
    }

}