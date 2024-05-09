import java.util.ArrayList;
import java.util.Random;

public class JogoTruco extends BaralhoManager {
    private Baralho cartaVirada;

    public void virarCarta() {
        Random rand = new Random();
        this.cartaVirada = getCartaPorId(rand.nextInt(40) + 1);
    }

    public Baralho getCartaVirada() {
        return this.cartaVirada;
    }

    public Baralho determinarVencedor(Baralho carta1, Baralho carta2) {
        ordenarBaralho();
        if (carta1.getForcCarta() == carta2.getForcCarta()) {
            return (carta1.getIdCarta() > carta2.getIdCarta()) ? carta1 : carta2;
        } else {
            return (carta1.getForcCarta() > carta2.getForcCarta()) ? carta1 : carta2;
        }
    }

    public ArrayList<Baralho> distribuirCartas(int numCartas) {
        ArrayList<Baralho> cartasDistribuidas = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numCartas && getBaralho().size() >= numCartas; i++) {
            Baralho carta = getBaralho().get(rand.nextInt(getBaralho().size()));
            cartasDistribuidas.add(carta);
            getBaralho().remove(carta);
        }
        return cartasDistribuidas;
    }
}
