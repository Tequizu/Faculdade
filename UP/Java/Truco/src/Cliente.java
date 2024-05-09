import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("192.168.20.82", 80);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pontuacaoServidor = 0;
        int pontuacaoCliente= 0;
        int pontuacaoMaxima= 12;
        while (pontuacaoServidor < pontuacaoMaxima && pontuacaoCliente < pontuacaoMaxima){
        JogoTruco jogo = new JogoTruco();
        ArrayList<Baralho> cartasCliente = jogo.distribuirCartas(3);

        for (int rodada = 1; rodada <= 3; rodada++) {
            System.out.println("Aguardando carta selecionada pelo servidor...");
            Baralho cartaSelecionada = null;
            int selecaoClienteIndex = -1;
            while (!cartasCliente.isEmpty()) {

                try {
                    cartaSelecionada = (Baralho) in.readObject();
                    System.out.println("Servidor selecionou a carta: " + cartaSelecionada);
                } catch (ClassCastException e) {
                    System.out.println("Erro ao converter objeto recebido em Baralho: " + e.getMessage());
                    continue;
                }
            while (cartaSelecionada!= null) {
                    System.out.println(" ----- Cliente ----- ");
                    System.out.println("Suas cartas:");
                    for (int i = 0; i < cartasCliente.size(); i++) {
                        System.out.println("Carta " + (i + 1) + ": " + cartasCliente.get(i));
                    }
                    System.out.println("Selecione uma carta: ");
                    try {
                        selecaoClienteIndex = Integer.parseInt(reader.readLine()) - 1;

                        if (selecaoClienteIndex >= 0 && selecaoClienteIndex < cartasCliente.size()) {
                            break;
                        } else {
                            System.out.println("Escolha uma carta válida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, digite um número válido.");
                    }
                }
                Baralho cartaCliente = cartasCliente.get(selecaoClienteIndex);
                out.writeObject(cartasCliente.get(selecaoClienteIndex));
                cartasCliente.remove(selecaoClienteIndex);
                System.out.println("Aguardando carta selecionada pelo Cliente...");
                if (cartaCliente.getForcCarta() > cartaSelecionada.getForcCarta()) {
                    System.out.println("Você venceu a rodada " + rodada + "!");
                    pontuacaoCliente+=1;
                } else if (cartaCliente.getForcCarta() < cartaSelecionada.getForcCarta()) {
                    System.out.println("Você perdeu a rodada " + rodada + "!");
                    pontuacaoServidor+=1;

                } else {
                    System.out.println("Empate na rodada " + rodada + "!");
                    pontuacaoServidor+=1;
                    pontuacaoCliente+=1;
                }
            }
            if (!socket.isConnected()) {
                System.out.println("Conexão encerrada pelo servidor.");
                break;
             
            }
            
        }

        if(pontuacaoCliente == pontuacaoMaxima){
            System.out.println("Você venceu o Truco ");
        }
        else if(pontuacaoServidor == pontuacaoMaxima){
            System.out.println("Você Perdeu o Truco ");
        }
        else if(pontuacaoCliente == pontuacaoMaxima && pontuacaoServidor == pontuacaoMaxima){
            System.out.println("O Truco Empatou ");
        }

    }
    socket.close();
}
}