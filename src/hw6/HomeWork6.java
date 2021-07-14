package hw6;

public class HomeWork6 {
    public static void main(String[] args) {
        Animal[] animals = new Animal[5];
        animals[0] = new Cat("Барсик");
        animals[1] = new Cat("Мурзик");
        animals[2] = new Cat("Васька");
        animals[3] = new Dog("Тузик");
        animals[4] = new Dog("Персик");
        for (Animal a : animals) {
            a.swim(10);
            a.run(100);
            a.run(300);
            a.run(600);
        }
        Animal.printAnimals();
        Dog.printDogs();
        Cat.printCats();
    }
}
