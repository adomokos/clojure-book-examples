(ns cookbook.03-general-computing)

(defn area-simple
  "Calculate the area of a shape"
  [shape]
  (condp = (:type shape)
    :triangle (* (:base shape) (:height shape) 1/2)
    :rectangle (* (:length shape) (:width shape))))

(defmulti area
  "Calculate the area of a shape"
  :type)

(defmethod area :rectangle [shape]
  (* (:length shape) (:width shape)))

(defmethod area :triangle [shape]
  (* (:base shape) (:height shape) 1/2))

(defmethod area :circle [shape]
  (* (. Math PI) (:radius shape) (:radius shape)))

(defprotocol Shape
  (protocol-area [s] "Calculate the area of a shape")
  (protocol-perimiter [s] "Calculate the perimiter of a shape"))

(defrecord Rectangle [length width]
  Shape
  (protocol-area [this] (* length width))
  (protocol-perimiter [this] (+ (* 2 length)
                       (* 2 width))))

(defmulti ingest-message
  "Ingest a message into an application"
  (fn [app message]
    (:priority message)) :default :low)

(defmethod ingest-message :low [app message]
  (str "Ingesting message " message ", eventually..."))

(defmethod ingest-message :high [app message]
  (str "Ingesting message " message ", now."))
