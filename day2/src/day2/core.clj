(ns day2.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn str->dimensions [s]
  (map #(Integer/parseInt %) (str/split s #"x")))

(defn dimensions->area [[l w h]]
  (let [a1 (* l w)
        a2 (* l h)
        a3 (* w h)
        slack (min a1 a2 a3)]
    (+ slack (* 2 (+ a1 a2 a3)))))

(defn dimensions->length [[l w h]]
  (let [sides (+ l w h)
        circum (- sides (max l w h))]
    (+ (* 2 circum) (* l w h))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [data (-> "data.txt"
                (slurp)
                (str/split #"\r\n"))
        dimensions (map str->dimensions data)
        areas (map dimensions->area dimensions)
        lengths (map dimensions->length dimensions)]
    (println (reduce + 0 areas))
    (println (reduce + 0 lengths))))
