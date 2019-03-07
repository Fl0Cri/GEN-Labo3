package ch.heig.pl.lecteursredacteurs;

public class Redacteur implements Runnable{
    private Controleur controleur;
    private boolean waiting;
    private Thread thread;

    public Redacteur(Controleur controleur) {
        this.controleur = controleur;
        this.thread = new Thread(this);
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    private synchronized void setWaiting(boolean value) {
        this.waiting = value;
    }

    public void startWrite() {
        this.thread.start();
    }

    public void stopWrite() {
        this.controleur.stopWriting(this);
    }

    public void run() {
        this.setWaiting(true);
        this.controleur.startWriting(this);
        this.setWaiting(false);
    }
}
