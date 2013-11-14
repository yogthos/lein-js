(defproject lein-js "0.1.2-SNAPSHOT"
  :description "A Leiningen plugin for concatenating and compiling JavaScript files using Google's Closure Compiler. "
  ;; Depends on the Google Closure Compiler, but it is not available on a Maven repo.
  ;; See http://code.google.com/p/closure-compiler/issues/detail?id=37
  ;; If it becomes available, I'll update this dependency to the official version.
  :dependencies [[org.clojars.maravillas/closure-compiler "1.0.0-SNAPSHOT"]]
  :dev-dependencies [[org.clojure/clojure "1.5.1"]])
