package ch.heig.pl.lecteursredacteurs;

import java.util.*;

public class Controleur {
    private Redacteur writer = null;
    private Set<Lecteur> readers = new HashSet<>();
    private Queue<Redacteur> waitingWriters = new PriorityQueue<>();
    private Queue<Lecteur> waitingReaders = new PriorityQueue<>();

    public boolean isWritable() {
        return this.writer == null && this.readers.isEmpty();
    }

    public boolean isReadable() {
        return this.writer == null && waitingWriters.isEmpty();
    }

    public synchronized void startWriting(Redacteur writer) {
        this.waitingWriters.add(writer);
        while (!this.isWritable() || !this.waitingWriters.peek().equals(writer)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.writer = this.waitingWriters.poll();
    }

    public synchronized void stopWriting(Redacteur writer) {
        if(this.writer == writer) {
            this.writer = null;
        }
        this.notifyAll();
    }

    public synchronized void startReading(Lecteur reader) {
        this.waitingReaders.add(reader);
        while (!this.isReadable() || !this.waitingReaders.peek().equals(reader))
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        this.readers.add(this.waitingReaders.poll());
        this.notifyAll();
    }

    public synchronized void stopReading(Lecteur reader) {
        this.readers.remove(reader);
        this.notifyAll();
    }
}