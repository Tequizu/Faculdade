import java.util.ArrayList;
import java.util.Collections;

public class BaralhoManager {
    private ArrayList<Baralho> baralho;

    public BaralhoManager() {
        this.baralho = new ArrayList<>();
        inicializarBaralho();
        embaralhar();
    }

    private void inicializarBaralho() {
        adicionarCarta(new Baralho("Quatro(4) de Ouro", 1, 1, 1));
        adicionarCarta(new Baralho("Quatro(4) de Espadas", 2, 2, 1));
        adicionarCarta(new Baralho("Quatro(4) de Copas", 3, 3, 1));
        adicionarCarta(new Baralho("Quatro(4) de Paus", 4, 4, 1));

        adicionarCarta(new Baralho("Cinco(5) de Ouro", 5, 1, 2));
        adicionarCarta(new Baralho("Cinco(5) de Espadas", 6, 2, 2));
        adicionarCarta(new Baralho("Cinco(5) de Copas", 7, 3, 2));
        adicionarCarta(new Baralho("Cinco(5) de Paus", 8, 4, 2));

        adicionarCarta(new Baralho("Seis(6) de Ouro", 9, 1, 3));
        adicionarCarta(new Baralho("Seis(6) de Espadas", 10, 2, 3));
        adicionarCarta(new Baralho("Seis(6) de Copas", 11, 3, 3));
        adicionarCarta(new Baralho("Seis(6) de Paus", 12, 4, 3));

        adicionarCarta(new Baralho("Sete(7) de Ouro", 13, 1, 4));
        adicionarCarta(new Baralho("Sete(7) de Espadas", 14, 2, 4));
        adicionarCarta(new Baralho("Sete(7) de Copas", 15, 3, 4));
        adicionarCarta(new Baralho("Sete(7) de Paus", 16, 4, 4));

        adicionarCarta(new Baralho("Rainha(Q) de Ouro", 17, 1, 5));
        adicionarCarta(new Baralho("Rainha(Q) de Espadas", 18, 2, 5));
        adicionarCarta(new Baralho("Rainha(Q) de Copas", 19, 3, 5));
        adicionarCarta(new Baralho("Rainha(Q) de Paus", 20, 4, 5));

        adicionarCarta(new Baralho("Valete(J) de Ouro", 21, 1, 6));
        adicionarCarta(new Baralho("Valete(J) de Espadas", 22, 2, 6));
        adicionarCarta(new Baralho("Valete(J) de Copas", 23, 3, 6));
        adicionarCarta(new Baralho("Valete(J) de Paus", 24, 4, 6));

        adicionarCarta(new Baralho("Rei(K) de Ouro", 25, 1, 7));
        adicionarCarta(new Baralho("Rei(K) de Espadas", 26, 2, 7));
        adicionarCarta(new Baralho("Rei(K) de Copas", 27, 3, 7));
        adicionarCarta(new Baralho("Rei(K) de Paus", 28, 4, 7));

        adicionarCarta(new Baralho("Ás(A) de Ouro", 29, 1, 8));
        adicionarCarta(new Baralho("Ás(A) de Espadas", 30, 2, 8));
        adicionarCarta(new Baralho("Ás(A) de Copas", 31, 3, 8));
        adicionarCarta(new Baralho("Ás(A) de Paus", 32, 4, 8));

        adicionarCarta(new Baralho("Dois(2) de Ouro", 33, 1, 9));
        adicionarCarta(new Baralho("Dois(2) de Espadas", 34, 2, 9));
        adicionarCarta(new Baralho("Dois(2) de Copas", 35, 3, 9));
        adicionarCarta(new Baralho("Dois(2) de Paus", 36, 4, 9));

        adicionarCarta(new Baralho("Três(3) de Ouro", 37, 1, 10));
        adicionarCarta(new Baralho("Três(3) de Espadas", 38, 2, 10));
        adicionarCarta(new Baralho("Três(3) de Copas", 39, 3, 10));
        adicionarCarta(new Baralho("Três(3) de Paus", 40, 4, 10));

    }



    public void adicionarCarta(Baralho carta) {
        this.baralho.add(carta);
    }

    public ArrayList<Baralho> getBaralho() {
        return this.baralho;
    }

    public Baralho getCartaPorId(int idCarta) {
        for (Baralho carta : this.baralho) {
            if (carta.getIdCarta() == idCarta) {
                return carta;
            }
        }
        return null;
    }

    public void embaralhar() {
        Collections.shuffle(this.baralho);
    }

    public void removerCarta(Baralho carta) {
        this.baralho.remove(carta);
    }

    public void ordenarBaralho() {
        Collections.sort(this.baralho, (carta1, carta2) -> {
            if (carta1.getEscCoringa() != carta2.getEscCoringa()) {
                return Integer.compare(carta1.getEscCoringa(), carta2.getEscCoringa());
            } else {
                return Integer.compare(carta1.getIdCarta(), carta2.getIdCarta());
            }
        });
    }
}