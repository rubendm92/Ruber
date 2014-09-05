package ruber.core.model;

public class Professor {

    private final String dni;
    private final String name;
    private final String email;

    public Professor(String dni, String name, String email) {
        this.dni = dni;
        this.name = name;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object object) {
        return isProfessor(object) && professorsAreEquals((Professor) object);
    }

    private boolean isProfessor(Object object) {
        return (object != null && getClass() == object.getClass());
    }

    private boolean professorsAreEquals(Professor professor) {
        return dni.equals(professor.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }
}