# database.sql.conman

Integrant multimethods for setting up a [conman][] database connection management and SQL query generation library for
the [Duct][] framework.

[conman]: https://github.com/luminus-framework/conman
[duct]: https://github.com/duct-framework/duct

## Installation

To install, add the following to your project `:dependencies`:

    [ten-dimensions/database.sql.conman "0.1.0"]

## Usage

This library depends on [database.sql][] and provides the
`:duct.database.sql/conman` key, which is derived from
`:duct.database/sql`.

The key takes the same [config options][] as the Clojure [conman][]
wrapper library, and returns a `duct.database.sql.Boundary` record
that contains a database spec.

```edn
{:duct.database.sql/conman {:db-spec {:jdbc-url "jdbc:sqlite:db/example.sqlite"}
                            :sql-path "sql"}}
```

[database.sql]:   https://github.com/duct-framework/database.sql
[config options]: https://github.com/tomekw/hikari-cp#configuration-options
[conman]: https://github.com/luminus-framework/conman

## License

Copyright © 2023 Kazushiro ABO

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
