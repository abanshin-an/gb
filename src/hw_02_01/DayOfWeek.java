package hw_02_01;

enum DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;


    public String getWorkingHours() {
        final int WEEK_WORKHOURS = 40;
        if ( MONDAY.ordinal() <= this.ordinal() && this.ordinal() <= FRIDAY.ordinal()) {
            return ""+( WEEK_WORKHOURS - (this.ordinal()-1)*8)+" рабочих часов";
        }
        return " Сегодня выходной";
    }
}
