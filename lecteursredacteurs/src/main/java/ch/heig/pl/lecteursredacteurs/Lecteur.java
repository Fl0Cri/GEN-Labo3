package ch.heig.pl.lecteursredacteurs;

public class Lecteur implements Runnable{
    private Controleur controleur;
    private boolean waiting;

    public Lecteur(Controleur controleur) {
        this.controleur = controleur;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void startRead() {
        new Thread(this).start();
        synchronized (this) {
            while (this.controleur.isReadable()) {
                try {
                    this.wait();
                    this.waiting = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.waiting = false;
        this.controleur.startReading(this);
    }

    public void stopRead() {
        synchronized (this) {
            this.controleur.stopReading(this);
            this.notifyAll();
        }
    }

    public void run() {}
}
