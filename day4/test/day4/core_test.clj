(ns day4.core-test
  (:require [clojure.test :refer :all]
            [day4.core :refer :all]))

(deftest a-test
  (testing "md5"
    (is (= (subs (md5 "abcdef609043") 0 5) "00000"))
    (is (= (subs (md5 "pqrstuv1048970") 0 5) "00000"))))
