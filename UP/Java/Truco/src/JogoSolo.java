
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogador {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo de Truco!");
        System.out.println("Escolha uma opção:");
        System.out.println("Escolha o Modo de jogo:");
        System.out.println("1 - Solo");
        System.out.println("2 - Duplas");
        int escolha = scanner.nextInt();
        if (escolha == 1) {
            jogarContraMaquina();
        } 
        else if (escolha == 2) {
            jogarContraMaquinaDupla();
        }else {
            System.out.println("Opção inválida. Encerrando o jogo.");
        }
        scanner.close();
    }
    public static void jogarContraMaquina() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pontuacaoJogador1 = 0;
        int pontuacaoPC = 0;
        int pontuacaoMaxima = 12;
        while (true) {
            boolean trucoJogador1 = false;
            boolean trucoPC = false;
            while (pontuacaoJogador1 < pontuacaoMaxima && pontuacaoPC < pontuacaoMaxima) {
                JogoTruco jogo = new JogoTruco();
                jogo.virarCarta();
                Random rand = new Random();
                ArrayList<Baralho> cartasPC = jogo.distribuirCartas(3);
                ArrayList<Baralho> cartasJogador1 = jogo.distribuirCartas(3);
                for (int rodada = 1; rodada <= 3; rodada++) {
                    System.out.println("------ Rodada " + rodada + " ------");
                    System.out.println("");
                    System.out.println("------ Pontuação ------");
                    System.out.println("Jogador1: " + pontuacaoJogador1 + " pontos");
                    System.out.println("PC: " + pontuacaoPC + " pontos");
                    System.out.println("");
                    
                    Baralho cartaPC = cartasPC.get(rand.nextInt(cartasPC.size()));
                    if (!trucoJogador1 && !trucoPC) {
                        System.out.println(" ----- Jogador1 ----- ");
                        System.out.println("A Carta Virada é: " + jogo.getCartaVirada());
                        System.out.println("Suas cartas:");
                        for (int i = 0; i < cartasJogador1.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                        }
                        System.out.println("Deseja pedir truco? (s/n)");
                        String resposta = reader.readLine();
                        if (resposta.equalsIgnoreCase("s")) {
                            trucoJogador1 = true;
                            System.out.println("Você pediu truco!");
    
                            if (rand.nextDouble() <= 0.5 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC aceitou o truco!");
                            } 
                            else if (rand.nextDouble() <= 0.3 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC retrucou!");
                                System.out.println("Você deseja aceitar o truco ou fugir ou retrucar? (s/f/r)");
                                String respostaretruco = reader.readLine();
                                if (respostaretruco.equalsIgnoreCase("s")){
                                    System.out.println("Voce Aceitou o Retruco!");
                                }
                                else if (respostaretruco.equalsIgnoreCase("f")){
                                    pontuacaoPC += 6;
                                }
                                else if (respostaretruco.equalsIgnoreCase("r")){

                                    if (rand.nextDouble() <= 0.1 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                        System.out.println("O PC aceitou o retruco!");
                                    } 
                                }
                                

                            } 
                        
                            else {
                                System.out.println("O PC fugiu!");
                                pontuacaoJogador1 += 3;
                                break;
                            }
                        }
                    }

                    int selecaoPCIndex;
                    while (true) {
                        System.out.println(" ----- Jogador1 ----- ");
                        System.out.println("A Carta Virada é: " + jogo.getCartaVirada());
                        System.out.println("Suas cartas:");
                        for (int i = 0; i < cartasJogador1.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                        }
                        System.out.println("Selecione uma carta: ");
                        try {
                            selecaoPCIndex = Integer.parseInt(reader.readLine()) - 1;
    
                            if (selecaoPCIndex >= 0 && selecaoPCIndex < cartasJogador1.size()) {
                                break;
                            } else {
                                System.out.println("Escolha uma carta válida.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, digite um número válido.");
                        }
                    }
                    System.out.println("Jogador1 selecionou a carta " + cartasJogador1.get(selecaoPCIndex));
                    Baralho cartaJogador1 = cartasJogador1.get(selecaoPCIndex);
                    int selecaoJogador1Index = rand.nextInt(cartasPC.size());

                    if (!trucoPC && rand.nextDouble() <= 0.5 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta() ) {
                        System.out.println("O PC pediu truco!");
                        System.out.println("Você deseja aceitar o truco? (s/n)");
                        String respostat = reader.readLine();
                        if (respostat.equalsIgnoreCase("s")) {
                            System.out.println("Você aceitou o truco!");
                            System.out.println("Deseja Retrucar? (s/n)");
                            String respostaTruco = reader.readLine();
                            if (respostaTruco.equals("s")) {
                                trucoJogador1 = true;
                                if (trucoPC && rand.nextDouble() <= 0.3 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                    trucoPC = true;
                                    System.out.println("O PC aceitou o truco!");
                                    System.out.println("Deseja retrucar? (s/n)");
                                    String resposta9 = reader.readLine();
                                    if (resposta9.equalsIgnoreCase("s")) {
                                        System.out.println("Você pediu retruco!");
                                        if (rand.nextDouble() <= 0.2 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                            System.out.println("O PC aceitou o retruco!");
                                    }
                                    else if (rand.nextDouble() <= 0.1 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()){
                                        System.out.println("O Pc retrucou!");
                                        String respostaretruco2 = reader.readLine();
                                        System.out.println("Você deseja aceitar o truco(s/n)");
                                        if (respostaretruco2.equalsIgnoreCase("s")){
                                            System.out.println("Voce Aceitou o Retruco!");
                                        }
                                        else if (respostaretruco2.equalsIgnoreCase("n")){
                                            pontuacaoPC += 9;
                                        }
                                }
                            }
                                } else {
                                    System.out.println("O PC fugiu!");
                                    pontuacaoJogador1 += 6;
                                    break;
                                }
                            } else if (respostaTruco.equals("n")) {
                                System.out.println("Você não pediu truco adicional.");
                            } else {
                                System.out.println("Opção inválida. Não foi feito nenhum pedido de truco adicional.");
                            }

                        } else if (respostat.equalsIgnoreCase("n")) {
                            System.out.println("Você fugiu do truco!");
                            pontuacaoPC += 3;
                            break;
                        }
                    }


                    System.out.println("PC selecionou a carta " + cartasPC.get(selecaoJogador1Index));
                    cartasJogador1.remove(selecaoPCIndex);
                    cartaPC = cartasPC.get(selecaoJogador1Index);
                    cartasPC.remove(selecaoJogador1Index);
                    if (cartaJogador1.getForcCarta() > cartaPC.getForcCarta()) {
                        System.out.println("Você venceu a rodada " + rodada + "!");
                        pontuacaoJogador1 += 1;
    
                        if (trucoPC){
                            pontuacaoJogador1 += 2;
                        }
                    } else if (cartaJogador1.getForcCarta() < cartaPC.getForcCarta()) {
                        System.out.println("Você perdeu a rodada " + rodada + "!");
                        pontuacaoPC += 1;
    
                        if (trucoJogador1){
                            pontuacaoPC += 2;
                        }
                    } else {
                        System.out.println("Empate na rodada " + rodada + "!");
                        pontuacaoPC += 1;
                        pontuacaoJogador1 += 1;
                    }
                }

                System.out.println("------ Pontuação Final ------");
                System.out.println("Jogador1: " + pontuacaoJogador1 + " pontos");
                System.out.println("PC: " + pontuacaoPC + " pontos");
                if (pontuacaoJogador1 >= pontuacaoMaxima) {
                    System.out.println("Parabéns! Você venceu o jogo!");
                } else if (pontuacaoPC >= pontuacaoMaxima) {
                    System.out.println("O PC venceu o jogo. Melhor sorte na próxima!");
                }
                if (pontuacaoJogador1 >= pontuacaoMaxima || pontuacaoPC >= pontuacaoMaxima) {
                    break;
                }
            }

  
            System.out.println("Deseja começar uma nova partida? (s/n)");
            String novoJogo = reader.readLine();
            if (novoJogo.equalsIgnoreCase("s")) {
                pontuacaoJogador1 = 0;
                pontuacaoPC = 0;
                trucoJogador1 = false;
                trucoPC = false;
            } else {
                break;
            }
        }
        reader.close();
    }

    public static void jogarContraMaquinaDupla() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pontuacaoJogador1 = 0;
        int pontuacaoPC = 0;
        int pontuacaoMaxima = 12;
        Baralho cartaPC2 = null;
        while (true) {
            boolean trucoJogador1 = false;
            boolean trucoPC = false;
            while (pontuacaoJogador1 < pontuacaoMaxima && pontuacaoPC < pontuacaoMaxima) {
                JogoTruco jogo = new JogoTruco();
                jogo.virarCarta();
                Random rand = new Random();
                ArrayList<Baralho> cartasPC = jogo.distribuirCartas(3);
                ArrayList<Baralho> cartasPC2 = jogo.distribuirCartas(3);
                ArrayList<Baralho> cartasJogador1 = jogo.distribuirCartas(3);
                ArrayList<Baralho> cartasJogador2 = jogo.distribuirCartas(3);
                for (int rodada = 1; rodada <= 3; rodada++) {
                    System.out.println("------ Rodada " + rodada + " ------");
                    System.out.println("");
                    System.out.println("------ Pontuação ------");
                    System.out.println("Equipe Jogadores: " + pontuacaoJogador1 + " pontos");
                    System.out.println("Equipe PC: " + pontuacaoPC + " pontos");
                    System.out.println("");
                    Baralho cartaPC = cartasPC.get(rand.nextInt(cartasPC.size()));
                    if (!trucoJogador1 && !trucoPC) {
                        System.out.println(" ----- Jogador1 ----- ");
                        System.out.println("A Carta Virada é: " + jogo.getCartaVirada());
                        System.out.println("Suas cartas:");
                        
                        for (int i = 0; i < cartasJogador1.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                        }
                        System.out.println("Deseja pedir truco? (s/n)");
                        String resposta = reader.readLine();
                        if (resposta.equalsIgnoreCase("s")) {
                            trucoJogador1 = true;
                            System.out.println("Você pediu truco!");
    
                            if (rand.nextDouble() <= 0.5 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC aceitou o truco!");
                            } 
                            else if (rand.nextDouble() <= 0.3 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC retrucou!");
                                System.out.println("Você deseja aceitar o truco ou fugir ou retrucar? (s/f/r)");
                                String respostaretruco = reader.readLine();
                                if (respostaretruco.equalsIgnoreCase("s")){
                                    System.out.println("Voce Aceitou o Retruco!");
                                }
                                else if (respostaretruco.equalsIgnoreCase("f")){
                                    pontuacaoPC += 6;
                                }
                                else if (respostaretruco.equalsIgnoreCase("r")){

                                    if (rand.nextDouble() <= 0.1 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                        System.out.println("O PC aceitou o retruco!");
                                    } 
                                }
                                

                            } 
                        
                            else {
                                System.out.println("O PC fugiu!");
                                pontuacaoJogador1 += 3;
                                break;
                            }
                        }
                    }


                    int selecaoPCIndex;
                    while (true) {
                        System.out.println(" ----- Jogador1 ----- ");
                        System.out.println("A Carta Virada é: " + jogo.getCartaVirada());
                        System.out.println("Suas cartas:");
                        for (int i = 0; i < cartasJogador1.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                        }
                        System.out.println("Selecione uma carta: ");
                        try {
                            selecaoPCIndex = Integer.parseInt(reader.readLine()) - 1;
    
                            if (selecaoPCIndex >= 0 && selecaoPCIndex < cartasJogador1.size()) {
                                break;
                            } else {
                                System.out.println("Escolha uma carta válida.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, digite um número válido.");
                        }
                    }
                    System.out.println("Jogador1 selecionou a carta " + cartasJogador1.get(selecaoPCIndex));
                    Baralho cartaJogador1 = cartasJogador1.get(selecaoPCIndex);
                    int selecaoJogador1Index = rand.nextInt(cartasPC.size());




                    System.out.println("PC selecionou a carta " + cartasPC.get(selecaoJogador1Index));
                    cartasJogador1.remove(selecaoPCIndex);
                    cartaPC = cartasPC.get(selecaoJogador1Index);
                    cartasPC.remove(selecaoJogador1Index);

                    if (!trucoPC && rand.nextDouble() <= 0.5 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta() ) {
                        System.out.println("O PC pediu truco!");
                        System.out.println("Você deseja aceitar o truco? (s/n)");
                        String respostat = reader.readLine();
                        if (respostat.equalsIgnoreCase("s")) {
                            System.out.println("Você aceitou o truco!");
                            System.out.println("Deseja Retrucar? (s/n)");
                            String respostaTruco = reader.readLine();
                            if (respostaTruco.equals("s")) {
                                trucoJogador1 = true;
                                if (trucoPC && rand.nextDouble() <= 0.3 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                    trucoPC = true;
                                    System.out.println("O PC aceitou o truco!");
                                    System.out.println("Deseja retrucar? (s/n)");
                                    String resposta9 = reader.readLine();
                                    if (resposta9.equalsIgnoreCase("s")) {
                                        System.out.println("Você pediu retruco!");
                                        if (rand.nextDouble() <= 0.2 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                            System.out.println("O PC aceitou o retruco!");
                                    }
                                    else if (rand.nextDouble() <= 0.1 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()){
                                        System.out.println("O Pc retrucou!");
                                        String respostaretruco2 = reader.readLine();
                                        System.out.println("Você deseja aceitar o truco(s/n)");
                                        if (respostaretruco2.equalsIgnoreCase("s")){
                                            System.out.println("Voce Aceitou o Retruco!");
                                        }
                                        else if (respostaretruco2.equalsIgnoreCase("n")){
                                            pontuacaoPC += 9;
                                        }
                                }
                            }
                                } else {
                                    System.out.println("O PC fugiu!");
                                    pontuacaoJogador1 += 6;
                                    break;
                                }
                            } else if (respostaTruco.equals("n")) {
                                System.out.println("Você não pediu truco adicional.");
                            } else {
                                System.out.println("Opção inválida. Não foi feito nenhum pedido de truco adicional.");
                            }

                        } else if (respostat.equalsIgnoreCase("n")) {
                            System.out.println("Você fugiu do truco!");
                            pontuacaoPC += 3;
                            break;
                        }
                    }

                    
                    int selecaoPC2Index = -1;
                    while (true) {
                        System.out.println(" ----- Jogador2 ----- ");
                        System.out.println("A Carta Virada é: " + jogo.getCartaVirada());
                        System.out.println("Suas cartas:");
                        for (int i = 0; i < cartasJogador2.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador2.get(i));
                        }
                        if (!trucoJogador1 && !trucoPC) {
                            System.out.println("");
                            System.out.println("Cartas de Sua Equipe:");
                            for (int i = 0; i < cartasJogador1.size(); i++) {
                                System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                            }
                              for (int i = 0; i < cartasJogador1.size(); i++) {
                            System.out.println("Carta " + (i + 1) + ": " + cartasJogador1.get(i));
                        }
                        System.out.println("Deseja pedir truco? (s/n)");
                        String resposta = reader.readLine();
                        if (resposta.equalsIgnoreCase("s")) {
                            trucoJogador1 = true;
                            System.out.println("Você pediu truco!");
    
                            if (rand.nextDouble() <= 0.5 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC aceitou o truco!");
                            } 
                            else if (rand.nextDouble() <= 0.3 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                System.out.println("O PC retrucou!");
                                System.out.println("Você deseja aceitar o truco ou fugir ou retrucar? (s/f/r)");
                                String respostaretruco = reader.readLine();
                                if (respostaretruco.equalsIgnoreCase("s")){
                                    System.out.println("Voce Aceitou o Retruco!");
                                }
                                else if (respostaretruco.equalsIgnoreCase("f")){
                                    pontuacaoPC += 6;
                                }
                                else if (respostaretruco.equalsIgnoreCase("r")){

                                    if (rand.nextDouble() <= 0.1 && cartaPC.getForcCarta() > jogo.getCartaVirada().getForcCarta()) {
                                        System.out.println("O PC aceitou o retruco!");
                                    } 
                                }
                                

                            } 
                        
                            else {
                                System.out.println("O PC fugiu!");
                                pontuacaoJogador1 += 3;
                                break;
                            }
                        }
                    }

                        System.out.println("Selecione uma carta: ");
    
                        try {
                            int input = Integer.parseInt(reader.readLine()) - 1;
    
                            if (input >= 0 && input < cartasJogador2.size()) {
                                selecaoPC2Index = input;
                                break;
                            } else {
                                System.out.println("Escolha uma carta válida.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, digite um número válido.");
                        }
                    }
                    System.out.println("Jogador2 selecionou a carta " + cartasJogador2.get(selecaoPC2Index));
                    Baralho cartaJogador2 = cartasJogador2.get(selecaoPC2Index);
                    int selecaoJogador2Index = rand.nextInt(cartasPC2.size());
                    System.out.println("PC2 selecionou a carta " + cartasPC2.get(selecaoJogador2Index));
                    cartasJogador2.remove(selecaoPC2Index);
                    cartaPC2 = cartasPC2.get(selecaoJogador2Index);
                    cartasPC2.remove(selecaoJogador2Index);
                    if (cartaJogador1.getForcCarta() > cartaPC.getForcCarta()) {
                        if (cartaJogador2.getForcCarta() > cartaPC2.getForcCarta()){
                        System.out.println("Você venceu a rodada " + rodada + "!");
                        pontuacaoJogador1 += 1;
    
                        if (trucoPC){
                            pontuacaoJogador1 += 2;
                        }}
                    } else if (cartaJogador1.getForcCarta() < cartaPC.getForcCarta()) {
                        if (cartaJogador2.getForcCarta() < cartaPC2.getForcCarta()){
                        System.out.println("Você perdeu a rodada " + rodada + "!");
                        pontuacaoPC += 1;
    
                        if (trucoJogador1){
                            pontuacaoPC += 2;
                        }}
                    } else {
                        System.out.println("Empate na rodada " + rodada + "!");
                        pontuacaoPC += 1;
                        pontuacaoJogador1 += 1;
                    }
                }

                System.out.println("------ Pontuação Final ------");
                System.out.println("Equipe Jogadores: " + pontuacaoJogador1 + " pontos");
                System.out.println("Equipe PC: " + pontuacaoPC + " pontos");
                if (pontuacaoJogador1 >= pontuacaoMaxima) {
                    System.out.println("Parabéns! Sua Equipe venceu o jogo!");
                } else if (pontuacaoPC >= pontuacaoMaxima) {
                    System.out.println("A Equipe venceu o jogo. Melhor sorte na próxima!");
                }
                if (pontuacaoJogador1 >= pontuacaoMaxima || pontuacaoPC >= pontuacaoMaxima) {
                    break;
                }
            }
            System.out.println("Deseja começar uma nova partida? (s/n)");
            String novoJogo = reader.readLine();
            if (novoJogo.equalsIgnoreCase("s")) {
                pontuacaoJogador1 = 0;
                pontuacaoPC = 0;
                trucoJogador1 = false;
                trucoPC = false;
            } else {
                break;
            }
        }
        reader.close();
    }
    }
    