package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {
    private CuentaAhorros cuentaAhorros;
    private Random random;

    @BeforeEach
    void setUp() {
        cuentaAhorros = new CuentaAhorros();
        random = new Random();
    }

    @Test
    void testIsActive() {
        boolean result = cuentaAhorros.isActive();
        assertTrue(result);
    }

    @Test
    void testIngresar() {
        
        int saldoInicial = random.nextInt(20000) + 10000;
     
        int cantidadIngreso = random.nextInt(1000) + 100;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.ingresar(cantidadIngreso);

        assertEquals(saldoInicial + cantidadIngreso, cuentaAhorros.getSaldo());
        assertTrue(cuentaAhorros.isActive());
    }

    @Test
    void testIngresarDesactivada() {
 
        int saldoInicial = random.nextInt(5000) + 5000;

        int cantidadIngreso = random.nextInt(500) + 100;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.ingresar(cantidadIngreso);

        assertEquals(saldoInicial + cantidadIngreso, cuentaAhorros.getSaldo());
        assertFalse(cuentaAhorros.isActive());
    }

    @Test
    void testRetirar() {

        int saldoInicial = random.nextInt(20000) + 10000;

        int cantidadRetiro = random.nextInt(2000) + 500;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.retirar(cantidadRetiro);

        assertEquals(saldoInicial - cantidadRetiro, cuentaAhorros.getSaldo());
        assertTrue(cuentaAhorros.isActive());
    }

    @Test
    void testRetirarDesactivada() {

        int saldoInicial = random.nextInt(1000) + 1000;

        int cantidadRetiro = random.nextInt(2000) + 100;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.retirar(cantidadRetiro);

        assertEquals(saldoInicial - cantidadRetiro, cuentaAhorros.getSaldo());
        assertFalse(cuentaAhorros.isActive());
    }

    @Test
    void testExtractoMensualSinMaxRetiros() {

        int saldoInicial = random.nextInt(20000) + 10000;

        float tasaAnual = random.nextFloat() * 0.1f; // 0-10%

        float comisionMensual = random.nextFloat() * 100f;

        int cantidadRetiro = random.nextInt(1000) + 100;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.setTasaAnual(tasaAnual);
        cuentaAhorros.setComisionMensual(comisionMensual);

        cuentaAhorros.retirar(cantidadRetiro);

        cuentaAhorros.extractoMensual();

        float interesEsperado = (cuentaAhorros.getTasaAnual() / 12) * cuentaAhorros.getSaldo();
        assertTrue(cuentaAhorros.isActive());
        assertEquals(
            cuentaAhorros.getSaldo() - cuentaAhorros.getComisionMensual() + interesEsperado,
            cuentaAhorros.getSaldo(), 
            0.01
        );
    }

    @Test
    void testExtractoMensualRetiros() {

        int saldoInicial = random.nextInt(20000) + 10000;

        float tasaAnual = random.nextFloat() * 0.1f; // 0-10%

        float comisionMensual = random.nextFloat() * 100f;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.setTasaAnual(tasaAnual);
        cuentaAhorros.setComisionMensual(comisionMensual);


        int[] retiros = {
            random.nextInt(500) + 100,
            random.nextInt(2000) + 1000,
            random.nextInt(3000) + 2000,
            random.nextInt(500) + 100
        };

        for (int retiro : retiros) {
            cuentaAhorros.retirar(retiro);
        }

        cuentaAhorros.extractoMensual();

        float interesEsperado = (cuentaAhorros.getTasaAnual() / 12) * cuentaAhorros.getSaldo();
        assertFalse(cuentaAhorros.isActive());
        assertEquals(
            cuentaAhorros.getSaldo() - cuentaAhorros.getComisionMensual() + interesEsperado,
            cuentaAhorros.getSaldo(), 
            0.01
        );
    }

    @Test
    void testVisualizar() {

        int saldoInicial = random.nextInt(20000) + 10000;

        int cantidadIngreso = random.nextInt(2000) + 500;
        int cantidadRetiro = random.nextInt(1000) + 100;

        cuentaAhorros.setSaldo(saldoInicial);
        cuentaAhorros.ingresar(cantidadIngreso);
        cuentaAhorros.retirar(cantidadRetiro);

        cuentaAhorros.visualizar();

        assertEquals(saldoInicial + cantidadIngreso - cantidadRetiro, cuentaAhorros.getSaldo());
        assertEquals(1, cuentaAhorros.getNumeroAbonos());
        assertEquals(1, cuentaAhorros.getNumeroRetiros());
        assertTrue(cuentaAhorros.isActive());
    }
}
