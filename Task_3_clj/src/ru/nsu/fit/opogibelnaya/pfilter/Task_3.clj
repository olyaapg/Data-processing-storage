(ns ru.nsu.fit.opogibelnaya.pfilter.Task_3)

(defn p-filter [pred coll]
  (let [parts (partition-all 10 coll)]
    (->> parts
         (map (fn [little-chunk] (future (doall (filter pred little-chunk)))))
         (doall)
         (map deref)
         (flatten))))
