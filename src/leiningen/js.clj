(ns leiningen.js
  (:import java.io.File)
  (:require [clojure.java.io :refer [file make-parents]]
            [lein-js.closure :as closure]))

(defn- add-path
  [path file]
  (str path File/separator file))

(defn- src-path
  [project]
  (add-path (:root project)
            (or (:src (:js project)) "src/js")))

(defn- deploy-path
  [project]
  (add-path (:root project)
            (or (:deploy (:js project)) "war/js")))

(defn- compile-bundle
  [inputs output project devel]
  (let [js-settings (:js project)
        input-paths (map #(add-path (src-path project) %) inputs)
        output-path (add-path (deploy-path project) output)
        options (merge {:pretty-print devel}
                       (:options js-settings)
                       (if devel
                         (:devel-options js-settings)
                         (:prod-options js-settings)))]
    (make-parents (file output-path))
    (println "Compiling" (apply str (interpose ", " inputs)) "...")
    (closure/run input-paths output-path options)))

(defn- full-path [project path]
  (str (get-in project [:js :src]) File/separator path))

(defn- find-js-files [project path]
  (if (.endsWith ".js" path)
    (full-path project  path)
    (let [f (File. (full-path project path))]
      (if (.isDirectory f)
        (find-js-files project (.getAbsolutePath f))))))

(defn- parse-inputs [project inputs]
  (for [input inputs]
    (cond
     (.endsWith input ".js")
     input

     (.isDirectory (File. (full-path project input)))
     (remove empty? (flatten (find-js-files project input)))

     :else
     (throw (Exception. (str "invalid Js input:"))))))

(defn js
  ([project] (js project "devel"))
  ([project action]
   (let [bundles (partition 2 (:bundles (:js project)))
         devel (not= action "prod")]
     (doseq [[output inputs] bundles]
       #_(println "output" output "inputs:" (parse-inputs project inputs))
       (compile-bundle inputs output project devel)))))
