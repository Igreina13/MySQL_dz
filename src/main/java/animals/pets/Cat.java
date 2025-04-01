package animals.pets;

import animals.AbstractAnimal;

public class Cat extends AbstractAnimal {

    public Cat(String name, int age, float weight, String color, int dbId) {
        super(name, age, weight, color, dbId);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }

}
