package SistemaVendas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendedorTest {
    private Vendedor vendedor;
    private List<Vendedor> vendedores;
    private Map<String, Vendedor> cpfVendedor;
    private Map<String, Vendedor> emailVendedor;

    @BeforeEach
    public void setUp() {
        vendedores = new ArrayList<>();
        cpfVendedor = new HashMap<>();
        emailVendedor = new HashMap<>();
        vendedor = new Vendedor("Myllena", "myllena@email.com", "123456789","senha123");
    }

    @Test
    public void deveAdicionarUmVendedor() {
        Vendedor novoVendedor = new Vendedor("Ana", "ana@email.com", "987654321","senha123");
        vendedor.adicionarVendedor(novoVendedor);
        List<Vendedor> vendedoresEsperados = new ArrayList<>();
        vendedoresEsperados.add(novoVendedor);
        assertEquals(vendedoresEsperados, vendedor.getVendedores());
    }

    @Test
    public void deveRetornarONome(){
        assertEquals("Myllena",vendedor.getNome());
    }
    @Test
    public void deveRetornarOEmail(){
        assertEquals("myllena@email.com",vendedor.getEmail());
    }
    @Test
    public void deveRetornarOCpf(){
        assertEquals("123456789",vendedor.getCpf());
    }

    @Test
    public void deveConferirSeAListaDeVendedoresEstaCorreta() {
        List<Vendedor> vendedoresEsperados = vendedores;
        List<Vendedor> vendedorAtual = vendedor.getVendedores();

        assertEquals(vendedoresEsperados, vendedorAtual);
    }

    @Test
    public void testaSeOMapCpfVendedorEstaCorreto() {
        Map<String, Vendedor> resultadoEsperadoCpfVendedor = cpfVendedor;
        Map<String, Vendedor> atualCpfVendedor = vendedor.getCpfVendedor();

        assertEquals(resultadoEsperadoCpfVendedor, atualCpfVendedor);
    }

    @Test
    public void testaSeOMapEmailVendedorEstaCorreto() {
        Map<String, Vendedor> resultadoEsperadoEmailVendedor = emailVendedor;
        Map<String, Vendedor> atualEmailVendedor = vendedor.getEmailVendedor();

        assertEquals(resultadoEsperadoEmailVendedor, atualEmailVendedor);
    }

}