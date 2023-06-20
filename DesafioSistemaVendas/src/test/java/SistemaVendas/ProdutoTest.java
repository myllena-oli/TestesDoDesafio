package SistemaVendas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    private Produto produto;

    @BeforeEach
    public void setup(){
        produto = new Produto("NomeProduto",10.0,5);
    }

    @Test
    public void deveRetornarOCodigo(){
        assertEquals(1,produto.getCodigo());
    }

    @Test
    public void deveRetornarONome(){
        assertEquals("NomeProduto",produto.getNome());
    }

    @Test
    public void deveRetornarOPreco(){
        assertEquals(10.0,produto.getPreco());
    }

    @Test
    public void deveRetornarAQuantidade(){
        assertEquals(5,produto.getQuantidadeDisponivel());
    }

    @Test
    public void deveSetarAQuantidadePara10(){
        produto.setQuantidadeDisponivel(10);
        assertEquals(10,produto.getQuantidadeDisponivel());
    }

    @Test
    public void deveConfirmarQueAListaEstaVazia() {
        List<Produto> produtos = produto.getProdutos();
        assertTrue(produtos.isEmpty());
    }

    @Test
    public void testeDaBuscarProdutoPorCodigo() {
        produto.adicionarProduto(new Produto("Produto2", 15.0, 2));
        produto.adicionarProduto(new Produto("Produto3", 20.0, 4));

        Produto produtoEncontrado = produto.buscarProdutoPorCodigo(3);
        assertEquals("Produto3", produtoEncontrado.getNome());

        Produto produtoNaoEncontrado = produto.buscarProdutoPorCodigo(4);
        assertNull(produtoNaoEncontrado);
    }
}