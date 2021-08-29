package hw_03_01;

import java.util.*;

/*
1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);

2. Написать метод, который преобразует массив в ArrayList;

3. Большая задача:

a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну
    коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
    которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
    (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
    (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно
    в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    private static final Random r;
    private static final int MAX_BOX_CAPACITY;

    static {
        MAX_BOX_CAPACITY = 10;
        r = new Random();
    }


    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3, 4);
        System.out.println("before swap l1:" + l1);
        swap(l1, 0, 1);
        System.out.println("after swap l1:" + l1);

        String[] l2 = new String[]{"apple", "banana", "orange"};
        System.out.println("String[] l2:" + Arrays.toString(l2));
        List<String> fruitList=Main.arrayToList(l2);
        System.out.println("array list l2:" + fruitList);
        Box<Orange> orangeBox= new Box<>();
        Box<Orange> orangeBox1= new Box<>();
        Box<Apple> appleBox=new Box<>();

        for (int i=0; i<=r.nextInt(MAX_BOX_CAPACITY);i++) {
            orangeBox.putInBox(new Orange());
        }
        System.out.printf("%nWeight of orangeBox %2.1f ",orangeBox.calcWeight());
        orangeBox.pour(orangeBox1);
        System.out.printf("%nPour orangeBox->orangeBox1 Weight of orangeBox %2.1f , orangebox1 %2.1f",orangeBox.calcWeight(),orangeBox1.calcWeight());
        for (int i=0; i<=r.nextInt(MAX_BOX_CAPACITY);i++) {
            orangeBox.putInBox(new Orange());
        }
        System.out.printf("%nAdd new oranges to orangeBox. Weight of orangeBox %2.1f , orangebox1 %2.1f",orangeBox.calcWeight(),orangeBox1.calcWeight());
        for (int i=0; i<=r.nextInt(MAX_BOX_CAPACITY);i++) {
            appleBox.putInBox(new Apple());
        }
        System.out.printf("%nWeight of orangeBox(%2.1f) equal to appleBox (%2.1f) => %b ",orangeBox.calcWeight(),appleBox.calcWeight(),orangeBox.compare(appleBox));

    }

    public static <T> List<T> arrayToList(T[] l2) {
        return Arrays.asList( l2);
    }

    public static <T> void swap(List<T> l, int i1, int i2) {
        Collections.swap(l, i1, i2);
    }

}
