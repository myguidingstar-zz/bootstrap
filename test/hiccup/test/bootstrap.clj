(ns hiccup.test.bootstrap
  (:use [hiccup.bootstrap]
        [clojure.test]))

(deftest meta-tags-test
  (is (= (meta-tags {:foo "bar" :boo "far"})
         '([:meta {:content "far", :name "boo"}]
             [:meta {:content "bar", :name "foo"}]))))

(deftest favicon-test
  (is (= (favicon "/path/to/file.ico")
         [:link {:href "/path/to/file.ico", :rel "shortcut icon"}])))

(deftest typeahead-data-test
  (is (= (typeahead-data ["item 1" "item 2" "item 3"])
         "[\"item 1\",\"item 2\",\"item 3\"]")))

(deftest get-assets-tests
  (is (= (get-assets  {:a "a.js" :b "b.js"}
                      [:a :b])
         ["a.js" "b.js"]))

  (is (= (get-assets (array-map  :a "a.js" :b "b.js" :c "c.js") :all)
         ["a.js" "b.js" "c.js"]))

  (is (= (get-assets {:a "a.js" :b "b.js"} nil)
         [])))

(deftest format-code-test
  (is (= (format-code
          (defn foo-function
            "A function that do nothing"
            [args]
            (str "I won't do anything!")))

         (str "(defn\n"
              " foo-function\n"
              " \"A function that do nothing\"\n"
              " [args]\n"
              " (str \"I won't do anything!\"))"))))

(deftest format-code-str-test
  (is (= (format-code-str
          (str
           '(defn foo-function
            "A function that do nothing"
            [args]
            (str "I won't do anything!"))))
         (str "(defn\n"
              " foo-function\n"
              " \"A function that do nothing\"\n"
              " [args]\n"
              " (str \"I won't do anything!\"))"))))