package SistemaVendas;

import java.util.*;

public class Vendedor {
    private String nome;
    private String email;
    private String cpf;
    private List<Vendedor> vendedores = new ArrayList<>();
    private Map<String, Vendedor> cpfVendedor = new HashMap<>();
    private Map<String, Vendedor> emailVendedor = new HashMap<>();


    public Vendedor(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Vendedor() {
        this.vendedores = new ArrayList<>();
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void listarVendedores() {
        System.out.println("\nLista de Vendedores");

        for (Vendedor vendedor : getVendedores()) {
            System.out.println("---------------------------");
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("Email: " + vendedor.getEmail());
            System.out.println("CPF: " + vendedor.getCpf());
        }
        System.out.println("----------------------------------");
    }

    public void cadastrarVendedor() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Cadastro de Vendedores\n");

        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (emailVendedor.containsKey(email)) {
                System.out.println("Email já cadastrado.\n");
                return;
            }

            if (!email.contains("@")) {
                System.out.println("Para o email ser válido, ele deve conter '@'!");
            } else {
                break;
            }
        }

        System.out.print("Digite o CPF: ");
        String cpf = ler.nextLine();

        if (cpfVendedor.containsKey(cpf)) {
            System.out.println("CPF já cadastrado.\n");
            return;
        }

        Vendedor vendedor = new Vendedor(nome, email, cpf);
        vendedores.add(vendedor);
        cpfVendedor.put(cpf, vendedor);
        emailVendedor.put(email, vendedor);

        System.out.println("\nVendedor cadastrado com sucesso!\n");
    }


    // getters e setters

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public Map<String, Vendedor> getCpfVendedor() {
        return cpfVendedor;
    }

    public Map<String, Vendedor> getEmailVendedor() {
        return emailVendedor;
    }
}
