package ch.heig.pl.lecteursredacteurs;

public class Redacteur
{
    private Controleur controleur;
    private Thread thread;

    public Redacteur(Controleur controleur)
    {
        this.controleur = controleur;
        this.thread = new Thread(new WriteSession());
    }

    public boolean isWaiting()
    {
        return this.controleur.isWriterWaiting(this);
    }

    public synchronized void startWrite() throws InterruptedException
    {
        this.thread.start();
        this.wait();
    }

    public synchronized void stopWrite() throws InterruptedException
    {
        this.controleur.stopWriting(this);
        this.thread.join();
    }

    private class WriteSession implements Runnable
    {
        public void run()
        {
            Redacteur.this.controleur.startWriting(Redacteur.this);
        }
    }
}
