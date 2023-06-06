package SistemaVendas;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    static int codigoGerado = 0;
    private int codigo;
    private String nome;
    private double preco;
    private int quantidadeDisponivel;
    private List<Produto> produtos = new ArrayList<>();


    public Produto(String nome, double preco, int quantidadeDisponivel) {
        codigoGerado++;
        this.codigo = codigoGerado;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    public Produto(){

    }
    public void adicionarProduto(Produto produto) {
        getProdutos().add(produto);
    }

    public void listarProdutosDisponiveis() {
        System.out.println("Lista de Produtos Disponíveis\n");

        for (Produto produto : getProdutos()) {
            System.out.println("Código: " + produto.getCodigo());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Quantidade Disponível: " + produto.getQuantidadeDisponivel());
            System.out.println("---------------------------");
        }
    }
    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto buscarProduto : getProdutos()) {
            if (buscarProduto.getCodigo()==codigo) {
                return buscarProduto;
            }
        }
        return null;
    }


    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }

}
