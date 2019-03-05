package ch.heig.pl.lecteursredacteurs;

public class Controleur {
    private boolean writed;

    public Controleur(){
        this.writed = false;
    }

    public boolean isWrited() {
        return this.writed;
    }

    public void startWriting() {
        this.writed = true;
    }

    public void stopWriting() {
        this.writed = false;
    }
}
