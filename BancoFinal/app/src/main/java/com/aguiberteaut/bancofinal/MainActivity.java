package com.aguiberteaut.bancofinal;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aguiberteaut.bancofinal.dao.BD;
import com.aguiberteaut.bancofinal.fragmentos.GastoFragment;
import com.aguiberteaut.bancofinal.fragmentos.IngresoFragment;
import com.aguiberteaut.bancofinal.fragmentos.ListadoGastosFragment;
import com.aguiberteaut.bancofinal.fragmentos.ListadoIngFragment;
import com.aguiberteaut.bancofinal.fragmentos.ListadoTotalFragment;
import com.aguiberteaut.bancofinal.fragmentos.LoginFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public int numCuenta;
    public int idUsuario;
    public double saldo;
    public double saldoLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BD bd=new BD(this);
        login();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

   @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.CrearGasto) {
            GastoFragment fragmentoGasto = new GastoFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fmContenedor, fragmentoGasto).commit();

        } else if (id == R.id.CrearIngreso) {
            IngresoFragment fragmentoIngreso = new IngresoFragment();
            FragmentTransaction ft2 = getFragmentManager().beginTransaction();
            ft2.replace(R.id.fmContenedor, fragmentoIngreso).commit();


        } else if (id == R.id.ListaGastos) {
            ListadoGastosFragment fragmentListaGastos = new ListadoGastosFragment();
            FragmentTransaction ft3 = getFragmentManager().beginTransaction();
            ft3.replace(R.id.fmContenedor, fragmentListaGastos).commit();

        } else if (id == R.id.ListaIngresos) {
            ListadoIngFragment fragmentListaIng = new ListadoIngFragment();
            FragmentTransaction ft4 = getFragmentManager().beginTransaction();
            ft4.replace(R.id.fmContenedor, fragmentListaIng).commit();

        } else if (id == R.id.ListadoTotal) {
            ListadoTotalFragment fragmentTotal = new ListadoTotalFragment();
            FragmentTransaction ft5 = getFragmentManager().beginTransaction();
            ft5.replace(R.id.fmContenedor, fragmentTotal).commit();

        } else if (id == R.id.CerrarSesion){
            LoginFragment lf = new LoginFragment();
            FragmentTransaction ft6 = getFragmentManager().beginTransaction();
            ft6.replace(R.id.fmContenedor, lf).commit();
            numCuenta = 0;
            idUsuario = 0;
            saldo = 0;

        } else if (id == R.id.Salir){
            System.exit(0);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void login(){
        LoginFragment lfragment = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fmContenedor, lfragment).commit();
    }
}
