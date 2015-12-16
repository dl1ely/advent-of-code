(defproject day2 "0.1.0-SNAPSHOT"
  :description "Advent of Code: day 2"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :plugins [[jonase/eastwood "0.2.1"]]
  :main ^:skip-aot day2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
