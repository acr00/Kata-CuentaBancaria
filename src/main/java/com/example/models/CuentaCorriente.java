package com.example.models;

public class CuentaCorriente extends Cuenta {

    private float sobregiro = 0;

    public CuentaCorriente(float saldo, int numeroAbonos, int numeroRetiros, float comisionMensual, float tasaAnual, int sobregiro) {

        super(saldo, numeroAbonos, numeroRetiros, comisionMensual, tasaAnual);
        this.sobregiro = sobregiro;

    }

    public CuentaCorriente() {

    }

    public void setSobregiro(float sobregiro) {

        this.sobregiro = sobregiro;

    }

    public float getSobregiro() {

        return sobregiro;

    }

    @Override
    void ingresar(float cantidad) {

        if (sobregiro > 0) {
            if (cantidad >= sobregiro) {
                cantidad -= sobregiro;
                sobregiro = 0;

                super.ingresar(cantidad);
            } else {
                sobregiro -= cantidad;
            }
        } else {
            super.ingresar(cantidad);
        }

    }

    @Override
    void retirar(float cantidad) {

        if (cantidad > saldo) {
            float dif = cantidad - saldo;
            sobregiro += dif;

            saldo = 0;
        } else {
            saldo -= cantidad;
        }

    }

    @Override
    void extractoMensual() {
        super.extractoMensual();
    }

    @Override
    public void visualizar() {
    
        super.visualizar();

        System.out.println(sobregiro == 0 ? "No hay sobregiro" : ("Valor del sobregiro: " + sobregiro + "/n" + "Nº transacciones realizadas: " + (numeroAbonos - numeroRetiros)));

    }
}
