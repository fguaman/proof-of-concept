package cl.guaman.threads.case_iii;

/**
 * CounterSync is a thread-safe class that provides synchronized methods
 * to increment and retrieve the value of a shared counter.
 *
 * @author fguaman
 */
public class CounterSync {
    private int counter = 0;

    /**
     * Default constructor for CounterSync.
     */
    public CounterSync() {
        // do nothing
    }

    /**
     * Increments the counter in a thread-safe manner.
     */
    public synchronized void increment() {
        counter++;
    }

    /**
     * Retrieves the current value of the counter in a thread-safe manner.
     *
     * @return the current value of the counter.
     */
    public synchronized int getCounter() {
        return counter;
    }
}
