(ns joy.chap07-04)

(defn pow [base exp]
  (if (zero? exp)
    1
    (* base (pow base (dec exp)))))

(defn tail-pow [base exp]
  (letfn [(kapow [base exp acc]
            (if (zero? exp)
              acc
              (recur base (dec exp) (* base acc))))]
    (kapow base exp 1)))

(defn elevator [commands]
  (letfn
    [(ff-open [[cmd & r]]
       "When the elevator is open the 1st floor
       it can either close or be done."
       #(case cmd
          :close (ff-closed r)
          :done true
          false))
     (ff-closed [[cmd & r]]
       "When the elevator is closed on the 1st floor
       it can either open or go up."
       #(case cmd
          :open (ff-open r)
          :up (sf-closed r)
          false))
     (sf-closed [[cmd & r]]
       "When the elevator is closed on the 2nd floor
       it can either go down or open."
       #(case cmd
          :down (ff-closed r)
          :open (sf-open r)
          false))
     (sf-open [[cmd & r]]
       "Whe the elevator is open on the 2nd floor
       it can either close or be done."
       #(case cmd
          :close (sf-closed r)
          :done true
          false))]
    (trampoline ff-open commands)))

(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]] size yx))
  ([deltas size yx]
    (filter (fn [new-yx]
              (every? #(< -1 % size) new-yx))
            (map #(map + yx %) deltas))))

(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est
    (- (+ size size) y x 2)))

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost
    (:cost cheapest-nbr 0)))

(defn total-cost [newcost step-cost-est size y x]
  (+ newcost
    (estimate-cost step-cost-est size y x)))

(defn min-by [f coll]
  (when (seq coll)
    (reduce (fn [min this]
              (if (> (f min) (f this)) this min))
             coll)))

(defn astar-1 [start-yx step-est cell-costs]
  (let [size (count cell-costs)]
    (loop [steps 0
           routes (vec (replicate size (vec (replicate size nil))))
           work-todo (sorted-set [0 start-yx])]
      (if (empty? work-todo)
        [(peek (peek routes)) :steps steps]
        (let [[_ yx :as work-item] (first work-todo)
               rest-work-todo (disj work-todo work-item)
               nbr-yxs (neighbors size yx)
               cheapest-nbr (min-by :cost
                                    (keep #(get-in routes %)
                                          nbr-yxs))
               newcost (path-cost (get-in cell-costs yx)
                                  cheapest-nbr)
               oldcost (:cost (get-in routes yx))]
          ;(println rest-work-todo)
          ;(println nbr-yxs)
          ;(println cheapest-nbr)
          ;(println newcost)
          ;(println oldcost)
          (if (and oldcost (>= newcost oldcost))
            (recur (inc steps) routes rest-work-todo)
            (recur (inc steps)
                   (assoc-in routes yx
                             {:cost newcost
                              :yxs (conj (:yxs cheapest-nbr [])
                                         yx)})
                   (into rest-work-todo
                         (map
                            (fn [w]
                                (let [[y x] w]
                                     [(total-cost newcost step-est size y x) w]))
                           nbr-yxs)))))))))


(defn astar [start-yx step-est cell-costs]
  (let [size (count cell-costs)]
    (loop [steps 0
           routes (vec (replicate size (vec (replicate size nil))))
           work-todo (sorted-set [0 start-yx])]
      (if (empty? work-todo)                    ;; #: Check done
        [(peek (peek routes)) :steps steps] ;; #: Grab the first route
        (let [[_ yx :as work-item] (first work-todo) ;; #: Get next work item
              rest-work-todo (disj work-todo work-item) ;; #: Clear from todo
              nbr-yxs (neighbors size yx)    ;; #: Get neighbors
              cheapest-nbr (min-by :cost     ;; #: Calc least-cost
                                   (keep #(get-in routes %) 
                                         nbr-yxs))
              newcost (path-cost (get-in cell-costs yx) ;; #: Calc path so-far
                                 cheapest-nbr)
              oldcost (:cost (get-in routes yx))]
          (if (and oldcost (>= newcost oldcost)) ;; #: Check if new is worse
            (recur (inc steps) routes rest-work-todo)
            (recur (inc steps) ;; #: Place new path in the routes
                   (assoc-in routes yx
                             {:cost newcost 
                              :yxs (conj (:yxs cheapest-nbr []) 
                                         yx)})
                   (into rest-work-todo ;; #: Add the estimated path to the todo and recur
                         (map 
                          (fn [w] 
                            (let [[y x] w]
                              [(total-cost newcost step-est size y x) w]))
                          nbr-yxs)))))))))

