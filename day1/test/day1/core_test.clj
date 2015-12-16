(ns day1.core-test
  (:require [clojure.test :refer :all]
            [day1.core :refer :all]))

(deftest a-test
  (testing "Examples"
    (is (= (santas-floor "(())") 0))
    (is (= (santas-floor "()()") 0))
    (is (= (santas-floor "))(((((") 3))
    (is (= (santas-floor "())") -1))
    (is (= (santas-floor "))(") -1))
    (is (= (santas-floor ")))") -3))
    (is (= (santas-floor ")())())") -3)))
  (testing "Examples second part"
    (is (= (floor-minus-one ")") [-1 1]))
    (is (= (floor-minus-one "()())") [-1 5]))))
