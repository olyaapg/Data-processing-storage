package ru.nsu.fit.opogibelnaya.task3;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    private final List<List<String>> list;
    public Printer(List<List<String>> list) {
        this.list = list;
    }

    public void print() {
        List<MyThread> threads = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            threads.add(new MyThread(list.get(i)));
        }
        for (MyThread t : threads) {
            t.start();
        }
        for (MyThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
