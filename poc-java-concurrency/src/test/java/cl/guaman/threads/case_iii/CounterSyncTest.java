package cl.guaman.threads.case_iii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for CounterSync to verify thread-safe increment functionality.
 *
 * @author fguaman
 */
class CounterSyncTest {

    /**
     * Logger instance to log test results and information.
     */
    private static final Logger logger = Logger.getLogger(CounterSyncTest.class.getName());

    /**
     * Executes a multi-threaded test to increment a shared counter using CounterSync.
     * Ensures the counter is incremented accurately in a thread-safe manner.
     *
     * @throws InterruptedException if the thread is interrupted while waiting for tasks to complete.
     */
    @Test
    void executeIncrementWithThreads() throws InterruptedException {
        CounterSync counterSync = new CounterSync();
        int numberOfThreads = 10;
        int incrementsPerThread = 1000;

        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counterSync.increment();
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        Assertions.assertEquals(numberOfThreads * incrementsPerThread, counterSync.getCounter());
        logger.log(Level.INFO, "counter = {0}", numberOfThreads * incrementsPerThread);
    }
}