(ns day10.core-test
  (:require [clojure.test :refer :all]
            [day10.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= [3 \1 [\2 \3 \4 \5]] (get-prefix "1112345")))
    (is (= [1 \1 [\2 \3]] (get-prefix "123")))
    (is (= "11" (look-and-say "1")))
    (is (= "21" (look-and-say "11")))
    (is (= "1211" (look-and-say "21")))
    (is (= "111221" (look-and-say "1211")))
    (is (= "312211" (look-and-say "111221")))))
