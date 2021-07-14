package hw6;

public class Animal {

    static private int animals=0;
    protected String name;
    Animal(String name){
        this.name=name;
        animals++;
    }

    public void run(int distance){
        System.out.println(name+" пробежал "+distance+" м ");
    }

    public void swim(int distance){
        System.out.println(name+" проплыл "+distance+" м ");
    }

    public static void printAnimals(){
        System.out.println("Создано "+animals+" животных");
    }

}
