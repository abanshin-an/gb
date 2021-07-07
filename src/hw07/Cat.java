package hw07;

import java.util.Random;

public class Cat {
    final public static int MAX_APPETITE = 5;
    static Random r = new Random();
    private String name;
    private int appetite;
    private boolean hungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        while (appetite <= 0)
            appetite = r.nextInt(MAX_APPETITE);
        this.appetite = appetite;
    }

    public boolean eat(Plate plate) {
        hungry = !plate.takeFood(appetite);
        if (!hungry)
            System.out.println("Кот " + name + " cъел " + appetite + " единиц еды");
        else
            System.out.println("Кот " + name + " остался голодным");
        return !hungry;
    }

    public boolean isHungry() {
        return hungry;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", hungry=" + hungry +
                '}';
    }
}
