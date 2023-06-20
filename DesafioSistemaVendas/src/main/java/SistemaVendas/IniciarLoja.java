package SistemaVendas;

import java.util.*;

public class IniciarLoja {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Vendedor vendedor = new Vendedor();
        Cliente cliente = new Cliente();
        Produto produto = new Produto();
        Venda venda = new Venda(vendedor, cliente, produto, 0, "");

        //Cliente pré cadastrado
        Cliente cliente1 = new Cliente("Myllena", "myllena@example.com", "3", "123");
        cliente.getClientes().add(cliente1);
        cliente.getCpfCliente().put(cliente1.getCpf(), cliente1);
        cliente.getEmailCliente().put(cliente1.getEmail(), cliente1);

        // Adicionar vendedores pré-cadastrados
        Vendedor vendedor1 = new Vendedor("João", "joao@example.com", "11111111111");
        Vendedor vendedor2 = new Vendedor("Maria", "maria@example.com", "22222222222");

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

        while (true) {
            System.out.println("----------------------------------");
            System.out.println("          Menu de Acesso:\n");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Vendedor");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar Vendedores");
            System.out.println("5. Comprar produtos");
            System.out.println("6. Pesquisar compras de um cliente");
            System.out.println("7. Pesquisar compras de um vendedor");
            System.out.println("8. Listar Vendas");
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
                    cliente.cadastrarCliente();
                    break;
                case 2:
                    vendedor.cadastrarVendedor();
                    break;

                case 3:
                    cliente.listarClientes();
                    break;

                case 4:
                    vendedor.listarVendedores();
                    break;
                case 5:
                    Cliente clienteLogado;
                    try {
                        clienteLogado = cliente.login();
                    } catch (LimiteDeTentativasException erroCadastro) {
                        System.out.println("Exceção capturada: " + erroCadastro.getMessage());
                        break;
                    }
                    venda.realizarVenda(clienteLogado);
                    break;

                case 6:
                    try {
                        venda.pesquisarCompraCliente();
                    } catch (NullPointerException Excecao) {
                        System.out.println(Excecao.getMessage());
                    }
                    break;
                case 7:
                    try {
                        venda.pesquisarCompraVendedor();
                    } catch (NullPointerException Excecao) {
                        System.out.println(Excecao.getMessage());
                    }
                    break;
                case 8:
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
