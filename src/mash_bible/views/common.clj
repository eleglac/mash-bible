(ns mash-bible.views.common
  (:import java.io.FileNotFoundException)

  (:require [mash-bible.views.util :as util]
            [clojure.string        :as s])

  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers]))

(defpartial header [& content]
  [:div#header
    [:h1 "The M*A*S*H Bible"]
    [:h3 (util/get-rand-header)]])

(defpartial ad []
  [:div#ad
    (include-js "/js/ads.js")
    (include-js "http://pagead2.googlesyndication.com/pagead/show_ads.js")])

(defpartial sidebar []
  [:div#sidebar
    [:ul
      [:li [:a {:href "/"} "Home"]]
      (map 
        (fn [lol] 
          [:li [:a {:href (str "/" (name lol))} "Season " (s/capitalize (name lol))]]) 
        (keys util/ssn-to-eplist))
      [:li "Search"]]
    (ad)])

(defpartial cont [ssnum epnum]
  (->> 
    (str util/summaries (util/sym-to-num (keyword ssnum)) " x " epnum ".html")
    (slurp)
    ((fn [ep]
       [:div#content 
         ep
         [:br] [:br]
         [:a {:href (str "/" (name ssnum) "/" epnum "/transcript")} "Episode Transcript"]]))))

(defpartial transcript [ssnum epnum]  
  (let [script (str util/transcripts (util/sym-to-num (keyword ssnum)) " x " epnum ".txt")]
    (try [:div#content (slurp script)] 
         (catch FileNotFoundException FNFE 
                [:div#content "This transcript is not yet available! Check back later."]))))


(defpartial footer [& content]
  [:div#footer
    [:em "This website created by Alex J. Ponting, (c) 2012"] [:br]
    [:em "Thanks to Vicinio Perez for production code information."] [:br]
    [:em "All other episode capsule text was created by Dave Smith (c) 2000-2005"] [:br]
    [:em "The home site for the episode capsules is " [:a {:href "http://epguides.com"} "http://epguides.com"] [:br]]])

(defpartial episode-list [ssn eps]
  [:div#content
    [:h2 "Season " (s/capitalize (name ssn))]
    [:ul 
      (map 
        (fn [foo] 
          [:li 
            [:a  {:href (str "/" ssn "/" foo)} foo "   "
             (nth (s/split-lines (slurp (util/title-path ssn))) (dec (util/parse-int foo)))]]) 
        eps)]])

(defpartial layout [title & content]
  (html5
    [:head
      [:title title]
      [:meta {:name "description" :content "The M*A*S*H Bible aims to be the #1 web clearinghouse for information related to the CBS television show 'M*A*S*H'."}]
      [:link {:rel "shortcut icon" :href "http://www.mashbible.com/favicon.ico"}]
      (include-css "/css/common.css")]
      [:body
        [:div#wrapper
          (header)
          content]]))

(defpartial greeting []
  [:div#content
    [:p "Welcome to the M*A*S*H Bible, a website devoted to the CBS television show M*A*S*H."]  
    [:p "At present, the site hosts an episode guide for all eleven seasons, including the finale.
       The site is still a work-in-progress, however. Eventually, transcripts for all 261
       episodes will also be available, so check back frequently!"]])

