package db.tables;

import animals.AbstractAnimal;
import animals.AnimalList;
import data.AnimalTypesData;
import factory.AnimalFactory;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTable extends AbstractTable {
    private static final String TABLENAME = "animals";

    public AnimalTable() {
        super(TABLENAME);
    }

    public void insert(AbstractAnimal animal, String type) {
        dbConnect.execute(String.format("INSERT INTO %s (id, color, name, weight, type, age)"
                        + "VALUES(NULL, '%s', '%s', '%s', '%s', '%s')",
                TABLENAME,
                animal.getColor(),
                animal.getName(),
                animal.getWeight(),
                type.toUpperCase(),
                animal.getAge()
        ));
        switchUpdateStatus(true);
    }

    public void update(String color, String name, float weight, String type, int age, int id) {
        String query = String.format("UPDATE %s SET " +
                        "color = '%s', name = '%s', weight = '%s', type = '%s', " +
                        "age = '%s' WHERE id = %s;",
                TABLENAME, color, name, weight, type, age, id
        );

        dbConnect.execute(query);

        switchUpdateStatus(true);
    }

    @Override
    public void deleteWhereId(int id) {
        String query = String.format("DELETE FROM %s WHERE id = " + id + ";", TABLENAME, id);
        dbConnect.execute(query);

        switchUpdateStatus(true);
    }


    public AnimalList read() throws SQLException {
        //создаем список животных
        AnimalList listAnimals = new AnimalList();

        try (ResultSet resultSet = selectAll()) {
            while (resultSet.next()) {
                //создаём экземпляр фабрики
                AnimalFactory animalFactory = new AnimalFactory();
                //создаём экземпляр дочернего класса через фабрику
                AbstractAnimal createdAnimal = animalFactory.create(
                        AnimalTypesData.valueOf(resultSet.getString("type").toUpperCase()),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getFloat("weight"),
                        resultSet.getString("color"),
                        resultSet.getInt("id")
                );

                //загружаем животного в список животных
                listAnimals.setListAnimals(createdAnimal);

            }
        }

        switchUpdateStatus(false);
        return listAnimals;
    }

}
