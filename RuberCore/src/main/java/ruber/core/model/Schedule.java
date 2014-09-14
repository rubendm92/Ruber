package ruber.core.model;

import java.time.LocalTime;

public class Schedule {

    private static final int ONE_HOUR = 60 * 60;

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public Schedule(LocalTime startTime, LocalTime endTime, String classroom) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public String getClassroom() {
        return classroom;
    }

    @Override
    public boolean equals(Object object) {
        return isSchedule(object) && schedulesAreEquals((Schedule) object);
    }

    private boolean isSchedule(Object object) {
        return (object != null && getClass() == object.getClass());
    }

    private boolean schedulesAreEquals(Schedule schedule) {
        return ((startTime.equals(schedule.startTime)) && (endTime.equals(schedule.endTime)) && (classroom.equals(schedule.classroom)));
    }

    @Override
    public int hashCode() {
        int result = startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + classroom.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return startTime.toString() + "-" + endTime.toString();
    }

    public boolean canBeSigned(LocalTime time) {
        return oneHourBeforeStart(time) || betweenSchedule(time) ||oneHourAfterEnd(time);
    }

    private boolean oneHourBeforeStart(LocalTime time) {
        return (Math.abs(startTime.toSecondOfDay() - time.toSecondOfDay()) <= ONE_HOUR);
    }

    private boolean betweenSchedule(LocalTime time) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    private boolean oneHourAfterEnd(LocalTime time) {
        return (Math.abs(endTime.toSecondOfDay() - time.toSecondOfDay()) <= ONE_HOUR);
    }
}