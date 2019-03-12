package ch.heig.pl.lecteursredacteurs;

import java.util.*;

public class Controleur
{
    private Redacteur writer = null;
    private Set<Lecteur> readers = new HashSet<>();
    private Queue<Redacteur> waitingWriters = new LinkedList<>();
    private Queue<Lecteur> waitingReaders = new LinkedList<>();

    public boolean isWritable() {
        return this.writer == null && this.readers.isEmpty();
    }

    public boolean isReadable() {
        return this.writer == null && waitingWriters.isEmpty();
    }

    synchronized void startWriting(Redacteur writer) {
        this.waitingWriters.add(writer);

        while (!this.isWritable() || this.waitingWriters.peek() != writer)
        {
            try {
                this.wait();
            }
            catch (InterruptedException ignored) { }
        }

        this.writer = this.waitingWriters.poll();
    }

    synchronized void stopWriting(Redacteur writer) {
        if (this.writer == writer) {
            this.writer = null;
        }
        this.notifyAll();
    }

    synchronized void startReading(Lecteur reader) {
        this.waitingReaders.add(reader);

        while (!this.isReadable() || this.waitingReaders.peek() != reader)
        {
            try {
                this.wait();
            }
            catch (InterruptedException ignored) { }
        }

        this.readers.add(this.waitingReaders.poll());
        this.notifyAll();
    }

    synchronized void stopReading(Lecteur reader) {
        this.readers.remove(reader);
        this.notifyAll();
    }
}
