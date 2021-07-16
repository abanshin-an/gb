package hw_02_01;

public class DayOfWeekMain {

    public static void main(String[] args) {
        System.out.println(DayOfWeek.MONDAY+" " +DayOfWeek.MONDAY.getWorkingHours());
        System.out.println(DayOfWeek.FRIDAY+" " +DayOfWeek.FRIDAY.getWorkingHours());
        System.out.println(DayOfWeek.SUNDAY+" " +DayOfWeek.SUNDAY.getWorkingHours());
        System.out.println(DayOfWeek.SATURDAY+" " +DayOfWeek.SATURDAY.getWorkingHours());
    }
}