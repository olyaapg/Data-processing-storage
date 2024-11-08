/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.fit.opogibelnaya.task5;

import static java.lang.Thread.sleep;

public class Parent {
    public void haveChild() {
        Child child = new Child();
        child.start();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        child.interrupt();
        try {
            child.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
