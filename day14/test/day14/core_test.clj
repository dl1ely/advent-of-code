(ns day14.core-test
  (:require [clojure.test :refer :all]
            [day14.core :refer :all]))

(def testdeer {:comet {:fly 10 :speed 14 :rest 127}
               :dancer {:fly 11 :speed 16 :rest 162}})
(def testduration 1000)

(deftest test
  (testing "Race"
    (is (= 1120 (distance (:comet testdeer) testduration)))
    (is (= 1056 (distance (:dancer testdeer) testduration)))
    (println (race testdeer testduration)))
  (testing "Scoring"
    (is (= 689 (:dancer (score testdeer testduration))))
    (is (= 312 (:comet (score testdeer testduration))))))
