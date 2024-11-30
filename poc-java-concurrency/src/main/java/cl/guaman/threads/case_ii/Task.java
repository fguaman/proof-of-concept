package cl.guaman.threads.case_ii;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A task to be executed by threads created using a thread factory.
 * The task simulates work by sleeping for 1 second and handles interruptions gracefully.
 *
 * @author fguaman
 */
public class Task implements Runnable {

    /**
     * Logger for recording messages about the execution of the task.
     */
    private static final Logger logger = Logger.getLogger(Task.class.getName());

    /**
     * Executes the task. Simulates work by sleeping for 1 second.
     * If interrupted, logs the exception with a severity level.
     */
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Task | run | InterruptedException | message={0}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
