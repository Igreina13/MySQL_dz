package db.tables;

import animals.AnimalList;

import java.sql.ResultSet;
import java.util.List;

public interface ITable {
    void create(List<String> column);

    void drop();

    default void switchUpdateStatus(boolean bool) {
        // для многопользовательской программы логика должна быть совсем иной.
        // может, будет лучше идея, поэтому пусть в отдельном методе лежит
        AnimalList.ifUpdated = bool;

    }

    ResultSet selectAll();

    ResultSet selectWhereId(int id);

    ResultSet selectQ(String query);

    ResultSet selectTemplate(String[] columns, String... predicates);

    void deleteWhereId(int id);

}
