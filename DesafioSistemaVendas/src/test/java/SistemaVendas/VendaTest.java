package SistemaVendas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {
    private Venda venda;
    private Vendedor vendedor;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        vendedor = new Vendedor("Myllena", "myllena@email.com","11111111111","senha123");
        cliente = new Cliente("My", "123456789","00000000000","senha123");
        venda = new Venda(vendedor, cliente, null, "19/06/2023");
    }


    @Test
    public void deveFornecerODiaAtual() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dataEsperada = dateFormat.format(date);

        String dataAtual = venda.obterDiaAtual();

        assertEquals(dataEsperada, dataAtual);
    }
    @Test
    public void deveRetornarOCliente(){
        Cliente clienteAtual = venda.getCliente();
        assertEquals(cliente,clienteAtual);
    }
    @Test
    public void deveRetornarOVendedor(){
        Vendedor vendedorAtual = venda.getVendedor();
        assertEquals(vendedor,vendedorAtual);
    }
    @Test
    public void deveRetornarADataDeReistro(){
        assertEquals("19/06/2023",venda.getDataRegistro());
    }

}