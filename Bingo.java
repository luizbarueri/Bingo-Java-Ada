import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

    public static void main(String[] args) {
        //String[] nomeJogadores = new String[] { "Ana", "Maria", "Tereza", "Mariza", "Luiz"};
        int linhas = 1;
        int colunas = 5;
        int[][] bingo = new int[linhas][colunas];
        int[] numerosBingo = new int[60];
        //===================================================================================
        //Cria tabela de numeros para sorteio de 1 a 60.
        for (int x = 0; x < 60; x++) {
            numerosBingo[x] = x + 1;
        }
        //===================================================================================
        //Chama a mensagem inicial
        int opcao = 0;
        int qtdJogador = 0;
        Scanner leitor = new Scanner(System.in);
        textoInicial();
        System.out.println("Opção: ");
        opcao = leitor.nextInt();
        System.out.println("Entre com a quantidade de Jogadores:");
        qtdJogador = leitor.nextInt();
        System.out.println("Quantidade de Jogadores: " + qtdJogador);
        if (opcao == 1 & qtdJogador > 0) linhas = qtdJogador; else opcao = 0;
        leitor.close();
        while (opcao == 1) {
            //================================================================================
            System.out.println("------------------------------------------------------------");
            int numero = 0;
            boolean deuBingo = false;
            System.out.println();
            //Gerar cartelas com 5 numeros ppara cada jogador
            Random rnd = new Random();
            for (int l = 0; l < linhas; l++) {
                bingo[l][0] = l;
                for (int c = 0; c < 5; c++) {
                    bingo[l][c] = rnd.nextInt(60) + 1;
                    if (c == 0) System.out.print("Jogador-" + (l + 1) + ": ");
                    System.out.print(bingo[l][c] + ",");
                }
                System.out.println();
            }
            int[] tabelaSorteados = embaralhaSorteio(numerosBingo);
            //------------------------------------------------------------------------------------
            //Embaralha Array para numeros sorteados.
            
            //------------------------------------------------------------------------------------
            numero = 0;
            deuBingo = false;
            while (deuBingo == false) {
                //Pega um numero sorteado do indice de 1 a 60
                int numeroSorteado = tabelaSorteados[numero];
                numero++;

                System.out.print(numeroSorteado + ",");

                for (int l = 0; l < linhas; l++) {
                    for (int c = 0; c < 5; c++) {
                        if (bingo[l][c] == numeroSorteado)  bingo[l][c] = 0;
                    }
                }
                //verifica se tem 5 zeros
                int contaZero = 0;
                for(int v = 0; v < linhas; v++) {
                    contaZero = 0;
                    for (int k = 0; k < 5; k++) {
                        if(bingo[v][k] == 0) contaZero++;
                        if (contaZero == 5) {
                            deuBingo = true;
                            System.out.println("BIIIGOOOOOO!!! -> Jogador: " + (v + 1));
                        }
                    }
                }
            }
            textoInicial();
            System.out.println("Opção: ");
            opcao = leitor.nextInt();
            if (opcao == 1) {
                System.out.println("Entre com a quantidade de Jogadores:");
                linhas = leitor.nextInt();
                if 
            }
        }
    }

    //-----------------------------------------------------------------------------------
    //FUNCAO
    public static void textoInicial() {
        System.out.println("=================================================================");
        System.out.println("Iniciar Bingo - digite [1], Finalizar Bingo - digite [0]");
        System.out.println("==================================================================");
    }
    //===================================================================================

    //Embaralha Array de numeros de 1 a 60
    public static int[] embaralhaSorteio(int[] Numeros) {
        Random rnd = new Random();
        for (int i = 0; i < 60; i++) {
            // Gerar um índice aleatório para trocar com o índice atual
            int indiceAleatorio = rnd.nextInt(60);
            // Trocar os elementos nos índices i e indiceAleatorio
            int temp = Numeros[i];
            Numeros[i] = Numeros[indiceAleatorio];
            Numeros[indiceAleatorio] = temp;
        }
        return Numeros;    
    }
}