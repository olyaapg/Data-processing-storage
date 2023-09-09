package ru.nsu.fit.opogibelnaya.task4;

public class Child extends Thread {
    private void print() throws InterruptedException {
        System.out.println("Alohomora!");
        sleep(100);
    }

    @Override
    public void run() {
        System.out.println("The child was launched...");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                print();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
