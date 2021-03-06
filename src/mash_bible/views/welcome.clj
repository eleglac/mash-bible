(ns mash-bible.views.welcome

  (:require [clojure.string          :as s]
            [mash-bible.views.common :as common]
            [mash-bible.views.util   :as util]
            [noir.content.getting-started])

  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
  (common/layout "Home - The M*A*S*H Bible"
    (common/sidebar)
    (common/greeting)
    (common/footer)))

(defpage [:get "/:ssnum"] {:keys [ssnum]}  
  (common/layout (str "Season " (s/capitalize ssnum) " - Episode Listing - The M*A*S*H Bible")
    (common/sidebar)
    (->>
      (keyword ssnum)
      (util/ssn-to-eplist)
      (common/episode-list ssnum))
    (common/footer)))

(defpage [:get "/:ssnum/:epnum"] {:keys [ssnum epnum]}
  (common/layout (str (util/get-ep-title ssnum (dec (util/parse-int epnum))) " - Summary - The M*A*S*H Bible")
    (common/sidebar)
    (common/cont ssnum epnum)
    (common/footer)))

(defpage [:get "/:ssnum/:epnum/transcript"] {:keys [ssnum epnum]}
  (common/layout (str (util/get-ep-title ssnum (dec (util/parse-int epnum))) " - Transcript - The M*A*S*H Bible")
    (common/sidebar)
    (common/transcript ssnum epnum)
    (common/footer)))

(defpage "/robots.txt" []
  (slurp "/app/resources/public/robots.txt"))
