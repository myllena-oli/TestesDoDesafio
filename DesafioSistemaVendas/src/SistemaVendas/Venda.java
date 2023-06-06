package SistemaVendas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Venda {
    private Vendedor vendedor;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private String dataRegistro;
    private List<Venda> vendas;


    public Venda(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade, String dataRegistro) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataRegistro = dataRegistro;
        this.valorTotal = produto.getPreco() * quantidade;
        this.vendas = new ArrayList<>();

    }

    public void listarVendas() {
        System.out.println("Lista de Vendas\n");

        for (Venda vendaAtual : getVendas()) {

            System.out.println("Vendedor responsável: " + getVendedor().getNome());
            System.out.println("Cliente: " + getCliente().getNome());

            System.out.println("Vendedor: " + vendaAtual.getVendedor().getNome());
            System.out.println("Cliente: " + vendaAtual.getCliente().getNome());
            System.out.println("Produto: " + vendaAtual.getProduto().getNome());
            System.out.println("Quantidade: " + vendaAtual.getQuantidade());
            System.out.println("Valor Total: " + vendaAtual.getValorTotal());
            System.out.println("Data de Registro: " + vendaAtual.getDataRegistro());
            System.out.println("---------------------------");
        }
    }

    public void realizarVenda(Cliente cliente) {
        Scanner ler = new Scanner(System.in);
        Random random = new Random();
        Vendedor vendedorCompraAtual;

        System.out.println("--------------------------- Realização de Venda ---------------------------\n");

        System.out.println("Lista de Vendedores:\n");
        vendedor.listarVendedores();

        vendedorCompraAtual = vendedor.getVendedores().get(random.nextInt(vendedor.getVendedores().size()));
        System.out.println("O(a) funcionario(a) "+vendedorCompraAtual.getNome()+" está disponível e irá atender você! ");


        while (true) {
            System.out.println("Produtos disponíveis:\n");
            produto.listarProdutosDisponiveis();

            System.out.print("\nDigite o código do produto: ");
            int codigoProduto = ler.nextInt();
            Produto produtoEscolhido = produto.buscarProdutoPorCodigo(codigoProduto);
            if (produtoEscolhido == null) {
                System.out.println("Produto não encontrado.\nTente novamente.");
                continue;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = ler.nextInt();
            ler.nextLine(); // Limpa o buffer do scanner

            if (quantidade > produtoEscolhido.getQuantidadeDisponivel()) {
                System.out.println("Quantidade insuficiente do produto.\nTente escolher outro produto ou quantidade.");
                continue;
            }

            produtoEscolhido.setQuantidadeDisponivel(produtoEscolhido.getQuantidadeDisponivel() - quantidade);

            //System.out.println("Digite 1 para finalizar a compra ou 2 para comprar mais itens");

            Venda venda = new Venda(vendedorCompraAtual, cliente, produtoEscolhido, quantidade, obterDiaAtual());
            vendas.add(venda);

            System.out.println("\nVenda realizada com sucesso!\n");
            break;
        }
    }

    public void pesquisarCompraCliente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o cpf para realizar a busca: ");
        String cpf = ler.nextLine();


        for (Venda buscar : vendas) {
            if (buscar.getCliente().getCpf().equals(cpf)) {
                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.println("Produto: " + buscar.getProduto().getNome());
                System.out.println("Quantidade: " + buscar.getQuantidade());
                System.out.println("Valor Total: " + buscar.getValorTotal());
                System.out.println("Data de Registro: " + buscar.getDataRegistro());
                System.out.println("---------------------------");
            }
        }
    }

    public void pesquisarCompraVendedor() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o email para realizar a busca: ");
        String email = ler.nextLine();


        for (Venda buscar : vendas) {
            if (buscar.getVendedor().getEmail().equals(email)) {
                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.println("Produto: " + buscar.getProduto().getNome());
                System.out.println("Quantidade: " + buscar.getQuantidade());
                System.out.println("Valor Total: " + buscar.getValorTotal());
                System.out.println("Data de Registro: " + buscar.getDataRegistro());
                System.out.println("---------------------------");
            }
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
