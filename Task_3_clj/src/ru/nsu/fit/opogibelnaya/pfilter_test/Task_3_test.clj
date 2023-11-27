(ns ru.nsu.fit.opogibelnaya.pfilter_test.Task_3_test
  (:use ru.nsu.fit.opogibelnaya.pfilter.Task_3)
  (:require [clojure.test :refer :all]))


(deftest check_number
  (is (= '[0, 2, 4, 6] (p-filter even? '[0, 1, 2, 3, 4, 5, 6, 7])))
  (is (= '[] (p-filter even? '[])))
  (is (= 50 (count (p-filter even? (range 0 100))))))


(defn condition [a]
  (Thread/sleep 10)
  (even? a))


(deftest check_time
  (time (println (filter condition (take 100 (iterate inc 1)))))
  (time (println (p-filter condition (take 100 (iterate inc 1))))))





