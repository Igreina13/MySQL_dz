package animals.pets;

import animals.AbstractAnimal;

public class Dog extends AbstractAnimal {


    public Dog(String name, int age, float weight, String color, int dbId) {
        super(name, age, weight, color, dbId);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }

}