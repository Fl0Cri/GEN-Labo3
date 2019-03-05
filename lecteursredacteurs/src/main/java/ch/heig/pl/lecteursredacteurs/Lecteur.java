package ch.heig.pl.lecteursredacteurs;

public class Lecteur {
    private Controleur controleur;
    private boolean waiting;

    public Lecteur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void startRead() {
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void stopRead() {
    }
}
