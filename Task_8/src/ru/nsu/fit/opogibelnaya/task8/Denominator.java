package ru.nsu.fit.opogibelnaya.task8;

class Denominator {

  private static int k = 1;
  private static int forOne = 1;

  static void setK(int k) {
    Denominator.k = k;
  }

  static void setForOne(int forOne) {
    Denominator.forOne = forOne;
  }

  static int nextDenominator(int denominator, int n) {
    if (denominator > 0) {
      return (denominator + forOne * 2 * n) * k;
    } else {
      return (denominator - forOne * 2 * n) * k;
    }
  }
}
