package ru.nsu.fit.opogibelnaya.task14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Detail {

  private final Semaphore detail;
  private final ReentrantLock lock;
  private final Condition tooManyDetails;
  private final int maxNDetails;

  public Detail() {
    detail = new Semaphore(0);
    lock = new ReentrantLock(true);
    tooManyDetails = lock.newCondition();
    maxNDetails = 1;
  }

  public void acquire() throws InterruptedException {
    detail.acquire();
    lock.lock();
    try {
      tooManyDetails.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public void release() throws InterruptedException {
    lock.lock();
    try {
      while (detail.availablePermits() == maxNDetails) {
        tooManyDetails.await();
      }
    } finally {
      lock.unlock();
    }
    detail.release();
  }

  public int availablePermits() {
    return detail.availablePermits();
  }
}
