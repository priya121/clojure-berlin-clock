(ns berlin-clock.core)

(def first-row-on  "     *     ")
(def first-row-off "           ")


(defn- first-row [s]
 (case (mod s 4)
   0 first-row-off
   1 first-row-off
   2 first-row-on
   3 first-row-on))

(defn- hour-row [h f s]
  (apply str (interpose " " (repeat (f h) s))))

(defn- minute-symbol [m]
  (cond
    (= (mod m 15) 0) "R"
    (= (mod m 5) 0) "Y"
    :else ""))

(defn- minute-row [m]
  (apply str (map minute-symbol (range 1 (inc m)))))

(defn clock [h m s]
  (apply str (interpose "\n"
                        [(first-row s)
                         (hour-row h #(int (/ % 5)) "RR")
                         (hour-row h #(mod % 5) "RR")
                         (minute-row m)
                         (hour-row m #(mod % 5) "YY")])))

