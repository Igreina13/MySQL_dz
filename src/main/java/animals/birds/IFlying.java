package animals.birds;

public interface IFlying {

    default void fly() {
        System.out.println("Я лечу");
    }
}