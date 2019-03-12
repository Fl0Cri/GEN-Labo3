package ch.heig.crisinel_mayo.banque;

import ch.heig.pl.banque.Banque;
import ch.heig.pl.banque.Transferts;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransfertsTest
{
    @Test
    void TestConcurrentTransfers()
    {
        Banque banque = new Banque(10);

        for (int i = 0; i < 10000; i++)
        {
            Transferts transfert = new Transferts(banque, 1000);
            transfert.start();

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ignored) { }

        System.out.println("Assert NOW");
        assertTrue(banque.consistent());
    }
}