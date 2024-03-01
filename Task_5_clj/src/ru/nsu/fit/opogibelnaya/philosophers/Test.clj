(ns ru.nsu.fit.opogibelnaya.philosophers.Test
  (:use ru.nsu.fit.opogibelnaya.philosophers.Task_5)
  (:require [clojure.test :refer :all]))


(deftest check-time-odd
  (println "Запуск №1.1")
  (time (main 11 10 100 10))
  (println "Запуск №2.1")
  (time (main 11 10 100 10))
  (println "Запуск №3.1")
  (time (main 11 10 100 10)))


(deftest check-time-even
  (println "-------------------------------------")
  (println "Запуск №1.2")
  (time (main 10 10 100 10))
  (println "Запуск №2.2")
  (time (main 10 10 100 10))
  (println "Запуск №3.2")
  (time (main 10 10 100 10)))


;Testing started at 13:25 ...
;
;Testing ru.nsu.fit.opogibelnaya.philosophers.Test
;Запуск №1.1
;Количество транзакций:  113
;"Elapsed time: 1307.9227 msecs"
;Запуск №2.1
;Количество транзакций:  110
;"Elapsed time: 1282.4172 msecs"
;Запуск №3.1
;Количество транзакций:  111
;"Elapsed time: 1289.1996 msecs"
;-------------------------------------
;Запуск №1.2
;Количество транзакций:  100
;"Elapsed time: 1273.5642 msecs"
;Запуск №2.2
;Количество транзакций:  102
;"Elapsed time: 1293.8466 msecs"
;Запуск №3.2
;Количество транзакций:  101
;"Elapsed time: 1289.3882 msecs"
;
;Process finished with exit code 0