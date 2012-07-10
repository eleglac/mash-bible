(ns mash-bible.views.welcome

  (:require [clojure.string          :as s]
            [mash-bible.views.common :as common]
            [mash-bible.views.util   :as util]
            [noir.content.getting-started])

  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
  (common/layout "The M*A*S*H Bible, the #1 resource for fans of televisions' M*A*S*H 4077th - Home"
    (common/sidebar)
    (common/greeting)
    (common/footer)))

(defpage [:get "/:ssnum"] {:keys [ssnum]}  
<<<<<<< HEAD
  (common/layout (str "Season " (s/capitalize ssnum) " - Episode Listing - The M*A*S*H Bible")
=======
  (common/layout (str "The M*A*S*H Bible, the #1 resource for fans of televisions' M*A*S*H 4077th - Episode Listing")
>>>>>>> parent of 5b6aca1... Formatting of sidebar links
    (common/sidebar)
    (->>
      (keyword ssnum)
      (util/ssn-to-eplist)
      (common/episode-list ssnum))
    (common/footer)))

(defpage [:get "/:ssnum/:epnum"] {:keys [ssnum epnum]}  
<<<<<<< HEAD
  (common/layout (str (util/get-ep-title ssnum epnum) " - Summary - The M*A*S*H Bible")
=======
  (common/layout "The M*A*S*H Bible, the #1 resource for fans of the M*A*S*H 4077th - Episode Summary"
>>>>>>> parent of 5b6aca1... Formatting of sidebar links
    (common/sidebar)
    (common/cont ssnum epnum)
    (common/footer)))

(defpage [:get "/:ssnum/:epnum/transcript"] {:keys [ssnum epnum]}
<<<<<<< HEAD
  (common/layout (str (util/get-ep-title ssnum epnum) " - Transcript - The M*A*S*H Bible")
=======
  (common/layout "The M*A*S*H Bible, the #1 resource for fans of the M*A*S*H 4077th - Episode Transcript"
>>>>>>> parent of 5b6aca1... Formatting of sidebar links
    (common/sidebar)
    (common/transcript ssnum epnum)
    (common/footer)))

(defpage "/robots.txt" []
  (slurp "/app/resources/public/robots.txt"))

