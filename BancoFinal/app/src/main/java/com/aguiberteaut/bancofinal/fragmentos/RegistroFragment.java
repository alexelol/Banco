package com.aguiberteaut.bancofinal.fragmentos;


import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.aguiberteaut.bancofinal.entidades.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment implements View.OnClickListener {
    private View v;
    MainActivity mainActivity;
    private EditText etUsuario, etPassword;
    private Button btRegistro, btLimpiar, btAtras;
    BD bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        bd = new BD(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_registro, container, false);
        etUsuario = (EditText) v.findViewById(R.id.etUSuario);
        etPassword = (EditText) v.findViewById(R.id.etContraseña);
        btRegistro = (Button) v.findViewById(R.id.btRegistro);
        btLimpiar = (Button) v.findViewById(R.id.btLimpiarReg);
        btAtras = (Button) v.findViewById(R.id.btAtras);
        btLimpiar.setOnClickListener(this);
        btRegistro.setOnClickListener(this);
        btAtras.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btRegistro:
                Usuario u1 = new Usuario();
                u1.setUsuario(etUsuario.getText().toString());
                u1.setPassword(etPassword.getText().toString());
                bd.addUsuario(u1);
                etUsuario.setText("");
                etPassword.setText("");
                Cuenta cuenta = new Cuenta();
                cuenta.setSaldo(0);
                cuenta.setNumCuenta((int)(Math.random()*100));
                bd.addCuenta(cuenta,u1);
                Toast.makeText(mainActivity, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btLimpiarReg:
                etUsuario.setText("");
                etPassword.setText("");
                break;
            case R.id.btAtras:
                LoginFragment lf = new LoginFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fmContenedor, lf).commit();
                break;

        }
    }

}
