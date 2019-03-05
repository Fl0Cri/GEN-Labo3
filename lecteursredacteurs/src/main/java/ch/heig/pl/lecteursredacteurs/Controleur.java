package ch.heig.pl.lecteursredacteurs;

import java.util.HashSet;
import java.util.Set;

public class Controleur {
    private boolean writed;
    private Set<Object> readed;

    public Controleur(){
        this.writed = false;
        this.readed = new HashSet<Object>();
    }

    public boolean isWritable() {
        return !this.writed && this.readed.isEmpty();
    }

    public void startWriting() {
        this.writed = true;
    }

    public void stopWriting() {
        this.writed = false;
    }

    public boolean isReadable() {
        return !this.writed;
    }

    public void startReading(Object o) {
        this.readed.add(o);
    }

    public void stopReading(Object o) {
        this.readed.remove(o);
    }
}
