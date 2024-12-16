(ns duct.database.sql.conman
  (:require [clojure.java.io :as io]
            [integrant.core :as ig]
            [duct.database.sql :as sql]
            [duct.logger :as log]
            [conman.core :as conman])
  (:import (java.nio.file FileSystems Paths Files)
           (java.util Arrays Collections)))

(defmethod ig/init-key :duct.database.sql/conman
  [_ {:keys [db-spec sql-path]}]
  (let [sqls (map
               #(str sql-path "/" (.toString (.getFileName %)))
               (if sql-path
                 (let [uri (.toURI (io/resource sql-path))
                       scheme (.getScheme uri)
                       resource-path (if (= scheme "jar")
                                       (.getPath (FileSystems/newFileSystem uri (Collections/emptyMap)) sql-path (make-array String 0))
                                       (Paths/get uri))
                       ]
                   (-> resource-path
                       Files/list
                       .sorted
                       .iterator
                       iterator-seq)
                   )
                 []))
        datasource (conman/connect! db-spec)
        args (concat [datasource] sqls)
        queries (apply conman/bind-connection-map args)]
    (sql/->Boundary
      {:datasource datasource
       :queries queries})))

(defmethod ig/halt-key! :duct.database.sql/conman
  [_ {:keys [spec]}]
  (conman/disconnect! (:datasource spec)))
