package Jogo.classes;

import Jogadores.Jogador;

public abstract class JogoDaVelha {

    private static int tabuleiro[][];
    private static Jogador j1;
    private static Jogador j2;


    private static int contarPontos() {
        int s1, s2, d1 = 0, d2 = 0;
        for (int i = 0; i < tabuleiro.length; i++) {
            s1 = 0;
            s2 = 0;
            for (int j = 0; j < tabuleiro.length; j++) {
                s1 += tabuleiro[i][j];
                s2 += tabuleiro[j][i];
                if (i == j) d1 += tabuleiro[i][j];
                if (i + j == tabuleiro.length - 1) d2 += tabuleiro[i][j];
            }
            if (s1 == tabuleiro.length || s1 == -(tabuleiro.length)) return s1;
            if (s2 == tabuleiro.length || s2 == -(tabuleiro.length)) return s2;
        }
        if (d1 == tabuleiro.length || d1 == -(tabuleiro.length)) return d1;
        if (d2 == tabuleiro.length || d2 == -(tabuleiro.length)) return d2;
        return 0;
    }

    private static void fazerJogada(Jogador j) {
        int posicao;
        posicao = Interacao.lerNumero(j.getNome() + " Escolha onde jogar: ");
        while (posicao < 0 || posicao > Math.pow(tabuleiro.length, 2) || tabuleiro[(posicao - 1) / tabuleiro.length][(posicao - 1) % tabuleiro.length] != 0) {
            Interacao.mostrarAlerta("JOGADA INVALIDA!");
            posicao = Interacao.lerNumero(j.getNome() + " Escolha onde jogar: ");
        }
        tabuleiro[(posicao - 1) / tabuleiro.length][(posicao - 1) % tabuleiro.length] = j.getNumero();
    }

    private static void tratarJogada(int turno) {
        if (turno % 2 == 1)
            fazerJogada(j1);
        else
            fazerJogada(j2);
    }

    private static void criarTabuleiro() {
        int tamanho;
        tamanho = Interacao.lerNumero("Escolha o tamnaho do tabuleiro: ");
        while (tamanho < 3) {
            Interacao.mostrarAlerta("TAMANHO INVALIDO (TAMANHO MINIMO 3)!");
            tamanho = Interacao.lerNumero("Escolha o tamnaho do tabuleiro: ");
        }
        tabuleiro = new int[tamanho][tamanho];
    }

    private static void novoJogo() {
        String nomeJ1, nomeJ2;
        criarTabuleiro();
        nomeJ1 = Interacao.lerNome("Digite o nome do Jogador 1: ");
        nomeJ2 = Interacao.lerNome("Digite o nome do Jogador 2: ");
        j1 = new Jogador(1, nomeJ1);
        j2 = new Jogador(-1, nomeJ2);
        Jogador.zeraEmpates();
    }

    private static void criarJogo(int repetir) {
        if (repetir == 1) {
            repetir = Interacao.escolherSimOuNao("Mesmos jogadores? (1- sim 2- não): ");
            if (repetir == 1) {
                criarTabuleiro();
                return;
            }
        }
        novoJogo();
    }


    public static void jogar() {
        int pontos, repetir = 0, turno;
        do {
            criarJogo(repetir);
            turno = 1;
            pontos = 0;
            do {
                Interacao.mostrarTabuleiro(tabuleiro);
                tratarJogada(turno);
                if (turno > ((tabuleiro.length * 2) - 2)) pontos = contarPontos();
                turno++;
            } while (pontos == 0 && turno <= Math.pow(tabuleiro.length, 2));
            Interacao.mostrarTabuleiro(tabuleiro);
            Interacao.mostrarGanhador(pontos, j1, j2, tabuleiro);
            repetir = Interacao.escolherSimOuNao("Gostaria de jogar de novo (1 - sim / 2 - não): ");
        } while (repetir == 1);

    }
}
