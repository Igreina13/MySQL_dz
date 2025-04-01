package db.tables;

import db.IDBConnect;
import db.MySQLConnect;

import java.sql.ResultSet;
import java.util.List;

abstract class AbstractTable implements ITable {
    protected IDBConnect dbConnect;
    private String tableName;

    public AbstractTable(String tableName) {
        dbConnect = new MySQLConnect();
        this.tableName = tableName;
    }

    @Override
    public void create(List<String> columns) {
        drop();
        dbConnect.execute(String.format("CREATE TABLE IF NOT EXISTS %s(%s);", tableName, String.join(",", columns)));
    }

    @Override
    public void drop() {
        dbConnect.execute(String.format("DROP TABLE  IF EXISTS %s;", tableName));
    }

    @Override
    public ResultSet selectAll() {
        return dbConnect.executeQuery(String.format("SELECT * FROM %s;", tableName));
    }

    @Override
    public ResultSet selectWhereId(int id) {
        return dbConnect.executeQuery(String.format("SELECT * FROM %s WHERE id = " + id + ";", tableName));
    }

    @Override
    public ResultSet selectQ(String query) {
        return dbConnect.executeQuery(String.format(query, tableName));
    }

    //String[] columns - какие колонки выводить
    //String... predicates - по каким условиям, можно передавать произвольное количество переменных или массив с переменными
    @Override
    public ResultSet selectTemplate(String[] columns, String... predicates) {


        String requestColumns, requestPredicates;

        if (columns == null || columns.length == 0) requestColumns = "*";
        else requestColumns = String.join(", ", columns);

        if (predicates == null || predicates.length == 0) requestPredicates = "";
        else requestPredicates = String.format("WHERE %s", String.join(" AND ", predicates));
        return dbConnect.executeQuery(String.format("SELECT %s FROM %s %s", requestColumns, tableName, requestPredicates));
    }
}
