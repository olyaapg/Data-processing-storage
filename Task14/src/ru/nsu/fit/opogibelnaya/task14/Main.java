package ru.nsu.fit.opogibelnaya.task14;

public class Main {

  private static final Detail detailA = new Detail();
  private static final Detail detailB = new Detail();
  private static final Detail detailC = new Detail();
  private static final Detail module = new Detail();

  public static void main(String[] args) {
    Thread produceA = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(1000);
          System.out.println("Detail A was produced");
          detailA.release();
        } catch (InterruptedException e) {
          return;
        }
      }
    });

    Thread produceB = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(2000);
          System.out.println("Detail B was produced");
          detailB.release();
        } catch (InterruptedException e) {
          return;
        }
      }
    });

    Thread produceC = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(3000);
          System.out.println("Detail C was produced");
          detailC.release();
        } catch (InterruptedException e) {
          return;
        }
      }
    });

    Thread produceModule = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          detailA.acquire();
          detailB.acquire();
          System.out.println("Module was produced");
          module.release();
        } catch (InterruptedException e) {
          return;
        }
      }
    });

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      produceA.interrupt();
      produceB.interrupt();
      produceC.interrupt();
      produceModule.interrupt();
      try {
        produceA.join();
        produceB.join();
        produceC.join();
        produceModule.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(detailA.availablePermits());
      System.out.println(detailB.availablePermits());
      System.out.println(detailC.availablePermits());
      System.out.println(module.availablePermits());
    }));

    produceA.start();
    produceB.start();
    produceC.start();
    produceModule.start();

    while (!Thread.currentThread().isInterrupted()) {
      try {
        module.acquire();
        detailC.acquire();
      } catch (InterruptedException e) {
        return;
      }
      System.out.println("Widget was produced");
    }
  }
}