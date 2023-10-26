package ru.nsu.fit.opogibelnaya.task9;

public class Spaghetti {

  private int spaghetti;

  public Spaghetti(int weight) {
    spaghetti = weight;
  }

  public synchronized boolean anySpaghetti() {
    return spaghetti > 0;
  }

  public synchronized boolean eatSpaghetti(int spaghetti) {
    if (this.spaghetti > 0) {
      this.spaghetti -= spaghetti;
      return true;
    } else {
      return false;
    }
  }
}
