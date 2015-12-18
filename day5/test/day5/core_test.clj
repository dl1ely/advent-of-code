(ns day5.core-test
  (:require [clojure.test :refer :all]
            [day5.core :refer :all]))

(deftest a-test
  (testing "vowels"
    (is (true? (three-vowels? "ugknbfddgicrmopn")))
    (is (true? (three-vowels? "aaa"))))
  (testing "double letters"
    (is (true? (double-letter? "ugknbfddgicrmopn")))
    (is (true? (double-letter? "aaa"))))
  (testing "no naughty strings"
    (is (true? (no-naughty? "ugknbfddgicrmopn")))
    (is (true? (no-naughty? "aaa"))))
  (testing "niceness"
    (is (true? (nice? "ugknbfddgicrmopn")))
    (is (true? (nice? "aaa"))))
  (testing "naughtyness"
    (is (false? (nice? "jchzalrnumimnmhp")))
    (is (false? (nice? "haegwjzuvuyypxyu")))
    (is (false? (nice? "dvszwmarrgswjxmb"))))
  (testing "niceness2"
    (is (true? (nice2? "qjhvhtzxzqqjkmpb")))
    (is (true? (nice2? "xxyxx")))
    (is (false? (nice2? "uurcxstgmygtbstg")))
    (is (false? (nice2? "ieodomkazucvgmuy")))))
