(defproject day12 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot day12.core
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.3.6"]]
  :profiles {:uberjar {:aot :all}})
