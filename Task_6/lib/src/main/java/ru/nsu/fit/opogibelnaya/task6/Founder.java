package ru.nsu.fit.opogibelnaya.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Runnable> workers;
    private final List<Department> departments;
    private CyclicBarrier barrier;
    private final Company company;

    public Founder(final Company company) {
        this.company = company;
        int count = company.getDepartmentsCount();
        this.workers = new ArrayList<>(count);
        departments = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            departments.add(company.getFreeDepartment(i));
        }
    }

    private void createWorkers() {
        for (Department department : departments) {
            workers.add(() -> {
                department.performCalculations();
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void start() {
        barrier = new CyclicBarrier(departments.size() + 1, company::showCollaborativeResult);
        createWorkers();
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}