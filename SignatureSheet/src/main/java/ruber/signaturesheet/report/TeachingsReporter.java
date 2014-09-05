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

    public String generate(TeachingList teachings) {
        this.teachings = teachings;
        initAttributes();
        checkIfTeachingsWereSigned();
        checkIfThereWereReplacements();
        return report();
    }

    private void initAttributes() {
        numberOfTeachingsSigned = "";
        unsignedTeachings = "";
        replacements = "";
    }

    private void checkIfTeachingsWereSigned() {
        int numberOfTeachings = 0;
        int numberOfSignedTeachings = 0;
        for (Teaching teaching : teachings) {
            for (Map.Entry<Professor,Signature> professorSignatureEntry : teaching.getSignatures().entrySet()) {
                numberOfTeachings++;
                if (professorSignatureEntry.getValue() != null)
                    numberOfSignedTeachings++;
                else
                    addUnsignedTeaching(teaching, professorSignatureEntry.getKey());
            }
        }
        if (numberOfSignedTeachings != numberOfTeachings) {
            numberOfTeachingsSigned = "Docencia firmada " + numberOfSignedTeachings + "/" + numberOfTeachings;
            unsignedTeachings = "Docencia no firmada:\n" + unsignedTeachings;
        }
        else
            numberOfTeachingsSigned = "Toda la docencia fue firmada";
    }

    private void addUnsignedTeaching(Teaching teaching, Professor professor) {
        unsignedTeachings += "- " + teaching.getSubjectName() + ", " + teaching.getStringSchedule() + ", " + professor.getName() + "\n";
    }

    private void checkIfThereWereReplacements() {
        for (Teaching teaching : teachings) {
            for (Map.Entry<Professor,Signature> professorSignatureEntry : teaching.getSignatures().entrySet()) {
                if (professorSignatureEntry.getValue() == null) continue;
                if (professorSignatureEntry.getKey().equals(professorSignatureEntry.getValue().getProfessor())) continue;
                replacements += "- " + professorSignatureEntry.getKey().getName() + " sustituido por " + professorSignatureEntry.getValue().getProfessor().getName() + "\n";
            }
        }
        if (!replacements.equals(""))
            replacements = "Sustituciones:\n" + replacements;
    }

    private String report() {
        return numberOfTeachingsSigned +
                (unsignedTeachings.equals("") ? "" : "\n\n" + unsignedTeachings) +
                (replacements.equals("") ? "" : "\n\n" + replacements);
    }
}
