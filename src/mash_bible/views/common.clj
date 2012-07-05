(ns mash-bible.views.common 
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers]
        [clojure.string :only [split-lines]]
        [mash-bible.views.logic]))

(defpartial header [& content]
  [:div#header
    [:h1 "The M*A*S*H Bible"]
    [:h3 "A page with so much body it should be continued on the next page."]])

(defpartial ad []
  [:div#ad
    (include-js "/js/ads.js")
    (include-js "http://pagead2.googlesyndication.com/pagead/show_ads.js")])

(defpartial sidebar [ep-list]
  [:div#sidebar
    [:ul
      [:li [:a {:href "/"} "Home"]]
      (map (fn [lol] [:li [:a {:href (str "/episode/" (name lol))} "Season " lol]]) (keys ep-list))        
      [:li "Search"]]
    (ad)])

(defpartial cont [ssnum epnum]
  (let [sym-to-num {:One 1, :Two 2, :Three 3, :Four 4, :Five 5, :Six 6, :Seven 7, :Eight 8, :Nine 9, :Ten 10, :Eleven 11}
        path       "/app/resources/public/eps/"]
    (->> (str  path (sym-to-num (keyword ssnum)) " x " epnum ".html")
         (slurp)
         ((fn [ep] [:div#content ep])))))

(defpartial footer [& content]
  [:div#footer
    [:em "This website created by Alex J. Ponting, (c) 2012"] [:br]
    [:em "Thanks to Vicinio Perez for production code information."] [:br]
    [:em "All other episode capsule text was created by Dave Smith (c) 2000-2005"] [:br]
    [:em "The home site for the episode capsules is " [:a {:href "http://epguides.com"} "http://epguides.com"] [:br]]])

(defpartial episode-list [ssn eps]
  [:div#content
    [:h2 "Season " ssn]
    [:ul
      (let [ssn-to-num {"One" 1, "Two" 2, "Three" 3, "Four" 4, "Five" 5, "Six" 6, "Seven" 7, "Eight" 8, "Nine" 9, "Ten" 10 "Eleven" 11}
            path       "/app/resources/public/eps/"]
      (map 
        (fn [foo] 
          [:li 
            [:a  {:href (str "/episode/" ssn "/" foo)} foo "   "
             (nth (split-lines (slurp (str path (ssn-to-num ssn) ".txt"))) (dec (parse-int foo)))]]) 
        eps))]])

(defpartial layout [& content]
  (html5
    [:head
      [:title "This is the M*A*S*H Bible.  I love being both of us."]
      (include-css "/css/common.css")]
      [:body
        [:div#wrapper
          (header)
          content]]))



