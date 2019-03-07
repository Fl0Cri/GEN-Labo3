package ch.heig.pl.banque;

import java.util.LinkedList;
import java.util.Random;

public class Transferts implements Runnable
{
    private Banque banque;
    private Random randomGenerator;

    public static void executeRandom(Banque banque, int nombre)
    {
        System.out.println("### Starting transfers");

        // Démare les transferts
        for (int i = 0; i < nombre; i++)
        {
            new Thread(new Transferts(banque)).start();
        }

        System.out.println("### Finished");
    }

    private Transferts(Banque banque)
    {
        this.banque = banque;
        randomGenerator = new Random();
    }

    public void run()
    {
        int nbComptes = banque.getNbComptes();
        int crediteur = randomGenerator.nextInt(nbComptes);
        int debiteur = randomGenerator.nextInt(nbComptes);
        int montant = randomGenerator.nextInt(4) + 1;

        System.out.println("Transfering " + montant + " from " + crediteur + " to " + debiteur);

        banque.transfert(debiteur, crediteur, montant);
    }
}
