package SistemaVendas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private String dataRegistro;
    private List<Venda> vendas = new ArrayList<>();


    public Venda(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade, String dataRegistro) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataRegistro = dataRegistro;
        this.valorTotal = produto.getPreco() * quantidade;
        this.vendas = new ArrayList<>();

    }

    public Venda() {

    }
    public void listarVendas() {
        System.out.println("Lista de Vendas\n");
        //Venda venda = new Venda();

        for (Venda vendaAtual : getVendas()) {

            System.out.println("Vendedor: " + getVendedor().getNome());
            System.out.println("Vendedor: " + vendaAtual.getVendedor().getNome());
            System.out.println("Cliente: " + vendaAtual.getCliente().getNome());
            System.out.println("Produto: " + vendaAtual.getProduto().getNome());
            System.out.println("Quantidade: " + vendaAtual.getQuantidade());
            System.out.println("Valor Total: " + vendaAtual.getValorTotal());
            System.out.println("Data de Registro: " + vendaAtual.getDataRegistro());
            System.out.println("---------------------------");
        }
    }

    public void realizarVenda(Cliente cliente, List<Vendedor> vendedores) {
        Scanner ler = new Scanner(System.in);
        Vendedor vendedor1 = new Vendedor();
        Produto produto1 = new Produto();

        System.out.println("Realização de Venda\n");

        int escolhaVendedor;
        while (true) {
            System.out.println("Lista de Vendedores:\n");

            for (int i = 0; i < vendedores.size(); i++) {
                System.out.println((i + 1) + ". " + vendedores.get(i).getNome());

            }

            System.out.print("\nDigite o número do vendedor desejado: ");
            escolhaVendedor = ler.nextInt();
            ler.nextLine(); // Limpa o buffer do scanner

            if (escolhaVendedor < 1 || escolhaVendedor > vendedores.size()) {
                System.out.println("Opção inválida.\nTente novamente");

            } else {
                break;
            }
        }

        Vendedor vendedorCompraAtual = vendedores.get(escolhaVendedor - 1);

//
//        System.out.print("Digite o e-mail do vendedor: ");
//        String emailVendedor = ler.nextLine();
//        Vendedor vendedor = buscarVendedorPorEmail(emailVendedor);
//        if (vendedor == null) {
//            System.out.println("Vendedor não encontrado.\n");
//            return;
//        }

        while (true) {
            System.out.println("Produtos disponíveis:\n");
            produto1.listarProdutosDisponiveis();

            System.out.print("\nDigite o código do produto: ");
            String codigoProduto = ler.nextLine();
            Produto produtoEscolhido = produto1.buscarProdutoPorCodigo(codigoProduto);
            if (produtoEscolhido == null) {
                System.out.println("Produto não encontrado.\nTente novamente.");
                continue;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = ler.nextInt();
            ler.nextLine(); // Limpa o buffer do scanner

            if (quantidade > produtoEscolhido.getQuantidadeDisponivel()) {
                System.out.println("Quantidade insuficiente do produto.\nTente novamente.");
                continue;
            }

            produtoEscolhido.setQuantidadeDisponivel(produtoEscolhido.getQuantidadeDisponivel() - quantidade);

            Venda venda = new Venda(vendedorCompraAtual, cliente, produtoEscolhido, quantidade, obterDiaAtual());
            vendas.add(venda);

            System.out.println("\nVenda realizada com sucesso!\n");
            break;
        }
    }
    public String obterDiaAtual() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);

    }


    // getters e setters


    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
}
