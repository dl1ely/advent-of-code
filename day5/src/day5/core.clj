(ns day5.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn three-vowels? [s]
  (let [f (frequencies s)
        vowels (select-keys f [\a \e \i \o \u])]
    (< 2 (reduce + 0 (vals vowels)))))

(defn double-letter? [s]
  (not (nil? (some (fn [[a b]] (= a b)) (partition 2 1 s)))))

(defn no-naughty? [s]
  (not (or (.contains s "ab") (.contains s "cd") (.contains s "pq") (.contains s "xy"))))

(defn nice? [s]
  (and (three-vowels? s) (double-letter? s) (no-naughty? s)))

(defn trigram? [s]
  (not (nil? (some (fn [[a b c]] (= a c)) (partition 3 1 s)))))

(defn bigram-func [s [a b]]
  (let [x (str a b)
        s2 (.replaceAll s x "")
        len (count s)
        len2 (count s2)]
    (< 3 (- len len2))))
    
(defn bigram-twice? [s]
  (not (nil? (some (partial bigram-func s) (partition 2 1 s)))))
(defn nice2? [s]
  (and (bigram-twice? s) (trigram? s)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [strings (str/split (slurp "input.txt") #"\r\n")]
    (println (count (filter nice? strings)))
    (println (count (filter nice2? strings)))))
