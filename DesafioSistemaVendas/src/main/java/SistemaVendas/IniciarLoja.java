package SistemaVendas;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.*;

public class IniciarLoja {
    private Vendedor vendedor;
    private Cliente cliente;
    private Produto produto;

    public IniciarLoja(Vendedor vendedor, Cliente cliente, Produto produto) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.produto = produto;
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Vendedor vendedor = new Vendedor();
        Cliente cliente = new Cliente();
        Produto produto = new Produto();
        Venda venda = new Venda(vendedor, cliente, produto, 0, "");

        //Cliente pré cadastrado
        Cliente cliente1 = new Cliente("Myllena", "myllena@example.com", "3", BCrypt.hashpw("senha123", BCrypt.gensalt()));
        cliente.getClientes().add(cliente1);
        cliente.getCpfCliente().put(cliente1.getCpf(), cliente1);
        cliente.getEmailCliente().put(cliente1.getEmail(), cliente1);

        // Adicionar vendedores pré-cadastrados
        Vendedor vendedor1 = new Vendedor("João", "joao@example.com", "11111111111", BCrypt.hashpw("senha123", BCrypt.gensalt()));
        Vendedor vendedor2 = new Vendedor("Maria", "maria@example.com", "22222222222", BCrypt.hashpw("senha321", BCrypt.gensalt()));

        vendedor.adicionarVendedor(vendedor1);
        vendedor.getCpfVendedor().put(vendedor1.getCpf(), vendedor1);
        vendedor.getEmailVendedor().put(vendedor1.getEmail(), vendedor1);

        vendedor.adicionarVendedor(vendedor2);
        vendedor.getCpfVendedor().put(vendedor2.getCpf(), vendedor2);
        vendedor.getEmailVendedor().put(vendedor2.getEmail(), vendedor2);


        // Adicionar produtos pré-cadastrados
        Produto produto1 = new Produto("Fone", 40.0, 5);
        Produto produto2 = new Produto("Mouse", 30.0, 10);
        Produto produto3 = new Produto("Teclado", 70.0, 15);

        produto.adicionarProduto(produto1);
        produto.adicionarProduto(produto2);
        produto.adicionarProduto(produto3);

        IniciarLoja menu = new IniciarLoja(vendedor, cliente, produto);

        while (true) {

            System.out.println("----------------------------------");
            System.out.println("          Menu de Acesso:\n");
            System.out.println("1. Login Cliente");
            System.out.println("2. Login Vendedor");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Cadastrar Vendedor");
            System.out.println("0. Sair\n");
            int opcao;
            while (true) {
                try {
                    System.out.println("Escolha uma opção: ");
                    opcao = ler.nextInt();
                } catch (InputMismatchException DigitoIncorreto) {
                    System.out.println("InputMismatchException: Essa opção não é válida.");
                    ler.nextLine();
                    continue;
                }
                ler.nextLine(); // Limpa o buffer do scanner
                break;
            }
            switch (opcao) {
                case 1:
                    Cliente clienteLogado;
                    try {
                        clienteLogado = cliente.login();
                    } catch (LimiteDeTentativasException erroCadastro) {
                        System.out.println("Exceção capturada: " + erroCadastro.getMessage());
                        break;
                    }
                    venda.realizarVenda(clienteLogado);
                    break;

                case 2:
                    Vendedor vendedorLogado;
                    try {
                        vendedorLogado = vendedor.login();
                    } catch (LimiteDeTentativasException erroCadastro) {
                        System.out.println("Exceção capturada: " + erroCadastro.getMessage());
                        break;
                    }

                    menu.menuVendedor(vendedorLogado, venda);
                    break;
                case 3:
                    cliente.cadastrarCliente();
                    break;
                case 4:
                    vendedor.cadastrarVendedor();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }

    public void menuVendedor(Vendedor vendedor, Venda venda) {
        Scanner ler = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------");
            System.out.println("          Menu Vendedor:\n");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Listar Vendedores");
            System.out.println("3. Pesquisar compras de um cliente");
            System.out.println("4. Pesquisar compras de um vendedor");
            System.out.println("5. Listar Vendas");
            System.out.println("0. Sair\n");
            int opcao;
            while (true) {
                try {
                    System.out.println("Escolha uma opção: ");
                    opcao = ler.nextInt();
                } catch (InputMismatchException DigitoIncorreto) {
                    System.out.println("InputMismatchException: Essa opção não é válida.");
                    ler.nextLine();
                    continue;
                }
                ler.nextLine(); // Limpa o buffer do scanner
                break;
            }


            switch (opcao) {

                case 1:
                    cliente.listarClientes();
                    break;
                case 2:
                    vendedor.listarVendedores();
                    break;
                case 3:
                    try {
                        venda.pesquisarCompraCliente();
                    } catch (NullPointerException Excecao) {
                        System.out.println(Excecao.getMessage());
                    }
                    break;
                case 4:
                    try {
                        venda.pesquisarCompraVendedor();
                    } catch (NullPointerException Excecao) {
                        System.out.println(Excecao.getMessage());
                    }
                    break;
                case 5:
                    venda.listarVendas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }
}
