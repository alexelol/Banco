package com.aguiberteaut.bancofinal.dao;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aguiberteaut.bancofinal.MainActivity;
import com.aguiberteaut.bancofinal.R;
import com.aguiberteaut.bancofinal.entidades.Cuenta;
import com.aguiberteaut.bancofinal.entidades.Movimiento;
import com.aguiberteaut.bancofinal.entidades.Usuario;
import com.aguiberteaut.bancofinal.fragmentos.ListadoIngFragment;
import com.aguiberteaut.bancofinal.fragmentos.ListadoTotalFragment;

import java.util.ArrayList;
import java.util.List;

public class BD  extends SQLiteOpenHelper {
    static final String NOMBREDB = "banco";
    static final int VERSION = 1;
    private Context contexto;
    final int ORDEN_ASCENDENTE = 0;
    final int ORDEN_DESCENDENTE = 1;
    private ArrayList<Usuario> listausuarios;
    private ArrayList<Cuenta> listaCuenta;
    private ArrayList<Movimiento> listaIngresos;
    private ArrayList<Movimiento> listaGastos;
    private ArrayList<Movimiento> listaTotal;

    public BD (Context context) {
        super (context, NOMBREDB, null, VERSION);
        this.contexto = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, password TEXT)";
        db.execSQL(sql);

        String sql1 = "CREATE TABLE cuentas(numCuenta INTEGER PRIMARY KEY AUTOINCREMENT, id INTEGER, saldo REAL, FOREIGN KEY(id) REFERENCES usuarios(id));";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE movimientos(idMovimiento INTEGER PRIMARY KEY AUTOINCREMENT, tipoMov INTEGER, cantidad REAL, numCuenta TEXT, concepto TEXT, FOREIGN KEY(numCuenta) REFERENCES cuentas(numCuenta));";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        if (oldVersion == 0 ) {
            sql = "CREATE TABLE usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, password TEXT);";
            db.execSQL(sql);
        }

    }

    public boolean addUsuario (Usuario usuario) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;

        ContentValues campos = new ContentValues();
        campos.put("nombre", usuario.getUsuario());
        campos.put("password", usuario.getPassword());
        try{
            bd.insert("usuario", null, campos);
            insertado = true;
        }catch (Exception ex) {

        }
        return insertado;
    }

    public boolean updateUsuario (Usuario usuario) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;

        ContentValues campos = new ContentValues();
        campos.put("usuario", usuario.getUsuario());
        campos.put("password", usuario.getPassword());
        try{
            bd.update("usuarios", campos, "id="+usuario.getId(),null);
            insertado = true;
        }catch (Exception ex){

        }
        return insertado;
    }

    public boolean deleteUsuario (Usuario usuario){
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;


        try {
            bd.delete("usuarios", "id="+usuario.getId(), null);
            insertado = true;
        }catch (Exception ex) {

        }
        return insertado;
    }

    public Usuario getUsuarioById(int id) {
        SQLiteDatabase bd = getReadableDatabase();
        Usuario usuario = null;
        String sql = "Select id, nombre, password from usuario where id="+id;
        Cursor cursor = bd.rawQuery(sql,null);
        if(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setUsuario(cursor.getString(cursor.getColumnIndex("nombre")));
            usuario.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        bd.close();
        return usuario;
    }

    public List<Usuario> getUsuarioByNombre(Usuario u1) {
        listausuarios = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Usuario usuario = null;
        String sql = "Select id, nombre, password from usuario where nombre='"+u1.getUsuario()+"'";
        Cursor cursor = bd.rawQuery(sql,null);
        if(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setUsuario(cursor.getString(cursor.getColumnIndex("nombre")));
            usuario.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            listausuarios.add(usuario);
        }
        bd.close();
        return listausuarios;
    }

    public boolean addCuenta(Cuenta cuenta, Usuario usuario){
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;
        SQLiteDatabase db = getReadableDatabase();
        Usuario u1 = null;
        String sql = "select id from usuario where nombre='"+usuario.getUsuario()+"'";
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToNext()){
            u1 = new Usuario();
            u1.setId(c.getInt(c.getColumnIndex("id")));
        }
        ContentValues campos = new ContentValues();
        campos.put("id", u1.getId());
        campos.put("saldo", 0);

        try {
            bd.insert("cuentas", null, campos);
        } catch (Exception ex) {

        }
        return insertado;
    }

    public boolean updateCuenta (Cuenta cuenta, Usuario usuario) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;

        ContentValues campos = new ContentValues();
        campos.put("id", usuario.getId());
        campos.put("numCuenta", cuenta.getNumCuenta());
        campos.put("saldo", cuenta.getSaldo());
        try {
            bd.update("cuentas", campos, "numCuenta="+cuenta.getNumCuenta(), null);
            insertado = true;
        } catch (Exception ex) {

        }
        return insertado;

    }

    public boolean deleteCuenta (Cuenta cuenta) {
        SQLiteDatabase bd = getWritableDatabase();
        boolean insertado = false;

        try {
            bd.delete("cuentas", "numCuenta="+cuenta.getNumCuenta(), null);
            insertado = true;
        } catch (Exception ex){

        }
        return insertado;
    }

    public Cuenta getCuentaByNumCuenta (int numCuenta){
        SQLiteDatabase bd = getReadableDatabase();
        Cuenta cuenta = null;
        String sql = "Select numCuenta, id, saldo from cuentas where numCuenta="+numCuenta;
        Cursor cursor = bd.rawQuery(sql,null);
        if(cursor.moveToNext()){
            cuenta = new Cuenta();
            cuenta.setNumCuenta(cursor.getInt(cursor.getColumnIndex("numCuenta")));
            cuenta.setIdUsuario(cursor.getInt(cursor.getColumnIndex("id")));
            cuenta.setSaldo(cursor.getDouble(cursor.getColumnIndex("saldo")));
        }
        bd.close();
        return cuenta;
    }

    public List<Cuenta> getCuentaByID (int id){
        listaCuenta = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Cuenta cuenta = null;
        String sql = "Select numCuenta, id, saldo from cuentas where id="+id;
        Cursor c = bd.rawQuery(sql,null);
        if(c.moveToNext()){
            cuenta = new Cuenta();
            cuenta.setNumCuenta(c.getInt(c.getColumnIndex("numCuenta")));
            cuenta.setIdUsuario(c.getInt(c.getColumnIndex("id")));
            cuenta.setSaldo(c.getInt(c.getColumnIndex("saldo")));
            listaCuenta.add(cuenta);
        }
        bd.close();
        return listaCuenta;
    }

    public boolean addMovimiento (Movimiento mov, Cuenta cuenta){
        SQLiteDatabase db = getWritableDatabase();
        boolean insertado = false;
        ContentValues camposMov = new ContentValues();
        ContentValues camposCuenta = new ContentValues();
        camposMov.put("tipoMov", mov.getTipoMov());
        camposMov.put("cantidad", mov.getCantidad());
        if (mov.getTipoMov()== 2){
            camposCuenta.put("saldo", cuenta.getSaldo() - mov.getCantidad());

        }else{
            camposCuenta.put("saldo", cuenta.getSaldo() + mov.getCantidad());
        }
        camposMov.put("numCuenta", cuenta.getNumCuenta());
        camposMov.put("concepto", mov.getConcepto());

        try{
            db.insert("movimientos", null, camposMov);
            db.update("cuentas", camposCuenta, "numCuenta="+cuenta.getNumCuenta(), null);
            insertado = true;
        }catch (Exception ex){

        }
        return insertado;
    }


    public List<Movimiento> getMovimientosByCuenta (int numCuenta){
        listaTotal = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Movimiento mov = null;
        String sql = "Select idMovimiento, tipoMov, cantidad, numCuenta, concepto from movimientos where numCuenta="+numCuenta;

        Cursor cursor = bd.rawQuery(sql,null);
        while (cursor.moveToNext()){
            mov = new Movimiento();
            mov.setIdMov(cursor.getInt(cursor.getColumnIndex("idMovimiento")));
            mov.setTipoMov(cursor.getInt(cursor.getColumnIndex("tipoMov")));
            mov.setCantidad(cursor.getDouble(cursor.getColumnIndex("cantidad")));
            mov.setConcepto(cursor.getString(cursor.getColumnIndex("concepto")));
            listaTotal.add(mov);

        }
        bd.close();
        return listaTotal;

    }

    public List<Movimiento> getIngresos (int numCuenta) {
        listaIngresos = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Movimiento mov = null;
        Movimiento m1 = null;
        String sql = "Select idMovimiento, tipoMov, cantidad, numCuenta, concepto from movimientos where numCuenta=" + numCuenta;
        Cursor cursor = bd.rawQuery(sql, null);
        while(cursor.moveToNext()){
            mov = new Movimiento();
            m1 = new Movimiento();
            m1.setTipoMov(cursor.getInt(cursor.getColumnIndex("tipoMov")));
            mov.setIdMov(cursor.getInt(cursor.getColumnIndex("idMovimiento")));
            mov.setCantidad(cursor.getDouble(cursor.getColumnIndex("cantidad")));
            mov.setConcepto(cursor.getString(cursor.getColumnIndex("concepto")));
            if (m1.getTipoMov() == 1) {
                listaIngresos.add(mov);
            }
        }
        bd.close();
        return listaIngresos;
    }

    public Double getSaldo (int numCuenta){
        SQLiteDatabase db = getReadableDatabase();
        Cuenta cuenta = null;

        String sql = "Select saldo from cuentas where numCuenta="+numCuenta;
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToNext()) {
            cuenta = new Cuenta();
            cuenta.setSaldo(c.getInt(c.getColumnIndex("saldo")));
        }

        return cuenta.getSaldo();
    }

    public List<Movimiento> getGastos (int numCuenta) {
        listaGastos = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Movimiento mov = null;
        Movimiento m1 = null;
        String sql = "Select idMovimiento, tipoMov, cantidad, numCuenta, concepto from movimientos where numCuenta=" + numCuenta;
        Cursor cursor = bd.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            mov = new Movimiento();
            m1 = new Movimiento();
            m1.setTipoMov(cursor.getInt(cursor.getColumnIndex("tipoMov")));
            mov.setIdMov(cursor.getInt(cursor.getColumnIndex("idMovimiento")));
            mov.setCantidad(cursor.getDouble(cursor.getColumnIndex("cantidad")));
            mov.setConcepto(cursor.getString(cursor.getColumnIndex("concepto")));
            if (m1.getTipoMov() == 2) {
                listaGastos.add(mov);
            }
        }
        bd.close();
        return listaGastos;
    }


}
