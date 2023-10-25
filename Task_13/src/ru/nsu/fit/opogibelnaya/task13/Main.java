package ru.nsu.fit.opogibelnaya.task13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static void main(String[] args) {
    int nPhilosophers = 5;
    int weightSpaghetti = 5000;
    int timeSleep = 100;
    int countEat = 500;
    Spaghetti spaghetti = new Spaghetti(weightSpaghetti);
    ReentrantLock forks = new ReentrantLock();
    Condition condition = forks.newCondition();

    List<ReentrantLock> allForks = new ArrayList<>(nPhilosophers);
    for (int i = 0; i < nPhilosophers; i++) {
      allForks.add(i, new ReentrantLock());
    }

    List<Philosopher> philosophers = new ArrayList<>(nPhilosophers);
    philosophers.add(0,
        new Philosopher(spaghetti, allForks.get(0), allForks.get(nPhilosophers - 1), timeSleep,
            countEat, forks, condition));
    philosophers.get(0).setName("Philosopher 1");
    for (int i = 1; i < nPhilosophers; i++) {
      philosophers.add(i,
          new Philosopher(spaghetti, allForks.get(i), allForks.get(i - 1), timeSleep, countEat,
              forks, condition));
      philosophers.get(i).setName("Philosopher " + (i + 1));
    }

    for (Philosopher philosopher : philosophers) {
      philosopher.start();
    }
  }
}