(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer :all]))

(deftest a-test
  (testing "Parsing"
    (is (= (str->dimensions "4x23x21") [4 23 21]))
    (is (= (str->dimensions "2x3x4") [2 3 4]))
    (is (= (str->dimensions "1x1x10") [1 1 10])))
  (testing "Calculation"
    (is (= (dimensions->area [2 3 4]) 58))
    (is (= (dimensions->area [1 1 10]) 43)))
  (testing "Part 2"
    (is (= (dimensions->length [2 3 4]) 34))
    (is (= (dimensions->length [1 1 10]) 14))))
