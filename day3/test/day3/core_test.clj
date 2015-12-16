(ns day3.core-test
  (:require [clojure.test :refer :all]
            [day3.core :refer :all]))

(deftest a-test
  (testing "House counts"
    (is (= (count (elf-route ">")) 2))
    (is (= (count (elf-route "^>v<")) 4))
    (is (= (count (elf-route "^v^v^v^v^v")) 2)))
  (testing "Santa-Robot"
    (is (= (count (santa-robot-route "^v")) 3))
    (is (= (count (santa-robot-route "^>v<")) 3))
    (is (= (count (santa-robot-route "^v^v^v^v^v")) 11))))
