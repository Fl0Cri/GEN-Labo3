package ch.heig.pl.lecteursredacteurs;

import java.util.*;

public class Controleur
{
    private Redacteur writer = null;
    private Set<Lecteur> readers = new HashSet<>();
    private Queue<Redacteur> waitingWriters = new LinkedList<>();
    private Queue<Lecteur> waitingReaders = new LinkedList<>();

    public boolean isWritable()
    {
        return this.writer == null && this.readers.isEmpty();
    }

    public boolean isReadable()
    {
        return this.writer == null && waitingWriters.isEmpty();
    }

    boolean isWriterWaiting(Redacteur writer)
    {
        return waitingWriters.contains(writer);
    }

    boolean isReaderWaiting(Lecteur lecteur)
    {
        return waitingReaders.contains(lecteur);
    }

    synchronized void startWriting(Redacteur writer)
    {
        this.waitingWriters.add(writer);

        synchronized (writer)
        {
            writer.notify();
        }

        this.tick();
    }

    synchronized void stopWriting(Redacteur writer)
    {
        if (this.writer == writer)
        {
            this.writer = null;
        }

        this.tick();
    }

    synchronized void startReading(Lecteur reader)
    {
        this.waitingReaders.add(reader);

        synchronized (reader)
        {
            reader.notify();
        }

        this.tick();
    }

    synchronized void stopReading(Lecteur reader)
    {
        this.readers.remove(reader);
        this.tick();
    }

    private synchronized void tick()
    {
        if (this.writer != null)
        {
            return;
        }

        if (!this.waitingWriters.isEmpty())
        {
            if (this.isWritable())
            {
                this.writer = this.waitingWriters.poll();
                this.notifyAll();
            }
        }
        else
        {
            while (!this.waitingReaders.isEmpty())
            {
                this.readers.add(this.waitingReaders.poll());
            }
            this.notifyAll();
        }
    }
}
