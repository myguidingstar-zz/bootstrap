(ns hiccup.test.pagination
  (:use [hiccup.pagination]
        [clojure.test]))

(deftest pagination-list-test
  (is (= (pagination-list 1 10 99)
         [1 "..." 9 10 11 "..." 99]))

  (is (= (pagination-list 1 10 11)
         [1 "..." 9 10 11]))

  (is (= (pagination-list 1 10 10)
         [1 "..." 9 10]))

  (is (= (pagination-list 10 10 99)
         [10 11 "..." 99]))

  (is (= (pagination-list 10 10 10)
         [10])))

(deftest pagination-view-test
   (is (= (pagination-view [10] 10 #(str "/some/url/" %1))
          [:ul [:li.active
                [:a {:href "/some/url/10"} 10]]]))

   (is (= (pagination-view [10 11 12] 10 #(str "/some/url/" %1))
          [:ul
           [:li.active [:a {:href "/some/url/10"} 10]]
           [:li [:a {:href "/some/url/11"} 11]]
           [:li [:a {:href "/some/url/12"} 12]]]))

   (is (= (pagination-view [1 "..." 3 4 5] 4 #(str "/some/url/" %1))
          [:ul
           [:li [:a {:href "/some/url/1"} 1]]
           [:li [:a {:href "#"} "..."]]
           [:li [:a {:href "/some/url/3"} 3]]
           [:li.active [:a {:href "/some/url/4"} 4]]
           [:li [:a {:href "/some/url/5"} 5]]])))

(deftest pagination-test
  (is (= (pagination [1 3 7] #(str "/new/url/" %1))
         [:ul
          [:li [:a {:href "/new/url/1"} 1]]
          [:li [:a {:href "/new/url/2"} 2]]
          [:li.active [:a {:href "/new/url/3"} 3]]
          [:li [:a {:href "/new/url/4"} 4]]
          [:li [:a {:href "#"} "..."]]
          [:li [:a {:href "/new/url/7"} 7]]]))

  (is (= (pagination [1 1 5] #(str "/new/url/" %1))
         [:ul
          [:li.active [:a {:href "/new/url/1"} 1]]
          [:li [:a {:href "/new/url/2"} 2]]
          [:li [:a {:href "#"} "..."]]
          [:li [:a {:href "/new/url/5"} 5]]]))

  (is (= (pagination [1 4 6] #(str "/new/url/" %1))
         [:ul
          [:li [:a {:href "/new/url/1"} 1]]
          [:li [:a {:href "#"} "..."]]
          [:li [:a {:href "/new/url/3"} 3]]
          [:li.active [:a {:href "/new/url/4"} 4]]
          [:li [:a {:href "/new/url/5"} 5]]
          [:li [:a {:href "/new/url/6"} 6]]])))

(deftest total-pages-test
  (is (= (total-pages 20 3) 7))
  (is (= (total-pages 19 3) 7))
  (is (= (total-pages 18 3) 6))
  (is (= (total-pages 17 3) 6))
  (is (= (total-pages 15 3) 5)))