package com.aguiberteaut.bancofinal.fragmentos;


import android.app.Fragment;
import android.app.FragmentManager;
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
import java.util.List;

public class LoginFragment extends Fragment implements View.OnClickListener{
    private View v;
    private EditText etUsuario, etContrasena;
    private Button btnLogin, btnRegistro;
    MainActivity mainActivity;

    BD bd;
    List<Usuario> listaUsuario;
    List<Cuenta> listaCuenta;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity)getActivity();
        bd = new BD(mainActivity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login, container, false);
        etUsuario = (EditText) v.findViewById(R.id.etUsuarioLogin);
        etContrasena = (EditText) v.findViewById(R.id.etPasswordLogin);
        btnLogin = (Button) v.findViewById(R.id.btLogin);
        btnRegistro = (Button) v.findViewById(R.id.btRegistroLogin);
        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:

                Usuario u1 = new Usuario();
                u1.setUsuario(etUsuario.getText().toString());
                u1.setPassword(etContrasena.getText().toString());
                listaUsuario = bd.getUsuarioByNombre(u1);
                for (int i = 0; listaUsuario.size() > i; i++){
                    if (listaUsuario.get(i).getUsuario().equals(u1.getUsuario()) && listaUsuario.get(i).getPassword().equals(u1.getPassword())){
                        mainActivity.idUsuario = listaUsuario.get(i).getId();
                        listaCuenta = bd.getCuentaByID(mainActivity.idUsuario);
                        mainActivity.numCuenta = listaCuenta.get(i).getNumCuenta();
                        mainActivity.saldo = listaCuenta.get(i).getSaldo();
                        mainActivity.saldoLV = bd.getSaldo(mainActivity.numCuenta);
                        ListadoTotalFragment fragmentTotal = new ListadoTotalFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fmContenedor, fragmentTotal).commit();


                    }else{
                        Toast.makeText(mainActivity, "Login incorrecto, intenta de nuevo", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btRegistroLogin:
                RegistroFragment rf = new RegistroFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fmContenedor, rf).commit();
                break;

        }
    }
}
