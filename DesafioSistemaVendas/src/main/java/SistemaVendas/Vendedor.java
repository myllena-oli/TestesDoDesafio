package SistemaVendas;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.*;

public class Vendedor {
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    private List<Vendedor> vendedores = new ArrayList<>();
    private Map<String, Vendedor> cpfVendedor = new HashMap<>();
    private Map<String, Vendedor> emailVendedor = new HashMap<>();


    public Vendedor(String nome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;

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

    public Vendedor cadastrarVendedor() {
        Scanner ler = new Scanner(System.in);
        System.out.println("----------Cadastro de Vendedores----------\n");

        System.out.println("Digite o nome: ");
        String nome = ler.nextLine();
        String email;

        while (true) {
            System.out.println("Digite o e-mail: ");
            email = ler.nextLine();

            if (emailVendedor.containsKey(email)) {
                System.out.println("Email já cadastrado.\n");
                return emailVendedor.get(email);
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
            return cpfVendedor.get(cpf);
        }
        System.out.println("Digite a senha: ");
        String senha = ler.nextLine();
        String hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());

        Vendedor vendedor = new Vendedor(nome, email, cpf, hashSenha);
        vendedores.add(vendedor);
        cpfVendedor.put(cpf, vendedor);
        emailVendedor.put(email, vendedor);

        System.out.println("\nVendedor cadastrado com sucesso!\n");
        return vendedor;
    }

    public Vendedor login() throws LimiteDeTentativasException {
        Scanner ler = new Scanner(System.in);
        Vendedor vendedor = new Vendedor();
        int tentativa = 0;

        System.out.print("Digite o CPF do vendedor: ");
        String cpfParaLogin = ler.nextLine();

        Map<String, Vendedor> listaVendedores = getCpfVendedor();

        if (!(listaVendedores.containsKey(cpfParaLogin))) {
            System.out.println("CPF não cadastrado.\n");
            System.out.println("Redirecionando para o cadastro de vendedores... ");
            return vendedor.cadastrarVendedor();

        } else {
            vendedor = listaVendedores.get(cpfParaLogin);
        }

        while (true) {
            System.out.print("Digite a senha: ");
            String senha = ler.nextLine();
            tentativa++;

            if(!BCrypt.checkpw(senha,vendedor.getSenha())){
                System.out.println("Senha incorreta.\n");
                if (tentativa >= 3) {
                    throw new LimiteDeTentativasException("Limite de tentativas atingido!");
                }
            } else {
                System.out.println("Login efetuado! Bem vindo, vendedor " + vendedor.getNome());
                break;
            }
        }

        return vendedor;
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

    public String getSenha() {
        return senha;
    }
}
