
package cl.guaman.threads.case_i;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for ArraySum to calculate the sum of an integer array using multiple threads.
 *
 * @author fguaman
 */
class ArraySumTest {
    private static final Logger logger = Logger.getLogger(ArraySumTest.class.getName());

    /**
     * Test method to verify the total sum calculated by ArraySum using multiple threads.
     *
     * @throws InterruptedException if any thread is interrupted while waiting.
     */
    @Test
    void executeTotalSum() throws InterruptedException {
        ArraySum arraySum = new ArraySum();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int totalSum = arraySum.calculateTotalSum(array);

        Assertions.assertEquals(55, totalSum);
        logger.log(Level.INFO, "the result is totalSum = {0}", totalSum);
    }
}