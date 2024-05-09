import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    @SuppressWarnings({ "unused", "resource" })
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(80);
        System.out.println("Servidor iniciado na porta 80");
        Socket client = server.accept();
        System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        System.out.println("Jogador conectado. A partida iniciará em breve!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pontuacaoServidor = 0;
        int pontuacaoCliente= 0;
        int pontuacaoMaxima= 12;
        while (pontuacaoServidor < pontuacaoMaxima && pontuacaoCliente < pontuacaoMaxima){
            JogoTruco jogo = new JogoTruco();
        ArrayList<Baralho> cartasServidor = jogo.distribuirCartas(3);
        for (int rodada = 1; rodada <= 3; rodada++) {
            int selecaoServidorIndex=-1;
            while (!cartasServidor.isEmpty()) {
                System.out.println(" ----- Servidor ----- ");
                System.out.println("");
                System.out.println("------ Pontuação ------");
                System.out.println("Servidor: " + pontuacaoServidor + " pontos");
                System.out.println("Cliente: " + pontuacaoCliente + " pontos");
                System.out.println("");
                System.out.println("Suas cartas:");
                for (int i = 0; i < cartasServidor.size(); i++) {
                    System.out.println("Carta " + (i + 1) + ": " + cartasServidor.get(i));
                }
                System.out.println("Selecione uma carta: ");
                try {
                    selecaoServidorIndex = Integer.parseInt(reader.readLine()) - 1;

                    if (selecaoServidorIndex >= 0 && selecaoServidorIndex < cartasServidor.size()) {
                        break;
                    } else {
                        System.out.println("Escolha uma carta válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Porfavor, digite um número válido.");
                }
            }
            Baralho cartaServidor = cartasServidor.get(selecaoServidorIndex);
            out.writeObject(cartasServidor.get(selecaoServidorIndex));
            cartasServidor.remove(selecaoServidorIndex);
            System.out.println("Aguardando carta selecionada pelo Cliente...");
            Baralho cartaSelecionadaCliente = (Baralho) in.readObject();
            System.out.println("Cliente selecionou a carta: " + cartaSelecionadaCliente);
                if (cartaServidor.getForcCarta() > cartaSelecionadaCliente.getForcCarta()) {
                        System.out.println("Você venceu a rodada " + rodada + "!");
                        pontuacaoServidor+=1;

                    } else if (cartaServidor.getForcCarta() < cartaSelecionadaCliente.getForcCarta()) {
                        System.out.println("Você perdeu a rodada " + rodada + "!");
                        pontuacaoCliente+=1;
                    } else {
                        System.out.println("Empate na rodada " + rodada + "!");
                                            pontuacaoServidor+=1;
                                            pontuacaoCliente+=1;
                    }
                    
                }
        if(pontuacaoCliente == pontuacaoMaxima){
            System.out.println("Você Perdeu o Truco ");
            
        }
        else if(pontuacaoServidor == pontuacaoMaxima){
            System.out.println("Você venceu o Truco ");
        }
        else if(pontuacaoCliente == pontuacaoMaxima && pontuacaoServidor == pontuacaoMaxima){
            System.out.println("O Truco Empatou ");
        }
        }
    }
}