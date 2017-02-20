package com.aguiberteaut.bancofinal.entidades;



public class Movimiento {

    private int idMov, tipoMov, numCuenta;
    private double cantidad;
    private String concepto;

    public int getIdMov() {return idMov;}

    public void setIdMov(int idMov) {this.idMov = idMov;}

    public int getTipoMov() {return tipoMov;}

    public void setTipoMov(int tipoMov) {this.tipoMov = tipoMov;}

    public int getNumCuenta() {return numCuenta;}

    public void setNumCuenta(int numCuenta) {this.numCuenta = numCuenta;}

    public double getCantidad() {return cantidad;}

    public void setCantidad(double cantidad) {this.cantidad = cantidad;}

    public String getConcepto() {return concepto;}

    public void setConcepto(String concepto) {this.concepto = concepto;}
}
