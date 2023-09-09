package ru.nsu.fit.opogibelnaya.task3;

import java.util.List;

public class MyThread extends Thread {
    private final List<String> toPrint;

    MyThread(List<String> str) {
        toPrint = str;
    }

    private void print(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Override
    public void run() {
        print(toPrint);
    }
}
