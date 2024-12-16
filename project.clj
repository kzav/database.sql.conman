(defproject ten-dimensions/database.sql.conman "0.1.0"
  :description "Integrant multimethods for setting up a conman database connection management and SQL query generation library for
the Duct framework."
  :url "https://github.com/kzav/database.sql.conman"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.logging "1.2.4"]
                 [duct/core "0.8.0"]
                 [duct/logger "0.3.0"]
                 [duct/database.sql "0.1.0"]
                 [integrant "0.8.0"]
                 [conman "0.9.6"]]
  :profiles
  {:dev {:dependencies [[eftest "0.5.9"]
                        [org.xerial/sqlite-jdbc "3.36.0.3"]
                        [org.slf4j/slf4j-nop "1.7.32"]
                        [org.clojure/java.jdbc "0.7.12"]]}}
  :repl-options {:init-ns duct.database.sql.conman}
  :plugins [[lein-ancient "0.6.14"]])
