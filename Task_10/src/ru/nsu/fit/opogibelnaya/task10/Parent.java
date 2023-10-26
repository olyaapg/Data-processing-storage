package ru.nsu.fit.opogibelnaya.task10;

public class Parent extends Thread {

  private final Thread myChild;
  private final Object monitor;
  private boolean flag;

  public Parent() {
    monitor = new Object();
    myChild = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        while (!flag) {
          synchronized (monitor) {
            try {
              monitor.wait();
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }
        }
        System.out.println("I'm a child!");
        flag = false;
        synchronized (monitor) {
          monitor.notify();
        }
      }
    });
  }

  public void run() {
    flag = false;
    myChild.start();
    for (int i = 0; i < 5; i++) {
      System.out.println("I'm a parent!");
      flag = true;
      synchronized (monitor) {
        monitor.notify();
      }
      while (flag) {
        synchronized (monitor) {
          try {
            monitor.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
  }
}