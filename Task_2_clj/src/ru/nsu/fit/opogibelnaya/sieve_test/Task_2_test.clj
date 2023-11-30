(ns ru.nsu.fit.opogibelnaya.sieve-test.Task_2_test
  (:use ru.nsu.fit.opogibelnaya.sieve.Task_2)
  (:require [clojure.test :refer :all]))


(deftest check_number
  (is (= 3 (nth sieve_Eratosthenes 1)))
  (is (= 29 (nth sieve_Eratosthenes 9)))
  (is (= 7001 (nth sieve_Eratosthenes 900))))


(deftest check_numbers
  (is (= 3 (nth sieve_Eratosthenes 1)))
  (is (= [2, 3, 5, 7, 11, 13, 17, 19, 23, 29] (take 10 sieve_Eratosthenes))))


(deftest check_time
  (time (nth sieve_Eratosthenes 1000))
  (time (nth sieve_Eratosthenes 1000))
  (time (nth sieve_Eratosthenes 1000))
  (time (nth sieve_Eratosthenes 1000))
  )