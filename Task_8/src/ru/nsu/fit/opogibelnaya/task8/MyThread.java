package ru.nsu.fit.opogibelnaya.task8;

import static ru.nsu.fit.opogibelnaya.task8.Denominator.nextDenominator;

public class MyThread extends Thread {

  private static double resSum = 0;
  private static int maxDenominator = 0;
  private final int nIterations;
  private final int nThreads;
  private int myDenominator;

  MyThread(int denominator, int nIterations, int nThreads) {
    myDenominator = denominator;
    this.nIterations = nIterations;
    this.nThreads = nThreads;
    setMaxDenominator(myDenominator);
  }

  private static synchronized void setResSum(double resSum) {
    MyThread.resSum += resSum;
  }

  private static synchronized void setMaxDenominator(int denominator) {
    if (maxDenominator < denominator) {
      maxDenominator = denominator;
    }
  }

  public static double getResSum() {
    return resSum;
  }

  private double calculate(int denominator) {
    double sum = 0;
    for (int i = 0; i < nIterations; i++) {
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

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      setResSum(calculate(myDenominator));
      myDenominator = nextDenominator(myDenominator, nThreads);
      setMaxDenominator(myDenominator);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        while (myDenominator < maxDenominator) {
          setResSum(calculate(myDenominator));
          myDenominator = nextDenominator(myDenominator, nThreads);
        }
        Thread.currentThread().interrupt();
      }
    }
  }
}
