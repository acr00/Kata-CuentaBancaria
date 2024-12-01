package com.example.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaCorrienteTest {
    private CuentaCorriente cuentaCorriente;
    private Random random;

    @BeforeEach
    void inicio() {
        cuentaCorriente = new CuentaCorriente();
        random = new Random();
    }

    @Test
    void testConstructorCompleto() {
        int saldoInicial = random.nextInt(20000) + 5000;
        int numeroAbonos = random.nextInt(50);
        int numeroRetiros = random.nextInt(50);
        float comisionMensual = random.nextFloat() * 100;
        float tasaAnual = random.nextFloat() * 0.1f;
        int sobregiro = random.nextInt(5000);

        CuentaCorriente cuenta = new CuentaCorriente(
            saldoInicial, 
            numeroAbonos, 
            numeroRetiros, 
            comisionMensual, 
            tasaAnual, 
            sobregiro
        );

        assertEquals(saldoInicial, cuenta.getSaldo());
        assertEquals(numeroAbonos, cuenta.getNumeroAbonos());
        assertEquals(numeroRetiros, cuenta.getNumeroRetiros());
        assertEquals(comisionMensual, cuenta.getComisionMensual());
        assertEquals(tasaAnual, cuenta.getTasaAnual());
        assertEquals(sobregiro, cuenta.getSobregiro());
    }

    @Test
    void testIngresarSinSobregiro() {
        int saldoInicial = random.nextInt(20000) + 5000;
        int cantidadIngreso = random.nextInt(10000) + 1000;
        
        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.ingresar(cantidadIngreso);

        assertEquals(saldoInicial + cantidadIngreso, cuentaCorriente.getSaldo());
    }

    @Test
    void testIngresarConSobregiroMayor() {
        int saldoInicial = random.nextInt(20000) + 5000;
        int sobregiroInicial = random.nextInt(5000) + 1000;
        int cantidadIngreso = sobregiroInicial + random.nextInt(5000) + 1000;

        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.setSobregiro(sobregiroInicial);
        cuentaCorriente.ingresar(cantidadIngreso);

        assertEquals(saldoInicial + (cantidadIngreso - sobregiroInicial), cuentaCorriente.getSaldo());
        assertEquals(0, cuentaCorriente.getSobregiro());
    }

    @Test
    void testIngresarConSobregiroMenor() {
        int saldoInicial = random.nextInt(20000) + 5000;
        int sobregiroInicial = random.nextInt(5000) + 1000;
        int cantidadIngreso = random.nextInt(sobregiroInicial);

        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.setSobregiro(sobregiroInicial);
        cuentaCorriente.ingresar(cantidadIngreso);

        assertEquals(sobregiroInicial - cantidadIngreso, cuentaCorriente.getSobregiro());
        assertEquals(saldoInicial, cuentaCorriente.getSaldo());
    }

    @Test
    void testRetirarSinSobregiro() {
        int saldoInicial = random.nextInt(20000) + 5000;
        int cantidadRetiro = random.nextInt(saldoInicial);

        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.retirar(cantidadRetiro);

        assertEquals(saldoInicial - cantidadRetiro, cuentaCorriente.getSaldo());
        assertEquals(0, cuentaCorriente.getSobregiro());
    }

    @Test
    void testRetirarConSobregiro() {
        int saldoInicial = random.nextInt(10000) + 5000;
        int cantidadRetiro = saldoInicial + random.nextInt(5000) + 1000;

        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.retirar(cantidadRetiro);

        assertEquals(0, cuentaCorriente.getSaldo());
        assertEquals(cantidadRetiro - saldoInicial, cuentaCorriente.getSobregiro());
    }

    @Test
    void testExtractoMensual() {
        int saldoInicial = random.nextInt(20000) + 5000;
        float tasaAnual = random.nextFloat() * 0.1f;
        float comisionMensual = random.nextFloat() * 100;

        cuentaCorriente.setSaldo(saldoInicial);
        cuentaCorriente.setTasaAnual(tasaAnual);
        cuentaCorriente.setComisionMensual(comisionMensual);

        cuentaCorriente.extractoMensual();

        // Verificar que se llama al método de la superclase sin errores
        assertNotNull(cuentaCorriente);
    }

    @Test
    void testVisualizarSinSobregiro() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cuentaCorriente.setSaldo(5000);
        cuentaCorriente.setSobregiro(0);
        cuentaCorriente.visualizar();

        assertTrue(outContent.toString().contains("No hay sobregiro"));
        System.setOut(System.out);
    }

    @Test
    void testVisualizarConSobregiro() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cuentaCorriente.setSaldo(5000);
        cuentaCorriente.setSobregiro(1000);
        cuentaCorriente.setNumeroAbonos(3);
        cuentaCorriente.setNumeroRetiros(1);
        cuentaCorriente.visualizar();

        assertTrue(outContent.toString().contains("Valor del sobregiro: 1000.0"));
        System.setOut(System.out);
    }

    @Test
    void testSetterAndGetterSobregiro() {
        float sobregiroTest = random.nextFloat() * 1000;
        cuentaCorriente.setSobregiro(sobregiroTest);
        assertEquals(sobregiroTest, cuentaCorriente.getSobregiro());
    }
}