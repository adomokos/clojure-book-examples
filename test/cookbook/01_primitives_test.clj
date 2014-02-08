(ns cookbook.01-primitives-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [cookbook.01-primitives :refer :all]))

(deftest changing-capitalization-test
  (testing "changing capitalization"
    (is (= "This is a proper sentence.",
           (str/capitalize "this is a proper sentence."))))
  (testing "upper casing string"
    (is (= "LOUD NOISES!", (str/upper-case "loud noises!"))))
  (testing "lower casing string"
    (is (= "column_header_one", (str/lower-case "COLUMN_HEADER_ONE"))))
  (testing "non letter characters are unchanged"
    (is (= "!@#$%^&*", (str/lower-case "!@#$%^&*")))))

(deftest cleaning-up-whitespace-test
  (testing "cleaning up leading and trailing white space"
    (is (= "Bacon ipsum dolor sit."
           (str/trim "\tBacon ipsum dolor sit.\n"))))
  (testing "cleaning up inside the string"
    (is (= "Who put all this whitespace here?"
           (str/replace "Who\t\nput  all this\fwhitespace here?" #"\s+" " "))))
  (testing "replace windows-style line-endings with Unix-style newlines"
    (is (= "Line 1\nLine 2"
           (str/replace "Line 1\r\nLine 2" "\r\n" "\n"))))
  (testing "left trim"
    (is (= "Column Header\t"
           (str/triml " Column Header\t"))))
  (testing "right trim"
    (is (= "\t\t* Second-level bullet."
           (str/trimr "\t\t* Second-level bullet.\n")))))

(deftest building-a-string-from-parts-test
  (testing "concatenate strings"
    (is (= "John Doe"
           (str "John" " " "Doe"))))
  (testing "collapsing a sequence of chars into string"
    (is (= "Hello World!"
           (apply str [\H \e \l \l \o \ \W \o \r \l \d \!]))))
  (testing "joining values"
    (is (= "milk, butter, flour, eggs",
           (str/join  ", " ["milk" "butter" "flour" "eggs"])))))

(deftest test-yelling
  (are [string result]
    (= (yelling? string) result)
       "LOUD NOISES!" true
       "Take a DEEP breath." false))
