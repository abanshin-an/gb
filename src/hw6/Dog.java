package hw6;

public class Dog extends Animal {
    static private int dogs=0;
    Dog(String name) {
        super(name);
        dogs++;
    }
    @Override
    public void run(int distance){
        distance=Math.min(distance,500);
        super.run(distance);
    }
    @Override
    public void swim(int distance){
        distance=Math.min(distance,10);
        super.swim(distance);
    }

    public static void printDogs(){
        System.out.println("Создано "+dogs+" собак");
    }


}
