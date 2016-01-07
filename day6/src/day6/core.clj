(ns day6.core
  (:gen-class)
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(def grid (make-array Long/TYPE 1000 1000))


(defn grid-on! [x1 y1 x2 y2]
  (doseq [x (range x1 (inc x2))
          y (range y1 (inc y2))]
    (let [val (aget grid x y)]
      (when (even? val)
        (aset grid x y (inc val))))))

(defn grid-off! [x1 y1 x2 y2]
  (doseq [x (range x1 (inc x2))
          y (range y1 (inc y2))]
    (let [val (aget grid x y)]
      (when (odd? val)
        (aset grid x y (inc val))))))

(defn grid-toggle! [x1 y1 x2 y2]
  (doseq [x (range x1 (inc x2))
          y (range y1 (inc y2))]
    (aset grid x y (inc (aget grid x y)))))

(defn str->coords [s1 s2]
  (map #(Integer/parseInt %) (into (str/split s1 #",") (str/split s2 #","))))

(defn brightness! [delta x1 y1 x2 y2]
  (doseq [x (range x1 (inc x2))
          y (range y1 (inc y2))]
    (let [val (+ (aget grid x y) delta)]
      (when-not (neg? val)
        (aset grid x y val)))))

(defn bright-and-dim-lights [s]
  (let [chunks (str/split s #" ")]
    (println (nth chunks 1))
    (case (nth chunks 1)
      "on" (apply (partial brightness! 1) (str->coords (nth chunks 2) (nth chunks 4)))
      "off" (apply (partial brightness! -1) (str->coords (nth chunks 2) (nth chunks 4)))
       (apply (partial brightness! 2) (str->coords (nth chunks 1) (nth chunks 3))))))

(defn light-lights [s]
 (let [chunks (str/split s #" ")]
   (println (nth chunks 1))
   (case (nth chunks 1)
     "on" (apply grid-on! (str->coords (nth chunks 2) (nth chunks 4)))
     "off" (apply grid-off! (str->coords (nth chunks 2) (nth chunks 4)))
      (apply grid-toggle! (str->coords (nth chunks 1) (nth chunks 3))))))

(defn count-lights [a]
  (def cnt (atom 0))
  (doseq [x (range 0 1000)
          y (range 0 1000)]
      (let [val (aget grid x y)]
        (when (odd? val) (swap! cnt inc))))
  @cnt)

(defn brightness [a]
  (def cnt (atom 0))
  (doseq [x (range 0 1000)
          y (range 0 1000)]
      (let [val (aget grid x y)]
        (swap! cnt (partial + val))))
  @cnt)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [inputs (str/split (slurp "input.txt") #"\r\n")]
    ; (doall (map light-lights inputs))
    ; (println (count-lights grid))))
    (doall (map bright-and-dim-lights inputs))
    (println "-----")
    (println (brightness grid))))
