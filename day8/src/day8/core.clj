(ns day8.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn filter-fn [s]
  (loop [st s res []]
    (if (empty? st) res
      (let [[adds r] (condp = (take 2 st)
                        [\\ \\] [\\ (drop 2 st)]
                        [\\ \"] [\" (drop 2 st)]
                        [\\ \x] [\x (drop 4 st)]
                        [(first st) (rest st)])]
        (recur r (conj res adds))))))

(defn encode-fn [s]
  (loop [st s res []]
    (if (empty? st) res
      (let [adds (condp = (first st)
                    \\ [\\ \\]
                    \" [\\ \"]
                    [(first st)])]
        (recur (rest st) (apply (partial conj res) adds))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [inputs (str/split (slurp "input.txt") #"\r\n")
        filterinputs (map filter-fn inputs)
        encodedinputs (map encode-fn inputs)
        len (reduce + (map count inputs))
        len2 (reduce + (map #(- (count %) 2) filterinputs))
        len3 (reduce + (map #(+ (count %) 2) encodedinputs))]
    (println len len2 (- len len2))
    (println len3 len (- len3 len))))
