(ns jogo-velha.core)

(defn new-board
  [nx]
  (as-> (range nx) result
        (map (fn [x]
               (apply vector (map (fn [x] nil) (range nx))))
             result)
        (apply vector result)))

(defn diagonal-win?
  [board]
  (loop [size (count board)]
    (when (>= size 0)
      (recur ()))))

(defn transversal-win?
  [board start stop interact]
  (loop [c start]
    (if (= c stop)
      c
      (do
          (recur (interact c)))
      )
    )
  )

(defn coord-free?
  [x y board]
  (as-> (get-in board [x y]) result
        (contains? #{"x" "o"} result)
        (not result)))

(defn play
  [x y board player]
  (if (coord-free? x y board)
    (assoc-in board [x y] player)
    (println "Posição já utilizada")))

(defn row-win?
  [board-row]
  (-> (set board-row)
      (count)
      (= 1)))

(defn horizontal-win?
  [board]
  (some row-win? board))

(defn vertical-win?
  [board]
  (horizontal-win? (apply mapv vector board)))

(defn winner?
  [board]
  (if (horizontal-win? board)
    (println "Winner!!!")
    false))

(transversal-win? (new-board 3) 0 2 inc)