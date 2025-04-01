package animals;

import misc.Funcs;
import misc.Templates;

import java.util.*;

public abstract class AbstractAnimal {
    private String name;
    private int age;
    private float weight;
    private String color;
    private int id;

    //сеттеры и геттеры по факту не используются, сохранены для соответствия условиям задачи
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //кучка неиспользуемых методов - тоже по условию задачи
    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public void say() {
        System.out.println("Я говорю");
    }


    //Конструктор задан здесь для того, чтоб указать наследникам "как надо".
    protected AbstractAnimal(String name, int age, float weight, String color, int id) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
        this.id = id;
    }

    Funcs miscFuncs = new Funcs();
    Templates templates = new Templates();

    //переопределяем метод toString
    @Override
    public String toString() {

        return "%s -- Привет! меня зовут %s, мне %d %s, я вешу %s кг, мой цвет - %s. "
                .formatted(id, name, age, miscFuncs.agePostfix(age),
                        templates.floatTemplate(weight), color);
    }


    public String toTableTr(String highlightColumn) {

        String type = this.getClass().getSimpleName();
        String highlightTemplate = "\u001B[36m%s\u001B[0m";

        LinkedHashMap<String, String> columns = new Templates().tableTemplate();

        columns.forEach((key, value) -> {
            if (key.equals(highlightColumn))
                columns.replace(key, highlightTemplate.formatted(value));
        });

        return String.format(String.join(" | ", columns.values()),
                id, type, name, age, templates.floatTemplate(weight), color
        );


    }


}
