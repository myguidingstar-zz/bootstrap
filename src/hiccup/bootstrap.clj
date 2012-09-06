(ns hiccup.bootstrap
  (:require clojure.string
            clojure.pprint))

(defmacro meta-tags
  "Add meta-tags quickly by providing a map of meta-tags."
  [m]

  `(for [[k# v#] ~m]
     [:meta {:name (name k#) :content v#}]))

(defmacro favicon
  "Add favicon to HTML page"
  [path]

  [:link {:rel "shortcut icon" :href path}])

(defmacro apple-touch-icon
  "A commonly used touch icon group"
  []

  [:link {:rel "apple-touch-icon-precomposed"
          :sizes "144x144"
          :href "assets/ico/apple-touch-icon-144-precomposed.png"}]
  [:link {:rel "apple-touch-icon-precomposed"
          :sizes "114x114"
          :href "assets/ico/apple-touch-icon-114-precomposed.png"}]
  [:link {:rel "apple-touch-icon-precomposed"
          :sizes "72x72"
          :href "assets/ico/apple-touch-icon-72-precomposed.png"}]
  [:link {:rel "apple-touch-icon-precomposed"
          :href "assets/ico/apple-touch-icon-57-precomposed.png"}])

(defmacro typeahead-data
  "Generate typeahead inline data"
  [items]

  (format "[%s]"
          (clojure.string/join ","
                               (map #(format "\"%s\"" %1) items))))

(def bootstrap-js
  "Bootstrap javascript pack"
  (array-map :jquery "/assets/js/jquery.js"
             :prettify "/assets/js/google-code-prettify/prettify.js"
             :prettify-clj "/assets/js/google-code-prettify/lang-clj.js"
             :transition "/assets/js/bootstrap-transition.js"
             :alert "/assets/js/bootstrap-alert.js"
             :modal "/assets/js/bootstrap-modal.js"
             :dropdown "/assets/js/bootstrap-dropdown.js"
             :scrollspy "/assets/js/bootstrap-scrollspy.js"
             :tab "/assets/js/bootstrap-tab.js"
             :tooltip "/assets/js/bootstrap-tooltip.js"
             :popover "/assets/js/bootstrap-popover.js"
             :button "/assets/js/bootstrap-button.js"
             :collapse "/assets/js/bootstrap-collapse.js"
             :carousel "/assets/js/bootstrap-carousel.js"
             :typeahead "/assets/js/bootstrap-typeahead.js"))

(def bootstrap-css
  "Bootstrap style pack"
  (array-map  :bootstrap "/assets/css/bootstrap.css"
              :responsive "/assets/css/bootstrap-responsive.css"
              :prettify "/assets/js/google-code-prettify/prettify.css"))

(defn get-assets
  "Get js/css assets' paths"
  ([m]
     (get-assets m :all))
  ([m v]
     (filter string?
             (cond (= :all v)
                   (apply vector (vals m))
                   (= nil v)
                   []
                   :else
                   (apply vector (map #(get m %) v))))))

(defmacro format-code
  "Get a Clojure block of code and return formatted code as string."
  [code]
  `(clojure.pprint/write (quote ~code) :pretty true :stream nil))

(defn format-code-str
  [string]
  (clojure.pprint/write (read-string string) :pretty true :stream nil))