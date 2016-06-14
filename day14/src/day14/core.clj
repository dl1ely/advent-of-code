(ns day14.core
  (:gen-class))

(defn distance [{:keys [fly speed rest]} duration]
  (loop [dist 0 rduration duration]
    (if-not (pos? rduration)
      dist
      (recur (+ dist (* (min fly rduration) speed))
           (- rduration fly rest)))))

(defn race [reindeer duration]
  (zipmap (keys reindeer) (map #(distance % duration) (vals reindeer))))

(defn max-keys [s]
  (let [maxval (apply max (vals s))]
    (filter #(= (% s) maxval) (keys s))))

(defn score [reindeer duration]
  (frequencies (mapcat #(max-keys (race reindeer %)) (range 1 (inc duration)))))

(def reindeer {:vixen {:fly 8 :speed 8 :rest 53}
               :blitzen {:fly 4 :speed 13 :rest 49}
               :rudolph {:fly 7 :speed 20 :rest 132}
               :cupid {:fly 4 :speed 12 :rest 43}
               :donner {:fly 5 :speed 9 :rest 38}
               :dasher {:fly 4 :speed 10 :rest 37}
               :comet {:fly 37 :speed 3 :rest 76}
               :prancer {:fly 12 :speed 9 :rest 97}
               :dancer {:fly 1 :speed 37 :rest 36}})

(def duration 2503)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (race reindeer duration))
  (println (score reindeer duration)))
