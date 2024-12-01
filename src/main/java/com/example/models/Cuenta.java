package com.example.models;

public class Cuenta {
    
    protected float saldo;
    protected int numeroAbonos = 0;
    protected int numeroRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0;

    public Cuenta(float saldo, int numeroAbonos, int numeroRetiros, float tasaAnual, float comisionMensual) {

        this.saldo = saldo;
        this.numeroAbonos = numeroAbonos;
        this.numeroRetiros = numeroRetiros;
        this.tasaAnual = tasaAnual;
        this.comisionMensual = comisionMensual;

    }

    public Cuenta() {

    }

    public float getSaldo() {

        return saldo;

    }

    public void setSaldo(float saldo) {

        this.saldo = saldo;

    }

    public int getNumeroAbonos() {

        return numeroAbonos;

    }

    public void setNumeroAbonos(int numeroAbonos) {

        this.numeroAbonos = numeroAbonos;

    }

    public int getNumeroRetiros() {

        return numeroRetiros;

    }

    public void setNumeroRetiros(int numeroRetiros) {

        this.numeroRetiros = numeroRetiros;

    }

    public float getTasaAnual() {

        return tasaAnual;

    }

    public void setTasaAnual(float tasaAnual) {

        this.tasaAnual = tasaAnual;

    }

    public float getComisionMensual() {

        return comisionMensual;

    }

    public void setComisionMensual(float comisionMensual) {

        this.comisionMensual = comisionMensual;

    }

    void ingresar(float cantidad) {

        if (cantidad > 0) {
            saldo += cantidad;
            numeroAbonos++;
        } else {
            System.out.println("Error en el ingreso. Debe ingresar una cantidad");
        }

    }

    void retirar(float cantidad) {

        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            numeroRetiros++;
        } else {
            System.out.println("El dinero a retirar debe ser igual o superior al dinero en cuenta");
        }

    }

    void calcularInteres() {

        float interes = (tasaAnual / 12) * saldo;
        saldo += interes;

    }

    void extractoMensual() {

        saldo -= comisionMensual;
        
        calcularInteres();

        comisionMensual = 0;

    }

    public void visualizar() {

        System.out.println("Saldo: " + saldo);
        System.out.println("Número de abonos: " + numeroAbonos);
        System.out.println("Número de retiros: " + numeroRetiros);
        System.out.println("Tasa Anual: " + tasaAnual);
        System.out.println("Comisión Mensual: " + comisionMensual);

    }

}
