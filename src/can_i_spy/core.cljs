(ns can-i-spy.core
    (:require sc.api))

(enable-console-print!)

(println "This text is printed from src/can-i-spy/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn haversine
  [x]
  (let [s (Math/sin (/ (double x) 2.0))]
    (* s s)))

(def distance
  (let [earth-radius 6.371e6
        radians-per-degree (/ Math/PI 180.0)]
    (fn [p1 p2]
      (let [[lat1 lng1] p1
            [lat2 lng2] p1
            phi1 (* lat1 radians-per-degree)
            lambda1 (* lng1 radians-per-degree)
            phi2 (* lat2 radians-per-degree)
            lambda2 (* lng2 radians-per-degree)]
        (sc.api/spy
          (* 2 earth-radius
             (Math/asin
               (Math/sqrt
                 (+ (haversine (- phi2 phi1))
                    (* (Math/cos phi1)
                       (Math/cos phi2)
                       (haversine (- lambda2 lambda1))))))))))))

(def Paris [48.8566 2.3522])
(def New-York [40.7134 -74.0055])
(def Athens [37.9838 23.7275])

(println "New-York Athens" (distance Paris New-York))
(println "Paris Athens" (distance Paris Athens))
(println "New-York Athens" (distance New-York Athens))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
