package SistemaVendas;

import java.util.*;

public class Cliente {
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private Map<String, Cliente> cpfCliente = new HashMap<>();
    private Map<String, Cliente> emailCliente = new HashMap<>();
    private List<Cliente> clientes = new ArrayList<>();


    public Cliente(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.cpfCliente = new HashMap<>();
        this.clientes = new ArrayList<>();


    }

    public Cliente() {
    }


    public void listarClientes() {
        System.out.println("Lista de Clientes\n");

        for (Cliente cliente : getClientes()) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("---------------------------");
        }
    }

    public Cliente cadastrarCliente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("----------Cadastro de Cliente----------\n");

        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (emailCliente.containsKey(email)) {
                System.out.println("Email já cadastrado.\n");
                return emailCliente.get(email);
            }

            if (!email.contains("@")) {
                System.out.println("Para o email ser válido, ele deve conter '@'!");
            } else {
                break;
            }
        }

        System.out.println("Digite o CPF: ");
        String cpf = ler.nextLine();

        if (cpfCliente.containsKey(cpf)) {
            System.out.println("CPF já cadastrado.\n");
            return cpfCliente.get(cpf);
        }

        System.out.println("Digite a senha: ");
        String senha = ler.nextLine();


        Cliente cliente = new Cliente(nome, email, cpf, senha);
        clientes.add(cliente);
        cpfCliente.put(cpf, cliente);

        System.out.println("\nCliente cadastrado com sucesso!\n");

        return cliente;
    }


    public Cliente login() throws LimiteDeTentativasException {
        Scanner ler = new Scanner(System.in);
        Cliente cliente = new Cliente();
        int tentativa = 0;

        System.out.println("É necessário logar para poder realizar compras!");

        System.out.print("Digite o CPF do cliente: ");
        String cpfParaLogin = ler.nextLine();

        Map<String, Cliente> listaClientes = getCpfCliente();

        if (!(listaClientes.containsKey(cpfParaLogin))) {
            System.out.println("CPF não cadastrado.\n");
            System.out.println("Redirecionando para o cadastro de clientes... ");
            return cliente.cadastrarCliente();

        } else {
            cliente = listaClientes.get(cpfParaLogin);
        }

        while (true) {
            System.out.print("Digite a senha: ");
            String senha = ler.nextLine();
            tentativa++;

            if (!cliente.getSenha().equals(senha)) {
                System.out.println("Senha incorreta.\n");
                if (tentativa > 3) {
                    throw new LimiteDeTentativasException("Limite de tentativas atingido!");
                }
            } else {
                System.out.println("Login efetuado! Bem vindo, cliente " + cliente.getNome());
                break;
            }
        }

        return cliente;
    }


    // getters e setters


    public Map<String, Cliente> getEmailCliente() {
        return emailCliente;
    }

    public Map<String, Cliente> getCpfCliente() {
        return cpfCliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
