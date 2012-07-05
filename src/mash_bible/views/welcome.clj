(ns mash-bible.views.welcome

  (:require [mash-bible.views.common :as common]
            [mash-bible.views.logic  :as logic]
            [noir.content.getting-started])

  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defn range-strs [n]
  (map str (range 1 (inc n)))) 

(def ssn-to-eplist
  (array-map 
    :One    (range-strs 24) 
    :Two    (range-strs 24) 
    :Three  (range-strs 24) 
    :Four   (range-strs 25) 
    :Five   (range-strs 25) 
    :Six    (range-strs 25) 
    :Seven  (range-strs 25) 
    :Eight  (range-strs 25) 
    :Nine   (range-strs 20) 
    :Ten    (range-strs 22) 
    :Eleven (range-strs 16)))

(defpage "/" []
  (common/layout
    (common/sidebar ssn-to-eplist)
    [:div#content "This is the MASH Bible!  So far it's not much, but there is a full episode guide."]
    (common/footer)))

(defpage [:get "/episode/:ssnum"] {:keys [ssnum]}  
  (common/layout
    (common/sidebar ssn-to-eplist)
    (->>
      (keyword ssnum)
      (ssn-to-eplist)
      (common/episode-list ssnum))
    (common/footer)))

(defpage [:get "/episode/:ssnum/:epnum"] {:keys [ssnum epnum]}  
  (common/layout
    (common/sidebar ssn-to-eplist)
    (common/cont ssnum epnum)
    (common/footer)))

