(ns org-to-reveal.parser
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-bullets [bullets-text])

(defn parse-card [card-text])

(defn parse-slide [slide-text])

(defn parse-slides [slides-text])

;; もうこれ完全にImperativeに書き直したほうがいいのでは…？

(defn parse-section [section-text]
  (if (re-find #"^\* " section-text)
    (let [slides-text (re-find #"^\*\* [\s\S]+" section-text)
          section-only (subs section-text 0 (count slide-text))]
      {:content (subs section-only (inc (str/index-of section-only "\n")))
       :})
    nil))

(defn parse-sections [sections-text]
  (let [text (atom nil)
        sections (atom [])]
    (with-open [r (io/reader "in.txt")]
      (doseq [line (line-seq r)]
        (if (re-find #"^\* " line)
          (let [parsed (parse-section text)]
            (when (some? parsed) (swap! sections (conj % )))
            (reset! text line))
          (swap! text #(str % "\n" line)))))
    (swap! sections (conj % (parse-section text))))
  {:sections sections})

(defn parse-header [header-text]
  {:title (str/trim ((re-find #"#+TITLE:(.*)") 1))
   :author (str/trim ((re-find #"#+AUTHOR:(.*)") 1))})

(defn parse-org [org-text]
  (let [header-text (re-find #"(^#.*$\n)+" org-text)
        sections-text (subs org-text (count header-text))]
    (-> {}
        (merge (parse-header (header-text)))
        (merge (parse-sections (sections-text))))))

(def presentation {:title    "タイトル"
                   :author   "オーサ―"
                   :sections [{:title   "The first section"
                               :content "...and justice for all"
                               :slides  [{:title "Slide #1"
                                          :img   "img/adorable-tabby.jpg"
                                          :cards [{:header       "Ace of spades"
                                                   :bullet-items ["You win some, or lose some"
                                                                  "It's all the same to me"
                                                                  "And don't forget the joker!"]}
                                                  {:header       "Carry on"
                                                   :bullet-items ["So, carry on"
                                                                  "It's time to forget"
                                                                  "The remains from the past"]}]
                                          :tail  "Let's do that."}
                                         {:title "Slide #2"
                                          :cards [{:header       "AOS"
                                                   :bullet-items ["YWSOLS"
                                                                  "IATSTM"
                                                                  "ADFTJ"]}
                                                  {:header       "CO"
                                                   :bullet-items ["SCO"
                                                                  "ITTF"
                                                                  "TRFTP"]}]
                                          :tail  "LDT"}]}]})
