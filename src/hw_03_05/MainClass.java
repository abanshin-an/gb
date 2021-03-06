package hw_03_05;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
Перенести приведенный ниже код в новый проект, где мы организуем гонки.
Все участники должны стартовать одновременно, несмотря на разное время подготовки.
В тоннель не может одновременно заехать больше половины участников (условность).
Попробуйте все это синхронизировать.
Первый участник, пересекший финишную черту, объявляется победителем (в момент пересечения этой самой черты).
Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
Когда все завершат гонку, нужно выдать объявление об окончании.
Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из пакета java.util.concurrent.

ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
Участник #2 готовится
Участник #1 готовится
Участник #4 готовится
Участник #3 готовится
Участник #2 готов
Участник #4 готов
Участник #1 готов
Участник #3 готов
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!
Участник #2 начал этап: Дорога 60 метров
Участник #4 начал этап: Дорога 60 метров
Участник #3 начал этап: Дорога 60 метров
Участник #1 начал этап: Дорога 60 метров
Участник #1 закончил этап: Дорога 60 метров
Участник #3 закончил этап: Дорога 60 метров
Участник #3 готовится к этапу(ждет): Тоннель 80 метров
Участник #1 готовится к этапу(ждет): Тоннель 80 метров
Участник #1 начал этап: Тоннель 80 метров
Участник #3 начал этап: Тоннель 80 метров
Участник #4 закончил этап: Дорога 60 метров
Участник #4 готовится к этапу(ждет): Тоннель 80 метров
Участник #2 закончил этап: Дорога 60 метров
Участник #2 готовится к этапу(ждет): Тоннель 80 метров
Участник #3 закончил этап: Тоннель 80 метров
Участник #1 закончил этап: Тоннель 80 метров
Участник #2 начал этап: Тоннель 80 метров
Участник #4 начал этап: Тоннель 80 метров
Участник #3 начал этап: Дорога 40 метров
Участник #1 начал этап: Дорога 40 метров
Участник #3 закончил этап: Дорога 40 метров
Участник #3 - WIN
Участник #1 закончил этап: Дорога 40 метров
Участник #4 закончил этап: Тоннель 80 метров
Участник #4 начал этап: Дорога 40 метров
Участник #2 закончил этап: Тоннель 80 метров
Участник #2 начал этап: Дорога 40 метров
Участник #2 закончил этап: Дорога 40 метров
Участник #4 закончил этап: Дорога 40 метров
ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!

 */
public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch startLatch=new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch finishLatch=new CountDownLatch(CARS_COUNT);
    private static final Random random=new Random();
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + random.nextInt(10));
        }

        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            startLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            finishLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

