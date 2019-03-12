package ch.heig.pl.banque;

import java.util.Random;

public class Transferts extends Thread
{
    private Banque banque;
    private Random randomGenerator;
    private int nombre;

    public Transferts(Banque banque, int nombre)
    {
        this.banque = banque;
        this.nombre = nombre;
        randomGenerator = new Random();
    }

    public void run()
    {
        int nbComptes = banque.getNbComptes();
        int crediteur;
        int debiteur;
        int montant;

        for (int i = 0; i < this.nombre; i++)
        {
            crediteur = randomGenerator.nextInt(nbComptes);
            debiteur = randomGenerator.nextInt(nbComptes - 1);
            debiteur = debiteur >= crediteur ? debiteur + 1 : debiteur;
            montant = randomGenerator.nextInt(4) + 1;

            banque.transfert(debiteur, crediteur, montant);
        }
    }
}
