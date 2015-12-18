(ns day4.core
  (:gen-class)
  (:import (java.security MessageDigest)
          (java.math BigInteger)))

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn starts-with-00000? [seed length s]
  (= (subs (md5 (str seed s)) 0 length) (format (str "%0" length "d") 0)))

(defn hashes [seed length]
  (filter (partial starts-with-00000? seed length) (range)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (first (hashes "yzbqklnj" 5)))
  (println (first (hashes "yzbqklnj" 6))))
