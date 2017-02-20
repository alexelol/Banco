package com.aguiberteaut.bancofinal.fragmentos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aguiberteaut.bancofinal.MainActivity;
import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.dao.BD;
import com.aguiberteaut.bancofinal.entidades.Cuenta;
import com.aguiberteaut.bancofinal.entidades.Movimiento;

public class GastoFragment extends Fragment implements View.OnClickListener{
    private View viewRoot;
    private EditText etCantidad, etConcepto;
    private Button btGasto, btLimpiarGasto;
    private MainActivity mainActivity;
    BD bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        bd = new BD(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_gasto, container, false);
        etCantidad = (EditText) viewRoot.findViewById(R.id.etCantidadGasto);
        etConcepto = (EditText) viewRoot.findViewById(R.id.etConceptoGasto);
        btGasto = (Button) viewRoot.findViewById(R.id.btGasto);
        btLimpiarGasto = (Button) viewRoot.findViewById(R.id.btLimpiarGast);
        btGasto.setOnClickListener(this);
        btLimpiarGasto.setOnClickListener(this);
        return viewRoot;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btGasto:
                try{
                Movimiento m1 = new Movimiento();
                m1.setNumCuenta(mainActivity.numCuenta);
                m1.setTipoMov(2);
                m1.setCantidad(Double.parseDouble(etCantidad.getText().toString()));
                m1.setConcepto(etConcepto.getText().toString());
                Cuenta c1 = new Cuenta();
                c1.setNumCuenta(mainActivity.numCuenta);
                c1.setSaldo(mainActivity.saldo);
                bd.addMovimiento(m1, c1);
                mainActivity.saldo = c1.getSaldo() - m1.getCantidad();
                    etConcepto.setText("");
                    etCantidad.setText("");
                    Toast.makeText(mainActivity, "Gasto registrado", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(mainActivity, "Error al introducir gasto, intenta de nuevo", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btLimpiarGast:
                etConcepto.setText("");
                etCantidad.setText("");
                break;
        }

    }
}
