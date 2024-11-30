package cl.guaman.threads.case_iii;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe counter implementation using a {@link Lock} to synchronize access.
 * <p>
 * This class ensures that incrementing and retrieving the count value are thread-safe
 * by using a {@link ReentrantLock} to prevent concurrent access issues.
 * </p>
 *
 * @author fguaman
 */
class CounterWithLock {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    /**
     * Increments the counter by one in a thread-safe manner.
     * <p>
     * This method uses a {@link Lock} to ensure that only one thread can
     * increment the counter at a time.
     * </p>
     */
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves the current value of the counter in a thread-safe manner.
     * <p>
     * This method uses a {@link Lock} to ensure that the value of the counter
     * is read atomically, preventing visibility issues in a multi-threaded environment.
     * </p>
     *
     * @return the current value of the counter
     */
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
