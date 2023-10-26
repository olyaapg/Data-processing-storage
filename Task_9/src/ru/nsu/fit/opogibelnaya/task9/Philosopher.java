package ru.nsu.fit.opogibelnaya.task9;

public class Philosopher extends Thread {

  private final Spaghetti spaghetti;
  private final Object leftFork;
  private final Object rightFork;
  private final int timeSleep;
  private final int countEat;
  private static int count = 0;

  public Philosopher(Spaghetti spaghetti, Object leftFork, Object rightFork, int timeSleep, int countEat) {
    this.spaghetti = spaghetti;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
    this.timeSleep = timeSleep;
    this.countEat = countEat;
  }

  private static synchronized void setCount() {
    count++;
  }

  public static int getCount() {
    return count;
  }

  @Override
  public void run() {
    while (spaghetti.anySpaghetti()) {
      try {
        Thread.sleep(timeSleep);
        synchronized (leftFork) {
          synchronized (rightFork) {
            if (spaghetti.eatSpaghetti(countEat)) {
              Thread.sleep(countEat);
              setCount();
            } else {
              return;
            }
          }
        }
      } catch (InterruptedException e) {
        throw new RuntimeException();
      }
    }
  }
}
