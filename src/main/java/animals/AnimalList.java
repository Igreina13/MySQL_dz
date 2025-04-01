package animals;

import misc.Templates;

import java.util.*;

public class AnimalList {

    private ArrayList<AbstractAnimal> listAnimals = new ArrayList<>();
    private ArrayList<AbstractAnimal> listOfFoundAnimals = new ArrayList<>();

    //булева переменная, отвечающая далее на вопрос - были ли обновления в таблице, надо ли ее заново считывать в listAnimals
    public static boolean ifUpdated = true;

    public void setListAnimals(AbstractAnimal animal) {
        listAnimals.add(animal);
    }

    public ArrayList<AbstractAnimal> getListOfFoundAnimals() {
        return listOfFoundAnimals;
    }

    public void setListOfFoundAnimals(AbstractAnimal animal) {
        listOfFoundAnimals.add(animal);
    }

    public void printListAnimals() {

        if (listAnimals.isEmpty()) System.out.println("Список животных пуст.");
        else listAnimals.forEach(thisAnimal ->
                System.out.println(thisAnimal.getClass().getSimpleName() + "#" + thisAnimal)
        );

    }

    public void printTableListAnimals() {
        printTableListAnimals(listAnimals, "id"); //дефолтный список и далее через оверлоад
    }

    public void printTableListAnimals(ArrayList<AbstractAnimal> listAn, String highlightColumn) {

        LinkedHashMap<String, String> columns = new Templates().tableTemplate();
        int tableWidth = 68;

        String th = String.format(
                String.join(" | ", columns.values()), //из значений Map собираем шаблон
                columns.keySet().toArray() //а из ключей собираем массив аргументов для .format
        )
                + "\n" + "=".repeat(tableWidth);

        if (!Objects.isNull(listAn)) {
            if (listAn.isEmpty()) System.out.println("Список животных пуст.");
            else {
                System.out.println("\u001b[1m" + th + "\u001B[0m");
                listAn.forEach(thisAnimal -> System.out.println(
                        thisAnimal.toTableTr(highlightColumn)
                                + "\n" + "-".repeat(tableWidth))
                );
            }
        }


    }


    public StringBuilder countAnimals() {

        Map<String, Integer> animalTypes = new HashMap<>();
        //ещё чуть-чуть рефлексии для счётчика.
        listAnimals.forEach(
                thisAnimal -> {
                    String thisClass = thisAnimal.getClass().getSimpleName();
                    if (!animalTypes.containsKey(thisClass))
                        animalTypes.put(thisClass, 1);
                    else
                        animalTypes.put(thisClass, animalTypes.get(thisClass) + 1);
                }
        );

        StringBuilder countStr = new StringBuilder("\n| Всего животных: " + (listAnimals.size()) + " | ");
        animalTypes.forEach((key, value) -> countStr.append(key).append(": ").append(value).append(" | "));


        return countStr;

    }
}

