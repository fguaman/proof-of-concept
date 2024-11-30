package cl.guaman.threads.case_ii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for demonstrating the use of a custom thread factory.
 * This class creates multiple threads using a {@link CustomThreadFactory}
 * and executes a shared task across them.
 * It also logs statistics from the thread factory after execution.
 */
class CustomThreadTest {

    /**
     * Logger for logging messages during the execution of threads.
     */
    private static final Logger logger = Logger.getLogger(CustomThreadTest.class.getName());

    /**
     * Test method that demonstrates the creation and execution of threads
     * using a custom thread factory. It creates 10 threads, each executing
     * the same {@link Task}, and logs the factory's statistics.
     */
    @Test
    void executeThreads() {
        CustomThreadFactory factory = new CustomThreadFactory("thread-factory");

        Task task = new Task();

        Thread thread;

        logger.log(Level.INFO, "start thread's");

        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }

        logger.log(Level.INFO, "factory stats ={0}", factory.getStats());
        Assertions.assertEquals(10, factory.getCounter());
    }

    /**
     * Tests the Task's handling of an InterruptedException.
     * Verifies that the exception is logged and the thread's interrupt status is set.
     */
    @Test
    void executeTaskInterruptedException() throws InterruptedException {
        Task task = new Task();
        Thread taskThread = new Thread(task);
        taskThread.start();

        Thread.sleep(100);
        taskThread.interrupt();
        taskThread.join();
    }
}
