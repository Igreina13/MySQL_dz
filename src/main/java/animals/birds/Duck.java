package animals.birds;

import animals.AbstractAnimal;

public class Duck extends AbstractAnimal implements IFlying {

    public Duck(String name, int age, float weight, String color, int dbId) {
        super(name, age, weight, color, dbId);
    }

    @Override
    public void say() {
        System.out.println("Кря");
    }

}
