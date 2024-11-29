package cl.guaman.simple.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class ArraySum {

    public ArraySum() {
        // do nothing
    }

    public int calculateTotalSum(int[] array) throws InterruptedException {

        // Using AtomicInteger to ensure thread-safe updates to totalSum
        AtomicInteger totalSum = new AtomicInteger(0);

        // First thread to sum the first half of the array
        Thread threadOne = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < array.length / 2; i++) {
                sum += array[i];
            }
            totalSum.addAndGet(sum);
        });

        // Second thread to sum the second half of the array
        Thread threadTwo = new Thread(() -> {
            int sum = 0;
            for (int i = array.length / 2; i < array.length; i++) {
                sum += array[i];
            }
            totalSum.addAndGet(sum);
        });

        // Start both threads
        threadOne.start();
        threadTwo.start();

        // Wait for both threads to complete
        threadOne.join();
        threadTwo.join();

        return totalSum.get();
    }
}
