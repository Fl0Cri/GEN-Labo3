package ch.heig.pl.lecteursredacteurs;

public class Lecteur
{
    private Controleur controleur;
    private Thread thread;

    public Lecteur(Controleur controleur)
    {
        this.controleur = controleur;
        this.thread = new Thread(new ReadSession());
    }

    public boolean isWaiting()
    {
        return this.controleur.isReaderWaiting(this);
    }

    public synchronized void startRead() throws InterruptedException
    {
        this.thread.start();
        this.wait();
    }

    public synchronized void stopRead() throws InterruptedException
    {
        this.controleur.stopReading(this);
        this.thread.join();
    }

    private class ReadSession implements Runnable
    {
        public void run()
        {
            Lecteur.this.controleur.startReading(Lecteur.this);
        }
    }
}
