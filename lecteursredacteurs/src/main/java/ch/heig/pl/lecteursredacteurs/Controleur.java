package ch.heig.pl.lecteursredacteurs;

import java.util.*;

public class Controleur {
    private Object writer = null;
    private Set<Object> readers = new HashSet<Object>();
    private Queue<Object> waitingWriters = new PriorityQueue<Object>();
    private Queue<Object> waitingReaders = new PriorityQueue<Object>();

    public boolean isWritable() {
        return this.writer == null && this.readers.isEmpty();
    }

    public boolean isReadable() {
        return this.writer == null && waitingWriters.isEmpty();
    }

    public synchronized void startWriting(Object o) {
        this.waitingWriters.add(o);
        while (!this.isWritable() || !this.waitingWriters.peek().equals(o)) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.writer = this.waitingWriters.poll();
    }

    public synchronized void stopWriting(Object o) {
        if(this.writer == o) {
            this.writer = false;
        }
        this.notifyAll();
    }

    public synchronized void startReading(Object o) {
        this.waitingReaders.add(o);
        while (!this.isReadable() || !this.waitingReaders.peek().equals(o))
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

    public synchronized void stopReading(Object o) {
        this.readers.remove(o);
        this.notifyAll();
    }
}
