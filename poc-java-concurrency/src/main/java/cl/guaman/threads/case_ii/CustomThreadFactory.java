package cl.guaman.threads.case_ii;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * A custom thread factory that provides named threads and maintains statistics
 * about the threads it creates.
 *
 * @author fguaman
 */
public class CustomThreadFactory implements ThreadFactory {

    /**
     * Counter to keep track of the number of threads created.
     */
    private int counter;

    /**
     * The name prefix for threads created by this factory.
     */
    private final String name;

    /**
     * A list to store statistics about the threads created.
     */
    private final List<String> stats;

    /**
     * Constructs a new CustomThreadFactory with a specified name prefix.
     *
     * @param name the name prefix for threads created by this factory
     */
    public CustomThreadFactory(String name) {
        counter = 0;
        this.name = name;
        stats = new ArrayList<>();
    }

    /**
     * Creates a new thread with a custom name based on the factory's name prefix
     * and an incremental counter. Also records the creation time and thread details
     * for statistical purposes.
     *
     * @param r the runnable task to be executed by the new thread
     * @return the newly created thread
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-thread-" + counter);
        counter++;
        stats.add("new thread " + t.getId() + " - " + t.getName() + " - " + new Date());
        return t;
    }

    /**
     * Retrieves the statistics of all threads created by this factory.
     *
     * @return a string containing statistics for each created thread
     */
    public String getStats() {
        StringBuilder buffer = new StringBuilder();
        for (String stat : stats) {
            buffer.append(stat);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * Get counter thread's
     *
     * @return counter
     */
    public int getCounter() {
        return counter;
    }
}