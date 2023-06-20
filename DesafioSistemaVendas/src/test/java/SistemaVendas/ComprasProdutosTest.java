package SistemaVendas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComprasProdutosTest {

    @Test
    public void verificaSeGetChaveRetornaAChave() {
        ComprasProdutos<String, Integer> compra = new ComprasProdutos<>("Produto", 5);
        assertEquals("Produto", compra.getChave());
    }

    @Test
    public void verificaSeGetValorRetornaOValor() {
        ComprasProdutos<String, Integer> compra = new ComprasProdutos<>("Produto", 5);
        assertEquals(5, compra.getValor());
    }

}