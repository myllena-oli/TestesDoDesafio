package SistemaVendas;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private String nome;
    private String email;
    private String cpf;
    private List<Vendedor> vendedores = new ArrayList<>();

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
        System.out.println("Lista de Vendedores\n");

        for (Vendedor vendedor : getVendedores()) {
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("Email: " + vendedor.getEmail());
            System.out.println("CPF: " + vendedor.getCpf());
            System.out.println("---------------------------");
        }
    }


    private Vendedor buscarVendedorPorEmail(String email) {

        Vendedor vendedor = new Vendedor();

        for (Vendedor buscarVendedor : vendedor.getVendedores()) {
            System.out.println("Conferir se nesse for eu preciso colocar o vendedor. aqui em cima.");

            if (buscarVendedor.getEmail().equals(email)) {
                return buscarVendedor;
            }
        }
        return null;
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

}
