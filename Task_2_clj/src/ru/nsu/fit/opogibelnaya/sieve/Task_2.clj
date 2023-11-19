(ns ru.nsu.fit.opogibelnaya.sieve.Task_2)


(defn filter-divisible [divisor some-seq]
  (filter (fn [x] (not= 0 (mod x divisor))) some-seq))

;(filter-divisible 2 [0, 1, 2, 3, 4])
;=> (1 3)


(defn func [some-seq]
  (lazy-cat
    (conj [] (first some-seq))
    (func (lazy-seq (filter-divisible (first some-seq) some-seq)))))


(def sieve_Eratosthenes (func (iterate inc 2)))