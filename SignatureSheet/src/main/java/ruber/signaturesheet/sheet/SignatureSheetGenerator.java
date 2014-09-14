package ruber.signaturesheet.sheet;

import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;

import java.io.File;
import java.util.List;

public interface SignatureSheetGenerator {

    public List<File> generate(TeachingList list, ProfessorList professors);
}
