package cl.guaman.threads.case_iii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit tests for the {@link CounterWithLock} class.
 * <p>
 * This class tests the thread-safety and functionality of the {@link CounterWithLock} implementation
 * by running multiple threads concurrently and verifying the final counter value.
 * </p>
 *
 * @author fguaman
 */
class CounterWithLockTest {
    private static final Logger logger = Logger.getLogger(CounterWithLock.class.getName());

    /**
     * Tests the {@link CounterWithLock} to ensure that it handles concurrent increments correctly.
     * <p>
     * This test creates two threads that increment the counter 1000 times each. It waits for
     * both threads to finish and verifies that the final counter value is 2000, ensuring
     * that no increments were lost due to concurrency issues.
     * </p>
     *
     * @throws InterruptedException if the thread execution is interrupted while waiting for completion
     */
    @Test
    void executeLock() throws InterruptedException {
        CounterWithLock counter = new CounterWithLock();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        logger.log(Level.INFO, "counter = {0}", counter.getCount());

        Assertions.assertEquals(2000, counter.getCount(), "The counter value should be 2000 after both threads complete");
    }
}