(ns duct.database.sql.conman-test
  (:require [clojure.test :refer :all]
            [duct.core :as duct]
            [duct.database.sql :as sql]
            [duct.database.sql.conman :refer :all]
            [integrant.core :as ig]))

(duct/load-hierarchy)

(deftest connection-test
  (testing "jdbc-url without SQL"
    (let [config  {::sql/conman {:db-spec {:jdbc-url "jdbc:sqlite:"}}}
          system  (ig/init config)
          conman  (::sql/conman system)]
      (is (instance? duct.database.sql.Boundary conman))
      (let [datasource (-> conman :spec :datasource)
            queries (-> conman :spec :queries :fns)]
        (is (instance? javax.sql.DataSource datasource))
        (is (not (.isClosed datasource)))
        (is (= (count queries) 0))
        (ig/halt! system)
        (is (.isClosed datasource)))))

  (testing "jdbc-url with SQL"
    (let [config  {::sql/conman {:db-spec {:jdbc-url "jdbc:sqlite:"}
                                 :sql-path "sql"}}
          system  (ig/init config)
          conman  (::sql/conman system)]
      (is (instance? duct.database.sql.Boundary conman))
      (let [datasource (-> conman :spec :datasource)
            queries (-> conman :spec :queries :fns)]
        (is (instance? javax.sql.DataSource datasource))
        (is (not (.isClosed datasource)))
        (is (= (count queries) 1))
        (ig/halt! system)
        (is (.isClosed datasource))))))
