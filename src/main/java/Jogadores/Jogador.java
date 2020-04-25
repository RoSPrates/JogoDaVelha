package Jogadores;

public class Jogador {
    private String nome;
    private int numero;
    private int vitorias;
    private static int empates;

    public Jogador(int numero, String nome) {
        this.nome = nome;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public static int getEmpates() {
        return empates;
    }

    public void acrescentarVitoria(){
        this.vitorias++;
    }

    public static void acrescentarEmpates() {
        Jogador.empates++;
    }

    public static void zeraEmpates() {
        Jogador.empates = 0;
    }
}
