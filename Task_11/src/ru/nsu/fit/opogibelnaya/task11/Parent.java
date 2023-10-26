package ru.nsu.fit.opogibelnaya.task11;

import java.util.concurrent.Semaphore;

public class Parent extends Thread {

  private final Thread myChild;
  private final Semaphore semPrint;
  private final Semaphore semWait;

  public Parent() {
    semPrint = new Semaphore(1, true);
    semWait = new Semaphore(1, true);
    myChild = new Thread(() -> {
      try {
        for (int i = 0; i < 5; i++) {
          semWait.acquire();
          semPrint.acquire();
          semWait.release();
          System.out.println("I'm a child!");
          semPrint.release();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void run() {
    try {
      semPrint.acquire();
      myChild.start();
      System.out.println("I'm a parent!");
      semPrint.release();
      for (int i = 0; i < 4; i++) {
        semWait.acquire();
        semPrint.acquire();
        semWait.release();
        System.out.println("I'm a parent!");
        semPrint.release();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}