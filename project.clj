(defproject lein-js "0.1.2-SNAPSHOT"
  :description "A Leiningen plugin for concatenating and compiling JavaScript files using Google's Closure Compiler. "
  ;; Depends on the Google Closure Compiler, but it is not available on a Maven repo.
  ;; See http://code.google.com/p/closure-compiler/issues/detail?id=37
  ;; If it becomes available, I'll update this dependency to the official version.
  :dependencies [;[org.clojure/google-closure-library "0.0-20130212-95c19e7f0f5f"]
                 [com.google.javascript/closure-compiler "r1352"]]
  :dev-dependencies [[org.clojure/clojure "1.5.1"]])
