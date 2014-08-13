package ruber.core.model;

import java.time.LocalTime;

public class Signature {

    private final Professor professor;
    private final LocalTime signTime;
    private final byte[] signature;

    public Signature(Professor professor, LocalTime signTime, byte[] signature) {
        this.professor = professor;
        this.signTime = signTime;
        this.signature = signature;
    }

    public Professor getProfessor() {
        return professor;
    }

    public byte[] getSignature() {
        return signature;
    }

    public LocalTime getSignTime() {
        return signTime;
    }
}