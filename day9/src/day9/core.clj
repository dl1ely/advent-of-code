(ns day9.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as comb]))

(def dist (atom {}))

(def loc (atom #{}))

(defn build-input [s]
  (let [[a _ b _ d] (str/split s #" ")]
    (swap! dist assoc [(keyword a) (keyword b)] (Integer/parseInt d))
    (swap! dist assoc [(keyword b) (keyword a)] (Integer/parseInt d))
    (swap! loc conj (keyword a) (keyword b))))

(defn dist-for-route [route]
  (loop [r route d 0]
    (if (empty? (rest r))
      d
      (recur (rest r) (+ d (get @dist (take 2 r) 99999999))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [inputs (str/split (slurp "input.txt") #"\r\n")]
    (doall (map build-input inputs)))

  (let [allroutes (comb/permutations (seq @loc))
        alldistance (map dist-for-route allroutes)
        alldata (zipmap allroutes alldistance)
        sorteddata (sort-by val < alldata)
        longestdata (sort-by val > alldata)]
    (println (first sorteddata))
    (println (first longestdata))))
