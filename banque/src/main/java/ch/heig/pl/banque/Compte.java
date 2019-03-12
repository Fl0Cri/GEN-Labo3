package ch.heig.pl.banque;

public class Compte {
    private int numero;
    private int montant;

    public Compte(int numero, int montant) {
        this.numero = numero;
        this.montant = montant;
    }

    public int getNumero() {
        return numero;
    }

    public int getMontant() {
        return montant;
    }

    public boolean debit(int valeur) {
        if (montant - valeur >= 0) {
            //this.montant -= valeur;

            int amount = this.montant;

            try {
                Thread.sleep(2);
            } catch (InterruptedException ignored) {
            }

            this.montant = amount - valeur;

            return true;
        } else {
            return false;
        }
    }

    public void credit(int valeur) {
        //this.montant += valeur;

        int amount = this.montant;

        try {
            Thread.sleep(2);
        } catch (InterruptedException ignored) {

        }

        this.montant = amount + valeur;
        System.out.println("Credited " + valeur + " to " + numero);
    }

}