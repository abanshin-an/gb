package hw_03_01;

import java.util.ArrayList;

public class  Box <T extends Fruit> {

    public void putInBox(T fruit) {
        fruitList.add(fruit);
    }

    public void pour(Box<T> box) {
        for (T fruit: fruitList) {
            box.putInBox(fruit);
        }
        fruitList.clear();
    }

    public Box(){
        fruitList=new ArrayList<>();
    }

    public double calcWeight(){
        double sum=0.0f;
        for (T fruit: fruitList) {
            sum+=fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box<? extends Fruit> box){
        return calcWeight()==box.calcWeight();
    }

    private final ArrayList<T> fruitList;
}
