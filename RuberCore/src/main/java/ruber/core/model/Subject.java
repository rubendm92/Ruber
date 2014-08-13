package ruber.core.model;

public class Subject {

    private final String name;
    private final String degree;

    public Subject(String name, String degree) {
        this.name = name;
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public boolean equals(Object object) {
        return isSubject(object) && subjectsAreEquals((Subject) object);
    }

    private boolean isSubject(Object object) {
        return (object != null && getClass() == object.getClass());
    }

    private boolean subjectsAreEquals(Subject subject) {
        return ((name.equals(subject.name)) && degree.equals(subject.degree));
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + degree.hashCode();
        return result;
    }

    public String getFormattedName() {
        return name.substring(name.indexOf("-") + 1, name.lastIndexOf("(") - 1);
    }
}
