package ch.heig.pl.lecteursredacteurs;

public class Lecteur implements Runnable{
    private Controleur controleur;
    private Boolean waiting;
    private Thread thread;

    public Lecteur(Controleur controleur) {
        this.controleur = controleur;
        this.thread = new Thread(this);
    }

    public synchronized boolean isWaiting() {
        return this.waiting;
    }

    public void startRead() {
        this.thread.start();
    }

    public void stopRead() {
        this.controleur.stopReading(this);
    }

    public void run() {
        this.waiting = true;
        this.controleur.startReading(this);
        this.waiting = false;
    }
}
