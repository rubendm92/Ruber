package ruber.core.model;

import java.time.LocalTime;

public class Schedule {

    private static final int TWO_HOURS = 7200;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public boolean canBeSigned(LocalTime time) {
        return (Math.abs(getStartTime().toSecondOfDay() - time.toSecondOfDay()) <= TWO_HOURS);
    }
}