(ns mash-bible.views.util
  (require [clojure.string :as s]))

(defn parse-int [ss] 
  (Integer. (re-find #"[0-9]+" ss)))

(defn range-strs [n]
  (map str (rest (range (inc n)))))

(def ssn-to-eplist
  (array-map
    :one    (range-strs 24)
    :two    (range-strs 24)
    :three  (range-strs 24)
    :four   (range-strs 25)
    :five   (range-strs 25)
    :six    (range-strs 25)
    :seven  (range-strs 25)
    :eight  (range-strs 25)
    :nine   (range-strs 20)
    :ten    (range-strs 22)
    :eleven (range-strs 16)))

(def sym-to-num 
  {:one 1 :two 2 :three 3 :four 4 :five 5 :six 6 :seven 7 :eight 8 :nine 9 :ten 10 :eleven 11})

(def root 
  "/app")

(def public
  (str root "/resources/public/"))

(def summaries 
  (str root "/resources/public/summaries/"))

(def transcripts 
  (str root "/resources/public/transcripts/"))

(def titles 
  (str root "/resources/public/titles/"))

(defn summary-path [ssn epnum]
  (str summaries (sym-to-num (keyword ssn)) " x " epnum ".html"))

(defn title-path [ssn]
  (str titles (name ssn) ".txt"))

(defn get-ep-title [ssn epnum]
  (nth (s/split-lines (slurp (title-path ssn))) epnum))

(defn get-rand-header []
  (let [titles (s/split-lines (slurp (str public "headers.txt")))]
    (rand-nth titles)))
