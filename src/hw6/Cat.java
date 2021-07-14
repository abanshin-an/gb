package hw6;

public class Cat extends Animal {
    static int cats=0;
    Cat(String name) {
        super(name);
        cats++;
    }
    @Override
    public void run(int distance){
        distance=Math.min(distance,200);
        super.run(distance);
    }
    @Override
    public void swim(int distance){
        System.out.println("Кот "+name+" не умеет плавать");
    }

    public static void printCats(){
        System.out.println("Создано "+cats+" кошек");
    }
}
