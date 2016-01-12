(ns day10.core
  (:gen-class))

(defn get-prefix [s]
  (loop [cnt 1 chr (first s) st (rest s)]
    (if-not (= chr (first st))
      [cnt chr st]
      (recur (inc cnt) chr (rest st)))))

(defn look-and-say [s]
  (loop [st s res ""]
    (if (empty? st)
      res
      (let [[cnt chr r] (get-prefix st)]
        (recur r (str res cnt chr))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (count (nth (iterate look-and-say "1113222113") 40)))
  (println (count (nth (iterate look-and-say "1113222113") 50))))
