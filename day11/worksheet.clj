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
(ns day11
  (:require [clojure.set :as set]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Characters of the alphabet
;; **

;; @@
(def abc "abcdefghijklmnopqrstuvwxyz")
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/abc</span>","value":"#'day11/abc"}
;; <=

;; **
;;; All possible consecutive three-character-sequences of the alphabet
;; **

;; @@
(def triplets (set (partition 3 1 abc)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/triplets</span>","value":"#'day11/triplets"}
;; <=

;; @@
(defn has-triplet? [s]
  "Return a list of all triplets contained in s, or nil if none"
  (seq (set/intersection triplets (set (partition 3 1 s)) )))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/has-triplet?</span>","value":"#'day11/has-triplet?"}
;; <=

;; @@
(defn has-only-allowed-characters? [s]
  "Checks if s does not contain any of the forbidden characters"
  (empty? (set/intersection (set s) #{\i \o \l})))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/has-only-allowed-characters?</span>","value":"#'day11/has-only-allowed-characters?"}
;; <=

;; **
;;; All characters of the alphabet, doubled
;; **

;; @@
(def duplets (set (map (fn [x] [x x]) abc)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/duplets</span>","value":"#'day11/duplets"}
;; <=

;; @@
(defn string->dupletmatch [s] 
  "Returns a list of indexes in s where duplets were found"
  (filter some? (map-indexed #(if (seq (set/intersection duplets (set [%2]))) %1 nil) (partition 2 1 s))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/string-&gt;dupletmatch</span>","value":"#'day11/string->dupletmatch"}
;; <=

;; @@
(defn duplet-count [s] 
  "Counts the number of no-overlapping duplets in s (where the index is at least 2 bigger then the previously counted one)."
  (last (reduce (fn [[lastidx cnt] idx] (if (< 1 (- idx lastidx)) [idx (inc cnt)] [lastidx cnt])) [-2 0] (string->dupletmatch s))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/duplet-count</span>","value":"#'day11/duplet-count"}
;; <=

;; @@
(defn has-two-duplets? [s]
  "Returns true if s has at least two duplets"
  (< 1 (duplet-count s)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/has-two-duplets?</span>","value":"#'day11/has-two-duplets?"}
;; <=

;; @@
(defn valid-password? [s]
  "Returns true if all 3 conditions for valid passwords are met"
  (and (has-triplet? s)
       (has-only-allowed-characters? s)
       (has-two-duplets? s)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/valid-password?</span>","value":"#'day11/valid-password?"}
;; <=

;; @@
(assert (false? (valid-password? "hijklmmn")))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(assert (nil? (valid-password? "abbceffg")))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(assert (nil? (valid-password? "abbcegjk")))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(assert (valid-password? "abcdffaa"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(assert (valid-password? "ghjaabcc"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(defn next-password [s]
  "Returns the next password by incrementing given password s. Also returns invalid passwords."
  (if (empty? s) "a"
	  (let [r (reverse s)
    	    l (inc (int (first r)))
        	rem (rest r)]
    	(if (< (int \z) l) 
      		(str (next-password (reverse rem)) "a")	
      		(str (if (empty? rem) nil (apply str (reverse rem))) (char l))))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/next-password</span>","value":"#'day11/next-password"}
;; <=

;; @@
(defn next-valid-password [s]
  "returns the next valid password."
  (first (filter valid-password? (rest (iterate next-password s)))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;day11/next-valid-password</span>","value":"#'day11/next-valid-password"}
;; <=

;; @@
(assert (= (next-valid-password "abcdefgh") "abcdffaa"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(assert (= (next-valid-password "ghijklmn") "ghjaabcc"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(next-valid-password "hepxcrrq")
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;hepxxyzz&quot;</span>","value":"\"hepxxyzz\""}
;; <=

;; @@
(next-valid-password (next-valid-password "hepxcrrq"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;heqaabcc&quot;</span>","value":"\"heqaabcc\""}
;; <=

;; @@

;; @@
