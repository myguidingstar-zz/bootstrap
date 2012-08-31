(ns hiccup.pagination)

(defn pagination-list [start current end]
  "Generate a vector of pages"
  (apply conj
    (apply vector ;ensure outputs are always vectors
      (let [delta (- current start)]
        (cond
          (>= delta 3) [start "..." (dec current)]
          (= delta 2) [start (dec current)]
          (= delta 1) [start])))
    current
    (let [delta (- current end)]
      (cond
        (= delta -1) [end]
        (= delta -2) [(inc current) end]
        (<= delta -3) [(inc current) "..." end]))))

(defn pagination-view [pg-list current fn-page->url]
  "Produce some HTML from a vector of pages"
  (apply vector
      (conj
        (for [page pg-list]
          (if (= page "...")
            [:li [:a {:href "#"} "..."]]
            (if (= page current)
              [:li.active [:a {:href (fn-page->url page)} page]]
              [:li [:a {:href (fn-page->url page)} page]])))
        :ul)))

(defn pagination [[start current end] fn-page->url]
  (pagination-view
    (pagination-list start current end)
    fn-page->url
    current))

(defn total-pages [total-items items-per-page]
  "Calculate number of pages from total-items and items-per-page"
  (if (zero? (rem total-items items-per-page))
    (/ total-items items-per-page)
    (+ 1 (quot total-items items-per-page))))
