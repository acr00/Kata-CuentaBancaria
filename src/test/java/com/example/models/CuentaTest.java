package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaTest {
    private Cuenta cuenta;
    private Random random;

    @BeforeEach
    void inicio() {
        cuenta = new Cuenta();
        random = new Random();
    }

    @Test
    void testGettersAndSetters() {
        // Generate random but reasonable values
        int saldo = random.nextInt(50000) + 10000;
        int numeroAbonos = random.nextInt(50) + 10;
        int numeroRetiros = random.nextInt(30) + 5;
        float tasaAnual = random.nextFloat() * 10f; // 0-10%
        float comisionMensual = random.nextFloat() * 100f; // 0-100

        cuenta.setSaldo(saldo);
        cuenta.setNumeroAbonos(numeroAbonos);
        cuenta.setNumeroRetiros(numeroRetiros);
        cuenta.setTasaAnual(tasaAnual);
        cuenta.setComisionMensual(comisionMensual);

        assertEquals(saldo, cuenta.getSaldo());
        assertEquals(numeroAbonos, cuenta.getNumeroAbonos());
        assertEquals(numeroRetiros, cuenta.getNumeroRetiros());
        assertEquals(tasaAnual, cuenta.getTasaAnual(), 0.001);
        assertEquals(comisionMensual, cuenta.getComisionMensual(), 0.001);
    }

    @Test
    void testIngresar() {
        int saldoInicial = random.nextInt(20000) + 5000;
        float cantidadIngreso = random.nextFloat() * 5000f + 1000f;

        cuenta.setSaldo(saldoInicial);
        cuenta.ingresar(cantidadIngreso);

        assertEquals(1, cuenta.getNumeroAbonos());
        assertEquals(saldoInicial + cantidadIngreso, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testRetirar() {
        int saldoInicial = random.nextInt(20000) + 10000;
        float cantidadRetiro = random.nextFloat() * (saldoInicial / 2f);

        cuenta.setSaldo(saldoInicial);
        cuenta.retirar(cantidadRetiro);

        assertEquals(saldoInicial - cantidadRetiro, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testCalcularInteres() {
        int saldoInicial = random.nextInt(50000) + 10000;
        float tasaAnual = random.nextFloat() * 0.2f; // 0-20%

        cuenta.setSaldo(saldoInicial);
        cuenta.setTasaAnual(tasaAnual);

        cuenta.calcularInteres();

        float interesEsperado = (tasaAnual / 12) * saldoInicial;
        assertEquals(saldoInicial + interesEsperado, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testExtractoMensual() {
        int saldoInicial = random.nextInt(30000) + 10000;
        float tasaAnual = random.nextFloat() * 0.1f; // 0-10%
        float comisionMensual = random.nextFloat() * 100f; // 0-100

        cuenta.setSaldo(saldoInicial);
        cuenta.setTasaAnual(tasaAnual);
        cuenta.setComisionMensual(comisionMensual);

        // Simular algunas transacciones
        float cantidadIngreso = random.nextFloat() * 5000f;
        float cantidadRetiro = random.nextFloat() * 3000f;

        cuenta.ingresar(cantidadIngreso);
        cuenta.retirar(cantidadRetiro);

        cuenta.extractoMensual();

        float interesEsperado = (tasaAnual / 12) * (saldoInicial + cantidadIngreso - cantidadRetiro);
        float saldoFinal = saldoInicial + cantidadIngreso - cantidadRetiro - comisionMensual + interesEsperado;
        
        assertEquals(saldoFinal, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testImprimir() {
        int saldoInicial = random.nextInt(30000) + 10000;
        float cantidadIngreso = random.nextFloat() * 5000f;
        float cantidadRetiro = random.nextFloat() * 3000f;

        cuenta.setSaldo(saldoInicial);
        cuenta.ingresar(cantidadIngreso);
        cuenta.retirar(cantidadRetiro);

        cuenta.visualizar();

        assertEquals(saldoInicial + cantidadIngreso - cantidadRetiro, cuenta.getSaldo(), 0.01);
        assertEquals(1, cuenta.getNumeroAbonos());
        assertEquals(1, cuenta.getNumeroRetiros());
    }
}
