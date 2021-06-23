package hw02;

import static java.lang.Math.random;

public class HomeWorkApp02 {
    public static void main(String[] args) {
        int a = (int) (random() * 15);
        int b = (int) (random() * 15);
        System.out.println(" task1(" + a + "," + b + ")=" + task1(a, b));
        a = (int) (random() * 30 - 15);
        task2(a);
        System.out.println(" task3(" + a + ") " + task3(a));
        task4((int) (random() * 5), "Строка");
        System.out.println(" 2020 is leap year :" + task5(2020));
        System.out.println(" 2021 is leap year :" + task5(2021));
        System.out.println(" 2000 is leap year :" + task5(2000));
        System.out.println(" 2100 is leap year :" + task5(2100));
        System.out.println(" 1000 is leap year :" + task5(1000));
        System.out.println(" 1900 is leap year :" + task5(1900));
        System.out.println(" 1600 is leap year :" + task5(1600));
    }

    private static boolean task1(int a, int b) {
//  Написать метод, принимающий на вход два целых числа и проверяющий,
//  что их сумма лежит в пределах от 10 до 20 (включительно),
//  если да – вернуть true, в противном случае – false.
        int s = a + b;
        return (10 <= s && s <= 20);
// :-)
//        if (10<=s&&s<=20) {
//            return true;
//        }
//        else {
//            return false;
//        }
    }

    private static void task2(int a) {
//  Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
//  положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
        System.out.println(" " + a + " " + (a >= 0 ? "положительное" : "отрицательное") + " число");
//  решение в несколько строк ;-)
//        String r = "положительное";
//        if (a < 0) {
//            r = "отрицательное";
//        }
//        System.out.println(" " + a + " " + r + " число");
    }

    private static boolean task3(int a) {
//  Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
//  если число отрицательное, и вернуть false если положительное.
        return a < 0;
    }

    private static void task4(int r, String s) {
//  Написать метод, которому в качестве аргументов передается строка и число,
//  метод должен отпечатать в консоль указанную строку, указанное количество раз;
        for (int i = 0; i < r; i++) {
            System.out.println(s);
        }
    }

    private static boolean task5(int year) {
//* Написать метод, который определяет, является ли год високосным,
// и возвращает boolean (високосный - true, не високосный - false).
// Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
//        System.out.println("year "+year);
        boolean r = (year % 4) == 0;
        r &= ((year % 100) != 0);
//        System.out.println("(year % 100)"+(year % 100));
        r |= ((year % 400) == 0);
//        System.out.println("(year % 400)"+(year % 400));
        return r;
    }

}
