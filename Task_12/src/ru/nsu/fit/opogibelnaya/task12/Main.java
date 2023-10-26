package ru.nsu.fit.opogibelnaya.task12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

  private static final LinkedList<String> list = new LinkedList<>();
  private static final String monitor = "";

  private static void printList() {
    synchronized (list.getLast()) {
      for (int i = list.size() - 2; i > -1; i--) {
        System.out.println(list.get(i));
      }
    }
  }

  private static void addToList(List<String> newList) {
    synchronized (list.getLast()) {
      list.addAll(0, newList);
    }
  }

  private static void addToList(String newLine) {
    synchronized (list.getLast()) {
      list.addFirst(newLine);
    }
  }

  private static boolean processString(String newLine) {
    if (newLine == null) {
      return false;
    }
    int len = newLine.length();
    if (len == 0) {
      printList();
    } else if (len > 80) {
      LinkedList<String> newList = new LinkedList<>();
      for (int i = 0; i < len; i += 80) {
        int endIndex = Math.min(i + 80, len);
        newList.addFirst(newLine.substring(i, endIndex));
      }
      addToList(newList);
    } else {
      addToList(newLine);
    }
    return true;
  }

  private static Thread getThread() {
    Thread daughter = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          return;
        }
        synchronized (list.getLast()) {
          int len = list.size();
          for (int i = 0; i < len - 2; i++) {
            for (int j = 0; j < len - i - 2; j++) {
              if (list.get(j).compareTo(list.get(j + 1)) < 0) {
                String s = list.get(j + 1);
                list.set(j + 1, list.get(j));
                list.set(j, s);
              }
            }
          }
        }
      }
    });
    daughter.start();
    return daughter;
  }

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line;
    list.add(monitor);
    Thread daughter = getThread();
    try {
      while (!Thread.currentThread().isInterrupted()) {
        line = reader.readLine();
        if (!processString(line)) {
          return;
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        reader.close();
        daughter.interrupt();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}