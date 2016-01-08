(ns day7.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def commands (atom {}))

(defn integrify [v]
  (if (nil? v)
    nil
    (if (number? v)
      v
      (if (re-matches #"\d+" v)
        (Integer/parseInt v)
        v))))

(defn add-command [s]
  (let [[wire _ op1 op2 op3] (reverse (str/split s #" "))]
    (if (nil? op2)
      (swap! commands assoc (keyword wire) (integrify op1))
      (swap! commands assoc (keyword wire) [(keyword op2) (integrify op3) (integrify op1)]))))

(declare resolve-wire)

(defn resolve-op [op]
  (if (number? op)
    op
    (resolve-wire (keyword op))))

(defmulti execute-command first)
(defmethod execute-command :AND [in]
  (let [[op1 op2] (rest in)]
    (bit-and (resolve-op op1) (resolve-op op2))))
(defmethod execute-command :OR [in]
  (let [[op1 op2] (rest in)]
    (bit-or (resolve-op op1) (resolve-op op2))))
(defmethod execute-command :NOT [in]
  (let [[op1 op2] (rest in)]
    (bit-not (resolve-op op2))))
(defmethod execute-command :LSHIFT [in]
  (let [[op1 op2] (rest in)]
    (bit-shift-left (resolve-op op1) op2)))
(defmethod execute-command :RSHIFT [in]
  (let [[op1 op2] (rest in)]
    (unsigned-bit-shift-right (resolve-op op1) op2)))
(defmethod execute-command :default [in]
  (println "ERROR"))

(defn resolve-wire [label]
  (if (vector? (@commands label))
    (let [val (bit-and (execute-command (@commands label)) 0xFFFF)]
      (swap! commands assoc label val)
      val)
    (@commands label)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [inputs (str/split (slurp "input.txt") #"\r\n")]
    (doall (map add-command inputs))
    (println "a="(resolve-wire :lx)))
  (reset! commands {})
  (let [inputs (str/split (slurp "input2.txt") #"\r\n")]
    (doall (map add-command inputs))
    (println "a="(resolve-wire :lx))))
