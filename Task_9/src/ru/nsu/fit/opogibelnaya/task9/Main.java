package ru.nsu.fit.opogibelnaya.task9;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    int nPhilosophers = 5;
    int weightSpaghetti = 20000;
    int timeSleep = 100;
    int countEat = 500;
    Spaghetti spaghetti = new Spaghetti(weightSpaghetti);

    List<Object> forks = new ArrayList<>(nPhilosophers);
    for (int i = 0; i < nPhilosophers; i++) {
      forks.add(i, new Object());
    }

    List<Philosopher> philosophers = new ArrayList<>(nPhilosophers);
    philosophers.add(0,
        new Philosopher(spaghetti, forks.get(0), forks.get(nPhilosophers - 1), timeSleep,
            countEat));
    //philosophers.get(0).setName("Philosopher 1");
    for (int i = 1; i < nPhilosophers; i++) {
      philosophers.add(i,
          new Philosopher(spaghetti, forks.get(i), forks.get(i - 1), timeSleep, countEat));
      //philosophers.get(i).setName("Philosopher " + (i + 1));
    }

    for (Philosopher philosopher : philosophers) {
      philosopher.start();
    }
    for (Philosopher philosopher : philosophers) {
      try {
        philosopher.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println(Philosopher.getCount());
  }
}