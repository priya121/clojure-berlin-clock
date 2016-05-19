(ns berlin-clock.core-spec
  (:require [speclj.core :refer :all]
            [berlin-clock.core :refer :all]))

; todo -
; figure out a way to do quicker tests - maps?

(defn- row-of [n s]
  (get (clojure.string/split s #"\n") n))

(def last-row-of (partial row-of 4))
(def fourth-row-of (partial row-of 3))
(def third-row-of (partial row-of 2))
(def second-row-of (partial row-of 1))
(def first-row-of (partial row-of 0))

(defn second-clock-row [h]
  (row-of 1 (clock h 0 0)))

(describe "berlin-clock"
  (context "first-row-of"
    (it "shows midnight"
      (should= (str "           \n\n\n\n") (clock 0 0 0)))
    (it "shows two second"
      (should= "     *     " (first-row-of (clock 0 0 2))))
    (it "shows one second"
      (should= "           " (first-row-of (clock 0 0 1))))
    (it "shows three second"
      (should= "     *     " (first-row-of (clock 0 0 3))))
    (it "shows four second"
      (should= "           " (first-row-of (clock 0 0 4)))))
  (context "second-row-of"
    (it "shows five hours"
      (should= "RR" (second-clock-row 5)))
    (it "shows ten hours"
      (should= "RR RR" (second-row-of (clock 10 0 0))))
    (it "shows fifteen hours"
      (should= "RR RR RR" (second-row-of (clock 15 0 0)))) 
    (it "shows twenty hours"
      (should= "RR RR RR RR" (second-row-of (clock 20 0 0))))
    (it "shows one hour"
      (should= "" (second-row-of (clock 1 0 0)))))
  (context "third-row-of"
    (it "shows one hour"
      (should= "RR" (third-row-of (clock 1 0 0)))))
  (context "four-row-of"
    (it "shows five minutes"
      (should= "Y" (fourth-row-of (clock 0 5 0))))
    (it "shows ten minutes"
      (should= "YY" (fourth-row-of (clock 0 10 0))))
    (it "shows fifteen minutes"
      (should= "YYR" (fourth-row-of (clock 0 15 0)))))
  (context "last-row"
    (it "shows one minute"
      (should= "YY" (last-row-of (clock 0 1 0))))))

