package Jogo.classes;

import Jogadores.Jogador;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Interacao {
    private static Scanner scan = new Scanner(System.in);

    public static void mostrarAlerta(String msg) {
        System.out.println(msg);
    }

    public static void mostrarMensagem(String msg) {
        System.out.print(msg);
    }

    public static int lerNumero(String msg) {
        boolean b;
        int resp = -3;
        do {
            try {
                mostrarMensagem(msg);
                resp = Integer.parseInt(scan.nextLine());
                b = false;
            } catch (InputMismatchException ex) {
                b = true;
                mostrarAlerta("OPÇÃO INVALIDA\nDIGITE APENAS NUMEROS INTEIROS");
            } catch (NumberFormatException ex) {
                b = true;
                mostrarAlerta("OPÇÃO INVALIDA\nDIGITE APENAS NUMEROS INTEIROS");
            }
        } while (b);
        return resp;
    }

    public static int escolherSimOuNao(String msg) {
        boolean b;
        int resp = 0;
        do {
            try {
                mostrarMensagem(msg);
                resp = Integer.parseInt(scan.nextLine());
                b = false;
            } catch (NumberFormatException ex) {
                b = true;
            } catch (InputMismatchException ex) {
                b = true;
            }
            if (resp > 2 || resp < 1) {
                mostrarAlerta("OPÇÃO INVALIDA");
                b = true;
            }
        } while (b);
        return resp;
    }

    public static String lerNome(String msg) {
        mostrarMensagem(msg);
        return scan.nextLine();
    }

    public static void mostrarGanhador(int pontos, Jogador j1, Jogador j2, int[][] tabuleiro) {
        if (pontos == j1.getNumero() * tabuleiro.length) {
            mostrarAlerta("Parabens " + j1.getNome() + "\nVoce Ganhou");
            j1.acrescentarVitoria();
        } else if (pontos == j2.getNumero() * tabuleiro.length) {
            mostrarAlerta("Parabens " + j2.getNome() + "\nVoce Ganhou");
            j2.acrescentarVitoria();
        } else {
            mostrarAlerta("Deu Velha");
            Jogador.acrescentarEmpates();
        }
        mostrarPlacar(j1, j2);
    }

    public static void mostrarTabuleiro(int[][] tabuleiro) {
        char a;
        int b = 1;
        for (int[] ints : tabuleiro) {
            for (int j = 0; j < tabuleiro.length; j++) {
                a = ints[j] == 1 ? 'X' : ints[j] == -1 ? 'O' : ' ';
                if (j != tabuleiro.length - 1) {
                    System.out.print(a + " | ");
                } else {
                    System.out.print(a);
                }
            }
            for (int j = 0; j < tabuleiro.length; j++) {
                if (j == 0) {
                    System.out.printf("     | %03d | ", b);
                } else {
                    System.out.printf("%03d | ", b);
                }
                b++;
            }
            System.out.println();
        }
    }

    public static void mostrarPlacar(Jogador j1, Jogador j2) {
        mostrarMensagem(j1.getNome() + " tem: " + j1.getVitorias() + " Vitorias\n");
        mostrarMensagem(j2.getNome() + " tem: " + j2.getVitorias() + " Vitorias\n");
        mostrarMensagem("Deu Velha: " + Jogador.getEmpates() + " Vezes\n");
    }


}
