;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla REPL
;;; 
;;; Welcome to gorilla :-)
;;; 
;;; Shift + enter evaluates code. Hit alt+g twice in quick succession or click the menu icon (upper-right corner) for more commands ...
;;; 
;;; It's a good habit to run each worksheet in its own namespace: feel free to use the declaration we've provided below if you'd like.
;; **

;; @@
(ns day13
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as comb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Get Test inputs
;; **

;; @@
(def test-input (str/split (slurp "testinput.txt") #"\r\n"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/test-input</span>","value":"#'day13/test-input"}
;; <=

;; **
;;; Parse strings to lists of values, with the amount changed to the right sign
;; **

;; @@
(defn parse-line [[guests happiness] s]
  (let [[g1 _ verb amount _ _ _ _ _ _ g2] (str/split s #" ")
        amount (Integer/parseInt amount)
        amount (if (= verb "lose") (- amount) amount)]
    [(conj guests (keyword g1)) (assoc happiness [(keyword g1) (keyword (apply str (reverse (rest (reverse g2)))))] amount)]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/parse-line</span>","value":"#'day13/parse-line"}
;; <=

;; @@
(defn parse-data [s]
  (reduce parse-line [#{} {}] s))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/parse-data</span>","value":"#'day13/parse-data"}
;; <=

;; **
;;; Parse test input
;; **

;; @@
(def parsed-test-input (parse-data test-input))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/parsed-test-input</span>","value":"#'day13/parsed-test-input"}
;; <=

;; @@
(defn calc-seating [seating happiness]
  "Calculates the happiness score for a giving seating by producing all the neighbouring pairs of the seating and adding up the individual scores"
  (let [pairs (partition 2 1 seating)
        pairs (conj pairs (list (last seating) (first seating)))
        pairs (apply conj pairs (map #(list (second %) (first %)) pairs))
        score (reduce + (map #(happiness % 0) pairs))]
    [seating score]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/calc-seating</span>","value":"#'day13/calc-seating"}
;; <=

;; @@
(defn produce-scores [guests happiness]
  "Produce a map of of all possible seatings and its happiness scores from the hashset of guests and the given happiness table"
  (let [allcombis (comb/permutations (seq guests))]
    (reduce conj {} (map #(calc-seating % happiness) allcombis))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/produce-scores</span>","value":"#'day13/produce-scores"}
;; <=

;; @@
(assert (= 330 (apply max (vals (produce-scores (first parsed-test-input) (second parsed-test-input))))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def input (str/split (slurp "input.txt") #"\r\n"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/input</span>","value":"#'day13/input"}
;; <=

;; @@
(def parsed-input (parse-data input))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/parsed-input</span>","value":"#'day13/parsed-input"}
;; <=

;; **
;;; Get the maximum happiness score
;; **

;; @@
(apply max (vals (produce-scores (first parsed-input) (second parsed-input))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>709</span>","value":"709"}
;; <=

;; **
;;; Second part: Add myself to the guest list and seat me
;; **

;; @@
(def guests-with-me (conj (first parsed-input) :Stefan))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day13/guests-with-me</span>","value":"#'day13/guests-with-me"}
;; <=

;; @@
(apply max (vals (produce-scores guests-with-me (second parsed-input))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>668</span>","value":"668"}
;; <=

;; @@

;; @@
