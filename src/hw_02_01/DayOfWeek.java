package hw_02_01;

enum DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    private final int WEEK_WORKHOURS = 40;

    public int getWorkingHours() {
        if ( MONDAY.ordinal() <= this.ordinal() && this.ordinal() <= FRIDAY.ordinal()) {
            return WEEK_WORKHOURS - (this.ordinal()-1)*8;
        }
        return 0;
    };
}
