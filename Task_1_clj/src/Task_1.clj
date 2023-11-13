(defn delete-unsuitable [ch list]
  (filter (fn [x] (not (= ch x))) list))

;(delete-unsuitable "a" '("a", "b", "c"))
;=> ("b" "c")


(defn add-tail [head list]
  (map (fn [sub-lust] (str head sub-lust)) list))

;(add-tail "ba" '("b", "c", "d"))
;=> ("bab" "bac" "bad")


(defn add [head list]
  (add-tail head (delete-unsuitable (str (last head)) list)))

;(add "ba" '("a", "b"))
;=> ("bab")


(defn find-strings-from-2-lists [list-1 list-2]
  (apply concat (map (fn [x] (add x list-2)) list-1)))

;(find-strings-from-2-lists '("a", "b") '("a", "b"))
;=> ("ab" "ba")


(defn main [N, alphabet]
  (reduce (fn [list _] (find-strings-from-2-lists list alphabet)) (list "") (range 0 N)))

(println (main 3 '("a", "b", "c")))
