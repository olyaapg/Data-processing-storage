package ru.nsu.fit.opogibelnaya.task14;

public class Main {

  private static final Detail detailA = new Detail();
  private static final Detail detailB = new Detail();
  private static final Detail detailC = new Detail();
  private static final Detail module = new Detail();
  private static int statisticA = 0;
  private static int statisticB = 0;
  private static int statisticC = 0;
  private static int statisticModule = 0;
  private static int statisticWidget = 0;

  public static void main(String[] args) {
    Thread produceA = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
        try {
          System.out.println("Detail A was produced");
          detailA.release();
        } catch (InterruptedException e) {
          return;
        } finally {
          statisticA++;
        }
      }
    });

    Thread produceB = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          return;
        }
        try {
          System.out.println("Detail B was produced");
          detailB.release();
        } catch (InterruptedException e) {
          return;
        } finally {
          statisticB++;
        }
      }
    });

    Thread produceC = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          return;
        }
        try {
          System.out.println("Detail C was produced");
          detailC.release();
        } catch (InterruptedException e) {
          return;
        } finally {
          statisticC++;
        }
      }
    });

    Thread produceModule = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          detailA.acquire();
          detailB.acquire();
        } catch (InterruptedException e) {
          return;
        }
        try {
          System.out.println("Module was produced");
          module.release();
        } catch (InterruptedException e) {
          return;
        } finally {
          statisticModule++;
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
      System.out.println("A details produced: " + statisticA);
      System.out.println("B details produced: " + statisticB);
      System.out.println("C details produced: " + statisticC);
      System.out.println("Modules produced: " + statisticModule);
      System.out.println("Widgets produced: " + statisticWidget);
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
      statisticWidget++;
    }
  }
}