(ns mash-bible.views.logic)

(defn parse-int [n]
  (Integer. (re-find #"[0-9]*" n)))
