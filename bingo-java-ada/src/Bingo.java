import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random rnd = new Random();
        //Chama a mensagem inicial
        textoInicial();
        System.out.println("Opção: ");
        int opcao = leitor.nextInt();
        System.out.println("Entre com a quantidade de Jogadores:");
        int linhas = leitor.nextInt();
        int colunas = 5;
        int rodada = 1;
        //String[] nomeJogadores = new String[linhas];
        int[][] bingo = new int[linhas][colunas];
        int[][] bingoClone = new int[linhas][colunas];
        int[] tabelaSorteados = new int[60];
        int numeroJogadorGanhador = 0;
        String numerosSorteados = "";
        //===================================================================================
        //Cria tabela de numeros para sorteio de 1 a 60.
        for (int x = 0; x < 60; x++) {
            tabelaSorteados[x] = x + 1;
        }
        //cria lista de 33 nome de jogadores
        String listaNomesT = "Ana-Marta-Tereza-Cristina-Vanessa-Eva-Mariza-Carla-Mirian-Helena-Sheva-Jil-Ada-Hunigan-Clair-Rebeca-Debora";
        String[] nomeJogadores = new String[linhas];
        String[] aux = listaNomesT.split("-");
        for (int numeroJ = 0; numeroJ < linhas; numeroJ++) {
            nomeJogadores[numeroJ] = "Jogador-";
        }
        for (int nome = 0; nome < linhas; nome++) {
            nomeJogadores[nome] = aux[nome];
        }

        // Imprimir o array embaralhado
        //System.out.println("Array aleatório: " + Arrays.toString(tabelaSorteados));
        System.out.println("Tabelas de Jogadores:");
        System.out.println("=================================================================");
        //===================================================================================
        int numero = 0;
        boolean deuBingo = false;
        while (opcao == 1 && linhas > 1 && linhas <= 1000) {
            //Gerar cartelas com 5 numeros ppara cada jogador sem repetição!
            int contador = 0;
            boolean existe = false;
            for (int l = 0; l < linhas; l++) {
                while (contador < 5) {
                    //Pega um numero sorteado do indice de 1 a 60
                    int indiceAleatorio = rnd.nextInt(60) + 1;

                    for (int x = 0; x < 5; x++) {
                        if (bingo[l][x] == indiceAleatorio) existe = true;
                    }
                    if (existe == false) {
                        bingo[l][contador] = indiceAleatorio;
                        bingoClone[l][contador] = indiceAleatorio;
                        contador++;
                    }
                    existe = false;
                }
                contador = 0;
            }
            //imprimir cartelas dos Jogadores
            imprimirCartelasJogadores(nomeJogadores, bingo);

            //------------------------------------------------------------------------------------
            //Embaralha Array para numeros sorteados.
            for (int i = 0; i < 60; i++) {
                // Gerar um índice aleatório para trocar com o índice atual
                int indiceAleatorio = rnd.nextInt(60);
                // Trocar os elementos nos índices i e indiceAleatorio
                int temp = tabelaSorteados[i];
                tabelaSorteados[i] = tabelaSorteados[indiceAleatorio];
                tabelaSorteados[indiceAleatorio] = temp;
            }
            //------------------------------------------------------------------------------------
            numero = 0;
            deuBingo = false;
            numerosSorteados = "";

            while (deuBingo == false) {
                int numeroSorteado = tabelaSorteados[numero];
                numero++;
                numerosSorteados += numeroSorteado + ",";

                for (int l = 0; l < linhas; l++) {
                    for (int c = 0; c < 5; c++) {
                        if (bingo[l][c] == numeroSorteado)  bingo[l][c] = 0;
                    }
                }
                //verifica se tem 5 zeros na cartela do jogador
                int contaZero = 0;
                for(int v = 0; v < linhas; v++) {
                    contaZero = 0;
                    for (int k = 0; k < 5; k++) {
                        if(bingo[v][k] == 0) contaZero++;
                        if (contaZero == 5) {
                            deuBingo = true;
                            numeroJogadorGanhador = v;
                        }
                    }
                }
            }
            String nomeJogador = "Não esta na lista dos 33 jogadores";
            if (numeroJogadorGanhador < nomeJogadores.length) nomeJogador = nomeJogadores[numeroJogadorGanhador];
            textoFinal(rodada, numerosSorteados, nomeJogador,numeroJogadorGanhador, bingoClone);

            opcao = leitor.nextInt();
            if (opcao == 1) {
                rodada++;
            }

        }
    }

    //-----------------------------------------------------------------------------------
    //Meus Métodos
    public static void textoInicial() {
        System.out.println("=================================================================");
        System.out.println("=================== BINGO JAVA ADA ==============================");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("     Digite - [1] para Jogar, Dgite - [0] para Sair");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("=================================================================");
    }
    //===================================================================================
    public static void textoFinal(int rodada, String numerosSorteados, String nomeJogador, int numeroJogador, int[][] numerosDaCartela) {
        System.out.println("=================================================================");
        System.out.println("                  BIIIGOOOOOOOOOOOOO !!!                         ");
        System.out.println("=================================================================");
        System.out.println("Rodada: " + rodada);
        System.out.println("Números sorteados:");
        System.out.println(numerosSorteados);
        System.out.println("=================================================================");
        System.out.println("Vencedor: Jogador - " + (numeroJogador + 1) + " -> " + nomeJogador);
        System.out.println("Cartela do ganhador: " + Arrays.toString(numerosDaCartela[numeroJogador]));
        System.out.println("=================================================================");
        System.out.println("Deseja jogar Novamente, Digite - [1] ou Digite - [0] para Sair.: ");
    }
    //=====================================================================================
    //Imprimir cartelas dos Jogadores
    public static void imprimirCartelasJogadores(String[] nomeJogadores, int[][] bingoNumerado) {
        String[] cartela = new String[5];
        for (int l = 0; l < bingoNumerado.length; l++) {
            for (int i = 0; i < 5; i++) {
                String aux = "00";
                if (bingoNumerado[l][i] < 10) {
                    aux = "0" + bingoNumerado[l][i];
                    cartela[i] = aux;
                } else {
                    cartela[i] = Integer.toString(bingoNumerado[l][i]);
                }
            }

            System.out.println(l + 1 + "\t" + "Cartela: " + Arrays.toString(cartela) + "\t" + nomeJogadores[l]);
        }
    }
    //=========================================================================================

   //FIM da CLASSE
}
