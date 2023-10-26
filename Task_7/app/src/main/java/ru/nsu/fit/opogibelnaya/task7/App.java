/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.fit.opogibelnaya.task7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {

    private static List<Task> createTasks(int nThreads, int nIterations) {
        int forOne = nIterations / nThreads;
        List<Task> taskList = new ArrayList<>();
        int denominator = 1, k;
        if (forOne % 2 == 0) {
            k = 1;
        } else {
            k = -1;
        }
        for (int i = 0; i < nThreads; i++) {
            if (i == nThreads - 1) {
                forOne = nIterations - i * forOne;
            }
            taskList.add(new Task(denominator, forOne));
            if ((denominator > 0)) {
                denominator = (denominator + forOne * 2) * k;
            } else {
                denominator = (denominator - forOne * 2) * k;
            }
        }
        return taskList;
    }

    public static void main(String[] args) {
        int nThreads = Integer.decode(args[0]);
        int nIterations = Integer.decode(args[1]);

        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
        List<Future<Double>> result;
        System.out.println("Let's start...");
        try {
            result = threadPool.invokeAll(createTasks(nThreads, nIterations));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pi/4 = " + result.stream().map(x -> {
            try {
                return x.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).reduce((double) 0, Double::sum));
        threadPool.shutdown();
    }
}