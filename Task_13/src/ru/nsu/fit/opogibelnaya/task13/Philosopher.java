package ru.nsu.fit.opogibelnaya.task13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {

  private final Spaghetti spaghetti;
  private final ReentrantLock leftFork;
  private final ReentrantLock rightFork;
  private final ReentrantLock forks;
  private final Condition conditionVar;
  private final int timeSleep;
  private final int countEat;

  public Philosopher(Spaghetti spaghetti, ReentrantLock leftFork, ReentrantLock rightFork,
      int timeSleep,
      int countEat, ReentrantLock forks, Condition condition) {
    this.spaghetti = spaghetti;
    this.leftFork = leftFork;
    this.rightFork = rightFork;
    this.timeSleep = timeSleep;
    this.countEat = countEat;
    this.forks = forks;
    this.conditionVar = condition;
  }


  @Override
  public void run() {
    while (spaghetti.anySpaghetti()) {
      System.out.println(Thread.currentThread().getName() + " размышляет");
      try {
        Thread.sleep(timeSleep);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      forks.lock();
      System.out.println(Thread.currentThread().getName() + " ждёт вилки");
      try {
        while (leftFork.isLocked() || rightFork.isLocked()) {
          conditionVar.await();
        }
        leftFork.lock();
        rightFork.lock();
      } catch (InterruptedException e) {
        System.out.println(
            "An error occurred while " + Thread.currentThread().getName()
                + " was waiting for forks");
      } finally {
        forks.unlock();
      }

      try {
        if (spaghetti.eatSpaghetti(countEat)) {
          System.out.println(Thread.currentThread().getName() + " ест");
          Thread.sleep(countEat);
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      forks.lock();
      System.out.println(Thread.currentThread().getName() + " положил вилки");
      try {
        leftFork.unlock();
        rightFork.unlock();
        conditionVar.signalAll();
      } finally {
        forks.unlock();
      }
    }
    System.out.println(Thread.currentThread().getName() + " ушел");
  }
}