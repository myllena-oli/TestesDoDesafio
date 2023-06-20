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
    private List<ComprasProdutos<Produto, Integer>> produtosComprados = new ArrayList<>();


    public Venda(Vendedor vendedor, Cliente cliente, Produto produto, int quantidade, String dataRegistro) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataRegistro = dataRegistro;
        this.valorTotal = produto.getPreco() * quantidade;
        this.vendas = new ArrayList<>();

    }

    public Venda(Vendedor vendedor, Cliente cliente, List<ComprasProdutos<Produto, Integer>> produtosComprados, String dataRegistro) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produtosComprados = produtosComprados;
        this.dataRegistro = dataRegistro;
        this.vendas = new ArrayList<>();

    }

    public void listarVendas() {
        System.out.println("----------------------------------");
        System.out.println("Lista de Vendas:\n");

        int existeVenda=0;
        for (Venda vendaAtual : getVendas()) {
            existeVenda=1;
            double valorTotal = 0;
            System.out.println("Vendedor: " + vendaAtual.getVendedor().getNome());
            System.out.println("Cliente: " + vendaAtual.getCliente().getNome());
            System.out.printf("%17s %10s %12s", "Código do produto", "Nome", "Quantidade\n");

            for (int i = 0; i < vendaAtual.produtosComprados.size(); i++) {
                int quantidade = vendaAtual.produtosComprados.get(i).getValor();

                double valorItem = vendaAtual.produtosComprados.get(i).getChave().getPreco();
                double valorItemQuantidade = quantidade * valorItem;
                valorTotal = valorItemQuantidade + valorTotal;

                System.out.printf("%9s %18s %6d\n",
                        vendaAtual.produtosComprados.get(i).getChave().getCodigo(),
                        vendaAtual.produtosComprados.get(i).getChave().getNome(),
                        quantidade);

            }
            System.out.printf("Valor Total: %.2f\n" , valorTotal);

            System.out.println("Data de Registro: " + vendaAtual.getDataRegistro());
            System.out.println("----------------------------------");
        }
        if (existeVenda==0){
            System.out.println("Não houveram vendas.");
        }
    }

    public void realizarVenda(Cliente cliente) {
        Scanner ler = new Scanner(System.in);
        Random random = new Random();
        Vendedor vendedorCompraAtual;
        List<ComprasProdutos<Produto, Integer>> produtosComprados = new ArrayList<>();
        ComprasProdutos<Produto, Integer> comprasProdutos;

        System.out.println("--------------------------- Realização de Venda ---------------------------\n");

        System.out.println("Lista de Vendedores:\n");
        vendedor.listarVendedores();

        vendedorCompraAtual = vendedor.getVendedores().get(random.nextInt(vendedor.getVendedores().size()));
        System.out.println("O(a) funcionario(a) " + vendedorCompraAtual.getNome() + " está disponível e irá atender você! ");


        while (true) {
            produto.listarProdutosDisponiveis();
            int codigoProduto;
            try {
                System.out.print("\nDigite o código do produto ou digite 0 para desistir da compra: ");
                codigoProduto = ler.nextInt();
                if(codigoProduto==0){
                    return;
                }
            } catch (InputMismatchException excecao) {
                System.out.println("InputMismatchException: O código deve ser um número inteiro. ");
                ler.nextLine(); // Limpa o buffer do scanner
                continue;
            }
            Produto produtoEscolhido = produto.buscarProdutoPorCodigo(codigoProduto);
            if (produtoEscolhido == null) {
                System.out.println("Produto não encontrado.\nTente novamente.");
                continue;
            }
            int quantidade;
            while (true) {
                try {
                    System.out.print("Digite a quantidade desejada: ");
                    quantidade = ler.nextInt();
                } catch (InputMismatchException excecao) {
                    System.out.println("InputMismatchException: A quantidade deve ser um número inteiro. ");
                    ler.nextLine(); // Limpa o buffer do scanner
                    continue;
                }
                break;
            }

            if (quantidade > produtoEscolhido.getQuantidadeDisponivel()) {
                System.out.println("Quantidade insuficiente do produto.\nTente escolher outro produto ou quantidade.");
                continue;
            }

            produtoEscolhido.setQuantidadeDisponivel(produtoEscolhido.getQuantidadeDisponivel() - quantidade);

            comprasProdutos = new ComprasProdutos<>(produtoEscolhido, quantidade);

            produtosComprados.add(comprasProdutos);

            int escolha;
            while (true) {
                try{
                System.out.println("Digite 1 para finalizar a compra ou 2 para comprar mais itens");
                escolha = ler.nextInt();
                }catch (InputMismatchException excecao) {
                    System.out.println("InputMismatchException: Você deve digitar uma opção válida. ");
                    ler.nextLine(); // Limpa o buffer do scanner
                    continue;
                }
                if (escolha != 1 && escolha != 2) {
                    System.out.println("Opção desconhecida...\n");
                } else {
                    break;
                }
            }
            if (escolha == 1) {
                break;
            }
        }
        Venda venda = new Venda(vendedorCompraAtual, cliente, produtosComprados, obterDiaAtual());
        vendas.add(venda);

        System.out.println("\nVenda realizada com sucesso!\n");
    }


    public void pesquisarCompraCliente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o cpf para realizar a busca: ");
        String cpf = ler.nextLine();

        String existeCompra = "";
        for (Venda buscar : vendas) {
            double valorTotal = 0;

            if (buscar.getCliente().getCpf().equals(cpf)) {
                existeCompra = cpf;
                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.printf("%17s %10s %12s", "Código do produto", "Nome", "Quantidade\n");


                for (int i = 0; i < buscar.produtosComprados.size(); i++) {
                    int quantidade = buscar.produtosComprados.get(i).getValor();
                    double valorItem = buscar.produtosComprados.get(i).getChave().getPreco();
                    double valorItemQuantidade = quantidade * valorItem;
                    valorTotal = valorItemQuantidade + valorTotal;

                    System.out.printf("%9s %18s %6d\n",
                            buscar.produtosComprados.get(i).getChave().getCodigo(),
                            buscar.produtosComprados.get(i).getChave().getNome(),
                            quantidade);
                }
                System.out.printf("Valor Total: %.2f\n" , valorTotal);

                System.out.println("Data de Registro: " + buscar.getDataRegistro());
                System.out.println("---------------------------");
            }
        }
        if (existeCompra.isEmpty()) {
            throw new NullPointerException("NullPointerException: Não há vendas para este cliente.");
        }
    }

    public void pesquisarCompraVendedor() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o email para realizar a busca: ");
        String email = ler.nextLine();
        String existeCompra = "";


        for (Venda buscar : vendas) {
            double valorTotal = 0;
            if (buscar.getVendedor().getEmail().equals(email)) {
                existeCompra = email;

                System.out.println("Vendedor: " + buscar.getVendedor().getNome());
                System.out.println("Cliente: " + buscar.getCliente().getNome());
                System.out.printf("%17s %10s %12s", "Código do produto", "Nome", "Quantidade\n");

                for (int i = 0; i < buscar.produtosComprados.size(); i++) {
                    int quantidade = buscar.produtosComprados.get(i).getValor();
                    double valorItem = buscar.produtosComprados.get(i).getChave().getPreco();
                    double valorItemQuantidade = quantidade * valorItem;
                    valorTotal = valorItemQuantidade + valorTotal;
                    System.out.printf("%9s %18s %6d\n",
                            buscar.produtosComprados.get(i).getChave().getCodigo(),
                            buscar.produtosComprados.get(i).getChave().getNome(),
                            quantidade);
                }
                System.out.printf("Valor Total: %.2f\n" , valorTotal);
                System.out.println("Data de Registro: " + buscar.getDataRegistro());
                System.out.println("---------------------------");
            }
        }
        if (existeCompra.isEmpty()) {
            throw new NullPointerException("NullPointerException: Não há vendas para este vendedor.");
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

    public String getDataRegistro() {
        return dataRegistro;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

}
