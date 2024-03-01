(ns ru.nsu.fit.opogibelnaya.philosophers.Task_5)

(def nTransactions (atom 0))

(defn action [nTimes nThinking nEating leftFork rightFork]
  (doseq [_ (range 0 nTimes)]
    (Thread/sleep nThinking)
    (dosync [(alter leftFork inc)
             (alter rightFork inc)
             (Thread/sleep nEating)
             (swap! nTransactions inc)
             ])))

; nPhilosophers, nThinking, nEating, nTimes
(defn main [nPhilosophers nTimes nThinking nEating]
  (let [forks (vec (for [_ (range nPhilosophers)]
                     (ref 0))),
        philosophers (conj (vec (for [i (range 1 nPhilosophers)]
                                  (new Thread #(action nTimes nThinking nEating (nth forks i) (nth forks (- i 1)))))) (new Thread #(action nTimes nThinking nEating (nth forks 0) (last forks))))
        ]
    (doseq [p philosophers]
      (.start p))
    (doseq [p philosophers]
      (.join p))
    (println "Количество транзакций: " @nTransactions)
    (reset! nTransactions 0)
    ))