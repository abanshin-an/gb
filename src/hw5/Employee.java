package hw5;
// 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
//
public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;
//2. Конструктор класса должен заполнять эти поля при создании объекта.
//

    public Employee(String name, String position, String email, String phone, double salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

//3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
//
    @Override
    public String toString() {
        return "Employee{" +
                "  ФИО='" + name + '\'' +
                ", должность='" + position + '\'' +
                ", email='" + email + '\'' +
                ", телефон='" + phone + '\'' +
                ", зарлпата=" + String.format("%.2f",salary) +
                ", возраст=" + age +
                '}';
    }

//4. Создать массив из 5 сотрудников.
//
    public static Employee[] buildArray() {
//Пример:
//Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
//persArray[0] = new Person( "Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
//persArray[1] = new Person(...);
//...
//persArray[4] = new Person(...);
        Employee[] e= new Employee[5];
        e[0]=new Employee("Ivanov Ivan", "Engineer", "iv.ivan@mailbox.com", "892312312", 30000, 30);
        e[1]=new Employee("Pertov Petr", "Programmer", "pe.petr@mailbox.com", "892312313", 40000, 25);
        e[2]=new Employee("Smirnov Serg", "Manager", "serg.smir@mailbox.com", "892312334", 50000, 41);
        e[3]=new Employee("Sidorov Sidor", "CIO", "si.sidorov@mailbox.com", "892314345", 60000, 45);
        e[4]=new Employee("Andrianov Andrey", "Chief", "an.andrianov@mailbox.com", "892314356", 70000, 50);
        return e;
    }

//5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
    public static void print40(Employee[] e) {
        for( Employee p : e ){
            if (p.age>40)
                System.out.println(p);
        }
    }
}
