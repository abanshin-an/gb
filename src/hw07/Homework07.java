package hw07;

import java.util.Random;
//
// +1. Расширить задачу про котов и тарелки с едой.
// +2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды,
// а кот пытается покушать 15-20).
// +3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать
// (хватило еды), сытость = true.
// +4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт
// (это сделано для упрощения логики программы).
// +5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию
// о сытости котов в консоль.
// +6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
//
public class Homework07 {
    static Random r=new Random();
    final static int MAX_FOOD_UNITS = 40;
    public static void main(String[] args) {
        boolean wellFedCats;
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Барсик",r.nextInt(Cat.MAX_APPETITE));
        cats[1] = new Cat("Машка",r.nextInt(Cat.MAX_APPETITE));
        cats[2] = new Cat("Мурзик",r.nextInt(Cat.MAX_APPETITE));
        cats[3] = new Cat("Васька",r.nextInt(Cat.MAX_APPETITE));
        cats[4] = new Cat("Мурка",r.nextInt(Cat.MAX_APPETITE));
        for (Cat cat :cats) {
            System.out.println(cat);
        }
        Plate plate = new Plate();
        do {
            wellFedCats=true;
            plate.putFood(r.nextInt(MAX_FOOD_UNITS));
            System.out.println("В тарелке "+plate.getFood()+" единиц еды");
            for (Cat cat : cats) {
                if (cat.isHungry())
                    wellFedCats &= cat.eat(plate);
            }
        } while (!wellFedCats);
        System.out.println("В тарелке осталось "+plate.getFood()+" единиц еды");

    }
}
