(ns mash-bible.views.welcome

  (:require [mash-bible.views.common :as common]
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
  (common/layout (str "Episode Listing - The M*A*S*H Bible")
    (common/sidebar)
    (->>
      (keyword ssnum)
      (util/ssn-to-eplist)
      (common/episode-list ssnum))
    (common/footer)))

(defpage [:get "/:ssnum/:epnum"] {:keys [ssnum epnum]}  
  (common/layout "Episode Summary - The M*A*S*H Bible"
    (common/sidebar)
    (common/cont ssnum epnum)
    (common/footer)))

(defpage [:get "/:ssnum/:epnum/transcript"] {:keys [ssnum epnum]}
  (common/layout "Episode Transcript - The M*A*S*H Bible"
    (common/sidebar)
    (common/transcript ssnum epnum)
    (common/footer)))

(defpage "/robots.txt" []
  (slurp "/app/resources/public/robots.txt"))

