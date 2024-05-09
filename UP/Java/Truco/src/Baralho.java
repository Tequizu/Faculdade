import java.io.Serializable;

public class Baralho implements Serializable {
    private String nomeCarta;
    private int idCarta;
    private int forcCarta;
    private int escCoringa;

    public Baralho(String nomeCarta, int idCarta, int forcCarta, int escCoringa) {
        this.nomeCarta = nomeCarta;
        this.idCarta = idCarta;
        this.forcCarta = forcCarta;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public int getForcCarta() {
        return forcCarta;
    }

    public int getEscCoringa() {
        return escCoringa;
    }

    @Override
    public String toString() {
        return this.nomeCarta;
    }
}