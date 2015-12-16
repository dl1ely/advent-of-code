(defproject day1 "0.1.0-SNAPSHOT"
  :description "Day 1 of Advent of Code"
  :url "http://adventofcode.com/day/1"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :plugins [[jonase/eastwood "0.2.1"]]
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot day1.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
