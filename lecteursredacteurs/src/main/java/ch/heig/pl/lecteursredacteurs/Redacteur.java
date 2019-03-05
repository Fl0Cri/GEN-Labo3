package ch.heig.pl.lecteursredacteurs;

public class Redacteur implements Runnable{
    private Controleur controleur;
    private boolean waiting;
    public Redacteur(Controleur controleur) {
        this.controleur = controleur;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public void startWrite() {
        new Thread(this).start();
        synchronized (this) {
            while (this.controleur.isWritable()) {
                try {
                    this.wait();
                    this.waiting = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.waiting = false;
        this.controleur.startWriting();
    }

    public void stopWrite() {
        synchronized (this) {
            this.controleur.stopWriting();
            this.notifyAll();
        }
    }

    public void run() {}
}
