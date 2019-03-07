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
        Transferts.executeRandom(banque, 1000);

        assertTrue(banque.consistent());
    }
}