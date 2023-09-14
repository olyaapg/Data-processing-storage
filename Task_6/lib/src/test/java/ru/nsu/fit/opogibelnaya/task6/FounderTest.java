package ru.nsu.fit.opogibelnaya.task6;

import org.junit.jupiter.api.Test;

class FounderTest {
    @Test
    void test() {
        Founder founder = new Founder(new Company(4));
        founder.start();
    }
}
