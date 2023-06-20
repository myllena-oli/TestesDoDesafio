package SistemaVendas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente("Myllena", "myllena@email.com", "123456789","senha123");
    }

    @Test
    public void deveRetornarONome(){
        assertEquals("Myllena",cliente.getNome());
    }
    @Test
    public void deveRetornarOEmail(){
        assertEquals("myllena@email.com",cliente.getEmail());
    }
    @Test
    public void deveRetornarOCpf(){
        assertEquals("123456789",cliente.getCpf());
    }
    @Test
    public void deveRetornarASenha(){
        assertEquals("senha123",cliente.getSenha());
    }




}