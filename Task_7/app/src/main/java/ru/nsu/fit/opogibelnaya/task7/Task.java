package ru.nsu.fit.opogibelnaya.task7;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

    private int denominator;
    private final int nNumbers;

    Task(int denominator, int nNumbers) {
        this.denominator = denominator;
        this.nNumbers = nNumbers;
    }

    @Override
    public Double call() {
        double sum = 0;
        for (int i = 0; i < nNumbers; i++) {
            sum += (double) 1 / denominator;
            if ((denominator > 0)) {
                denominator += 2;
            } else {
                denominator -= 2;
            }
            denominator *= -1;
        }
        return sum;
    }
}
