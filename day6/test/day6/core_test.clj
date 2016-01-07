(ns day6.core-test
  (:require [clojure.test :refer :all]
            [day6.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (light-lights "turn on 0,0 through 5,5")
    (is (= 1 (aget grid 0 0)))
    (is (= 0 (aget grid 499 499)))
    (is (= 0 (aget grid 999 999)))))
