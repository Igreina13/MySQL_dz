import animals.AnimalList;
import animals.AnimalTools;
import db.MySQLConnect;
import data.MainMenuData;
import db.tables.AnimalTable;
import misc.Loader;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        //Создание списка животных
        AnimalList listAnimals = new AnimalList();
        //Создание таблицы животных для выполнения запросов к ней
        AnimalTable animalTable = new AnimalTable();
        //Создание объекта animalTools для вызова методов изменения объекта
        AnimalTools animalTools = new AnimalTools();
        MySQLConnect mySQLConnect = new MySQLConnect();

        Loader loader = new Loader();


        labelExit:
        for (; ; ) {
            // запрашиваем список из базы на старте и каждом возвращении в главное меню список из таблицы для счётчика
            // если были изменения записи
            try {
                if (AnimalList.ifUpdated) {
                    loader.loader(0);
                    listAnimals = animalTable.read();
                    loader.loader(3);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(listAnimals.countAnimals() + "\n"
                        + "\u001b[36;1mВыберите действие, напечатав команду: \u001B[0m");
                Arrays.stream(MainMenuData.values()).forEach(
                        menuChoice -> System.out.println("\t" + (menuChoice.ordinal() + 1)
                                + " - " + menuChoice.toString().toLowerCase()));
                Scanner console = new Scanner(System.in);
                String command = console.nextLine().toLowerCase().trim();


                var action = MainMenuData.allOptions(command);
                switch (action) {
                    case ADD -> animalTools.addAnimal();
                    case EDIT -> {
                        listAnimals.printTableListAnimals();
                        animalTools.editAnimal();
                    }
                    case LIST -> listAnimals.printListAnimals();
                    case SEARCH -> {
                        try {
                            animalTools.searchAnimal();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case DELETE -> {
                        listAnimals.printTableListAnimals();
                        animalTools.deleteAnimal();
                    }
                    case EXIT -> {
                        System.out.println("Пока Пока!");
                        mySQLConnect.close();
                        break labelExit;
                    }
                }

            } catch (IllegalStateException e) {
                System.out.println("НЕкорректный выбор.");
            }


        }
    }


}


