(ns jogo-velha.async
  (:require [clojure.core.async :as a :refer [<! >! go go-loop put! poll!]]))

(def main-chanel  (a/chan))

(put! main-chanel "teste asyc")

(poll! main-chanel)

(go-loop [messages []]
  (let [new-messages [(<! main-chanel)]]
    (println messages)
    (recur (conj messages new-messages))))