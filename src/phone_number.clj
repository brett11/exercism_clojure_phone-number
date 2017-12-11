(ns phone-number)

(defn scrub-number [s]
  (let [numbers-only (apply str (re-seq #"\d+" s))]
    (if (and (= \1 (nth numbers-only 0)) (= 11 (count numbers-only)))
      (apply str (drop 1 numbers-only))
      numbers-only)))

(defn number [s]
  (let [scrubbed-number (scrub-number s)]
    (cond
      (not (= (count scrubbed-number) 10)) "0000000000"
      :else scrubbed-number)))

(defn area-code [s]
  (let [phone-number (number s)]
    (apply str (take 3 phone-number))))

(defn pretty-print [s]
  (let [phone-number (number s)
        area-code (area-code s)
        next-3 (apply str (take 3 (take-last 7 phone-number)))
        last-4 (apply str (take-last 4 phone-number))]
    (format "(%s) %s-%s" area-code next-3 last-4)))

