package hw_02_01;

public class DayOfWeekMain {

    public static void main(String[] args) {
        final String F="\n%s %d working hours ";
        System.out.printf(F,DayOfWeek.MONDAY,DayOfWeek.MONDAY.getWorkingHours());
        System.out.printf(F,DayOfWeek.FRIDAY+" "+DayOfWeek.FRIDAY.getWorkingHours());
    }
}