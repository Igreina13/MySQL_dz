package db;

import java.sql.ResultSet;

public interface IDBConnect {
    void execute(String sqlRequest);

    ResultSet executeQuery(String sqlRequest);
}
