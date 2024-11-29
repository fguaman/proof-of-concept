package cl.guaman.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains a unit test for calculating the sum of an integer array
 * using multiple threads.
 *
 * @author fguaman
 */
class ArraySumTest {
    private static final Logger logger = Logger.getLogger(ArraySumTest.class.getName());

    /**
     * The array to be summed.
     */
    private final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    /**
     * The total sum calculated by both threads.
     */
    private int totalSum = 0;

    /**
     * Test method to calculate the sum of the array using two threads.
     *
     * @throws InterruptedException if any thread is interrupted while waiting.
     */
    @Test
    void executeTotal() throws InterruptedException {
        logger.log(Level.INFO, "execute total sum with two thread's");
        Thread threadOne = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 2; i++) {
                sum += array[i];
            }
            synchronized (ArraySumTest.class) {
                totalSum += sum;
            }
        });

        Thread threadTwo = new Thread(() -> {
            int sum = 0;
            for (int i = array.length / 2; i < array.length; i++) {
                sum += array[i];
            }
            synchronized (ArraySumTest.class) {
                totalSum += sum;
            }
        });

        // start both threads
        threadOne.start();
        threadTwo.start();

        // wait for both threads to complete
        threadOne.join();
        threadTwo.join();

        // verify that the total sum is correct
        Assertions.assertEquals(55, totalSum);
        logger.log(Level.INFO, "the result is totalSum={0}", totalSum);
    }
}