package ruber.signaturesheet.report;

import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.Teaching;
import ruber.core.model.TeachingList;

import java.util.Map;

public class TeachingsReporter {

    private TeachingList teachings;
    private String numberOfTeachingsSigned;
    private String unsignedTeachings;
    private String replacements;
    private String professorsThatDidNotSigned;

    public String generate(TeachingList teachings) {
        this.teachings = teachings;
        initAttributes();
        checkIfTeachingsWereSigned();
        checkProfessorsThatDidNotSigned();
        checkIfThereWereReplacements();
        return report();
    }

    private void initAttributes() {
        numberOfTeachingsSigned = "";
        unsignedTeachings = "";
        professorsThatDidNotSigned = "";
        replacements = "";
    }

    private void checkIfTeachingsWereSigned() {
        int numberOfTeachings = 0;
        int numberOfSignedTeachings = 0;
        for (Teaching teaching : teachings) {
            numberOfTeachings++;
            if (teaching.isSigned())
                numberOfSignedTeachings++;
            else
                addUnsignedTeaching(teaching);
        }
        if (numberOfSignedTeachings != numberOfTeachings) {
            numberOfTeachingsSigned = "Docencia firmada " + numberOfSignedTeachings + "/" + numberOfTeachings;
            unsignedTeachings = "Docencia no firmada:\n" + unsignedTeachings;
        }
        else
            numberOfTeachingsSigned = "Toda la docencia fue firmada";
    }

    private void addUnsignedTeaching(Teaching teaching) {
        unsignedTeachings += "- " + teaching.getSubjectName() + ", " + teaching.getStringSchedule() + ", " + teaching.getGroup() + ", " + teaching.getDegree() + "\n";
        teaching.getProfessors().forEach(professor -> unsignedTeachings += "\t" + professor.getName() + "\n");
    }

    private void checkProfessorsThatDidNotSigned() {
        for (Teaching teaching : teachings) {
            if (!teaching.isSigned()) continue;
            teaching.getSignatures().entrySet().forEach(entry -> {
                if (entry.getValue() == null)
                    professorsThatDidNotSigned += "- " + entry.getKey().getName() + ", " + teaching.getSubjectName() + ", " + teaching.getStringSchedule() + ", " + teaching.getGroup() + ", " + teaching.getDegree() + "\n";
            });
        }
        if (professorsThatDidNotSigned.isEmpty())
            professorsThatDidNotSigned = "Todos los profesores firmaron";
        else
            professorsThatDidNotSigned = "Profesores que no firmaron su docencia: \n" + professorsThatDidNotSigned;
    }

    private void checkIfThereWereReplacements() {
        for (Teaching teaching : teachings) {
            for (Map.Entry<Professor,Signature> professorSignatureEntry : teaching.getSignatures().entrySet()) {
                if (professorSignatureEntry.getValue() == null) continue;
                if (professorSignatureEntry.getKey().equals(professorSignatureEntry.getValue().getProfessor())) continue;
                replacements += replacement(teaching, professorSignatureEntry) + "\n";
            }
        }
        if (!replacements.equals(""))
            replacements = "Sustituciones:\n" + replacements;
    }

    private String replacement(Teaching teaching, Map.Entry<Professor, Signature> professorSignatureEntry) {
        String replacement = "- " + teaching.getSubjectName() + ", " + teaching.getStringSchedule() + ", " + teaching.getGroup() + ", " + teaching.getDegree() + "\n";
        replacement += "\t" + professorSignatureEntry.getKey().getName() + " sustituido por " + professorSignatureEntry.getValue().getProfessor().getName();
        return replacement;
    }

    private String report() {
        return numberOfTeachingsSigned +
                (unsignedTeachings.isEmpty() ? "" : "\n\n" + unsignedTeachings) +
                "\n\n" + professorsThatDidNotSigned +
                (replacements.isEmpty() ? "" : "\n\n" + replacements);
    }
}
