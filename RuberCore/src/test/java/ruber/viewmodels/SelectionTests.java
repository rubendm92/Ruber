package ruber.viewmodels;

import org.junit.Test;
import ruber.model.fake.Professors;
import ruber.model.fake.Teachings;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SelectionTests {

    @Test
    public void getSelectedTeachings() {
        Selection selection = new Selection();
        final Teaching fso = new Teaching(Teachings.fso());
        final Teaching is2 = new Teaching(Teachings.is2());
        selection.showTeachings(teachings(fso, is2));
        is2.unselect();
        assertThat(selection.getSelectedTeachings(), is(fsoList(Teachings.fso())));
    }

    private TeachingList teachings(Teaching fso, Teaching is2) {
        TeachingList teachings = new TeachingList();
        teachings.add(fso);
        teachings.add(is2);
        return teachings;
    }

    private ruber.model.TeachingList fsoList(ruber.model.Teaching fso) {
        final ruber.model.TeachingList teachings = new ruber.model.TeachingList();
        teachings.add(fso);
        return teachings;
    }

    @Test
    public void getSelectedProfessor() {
        Selection selection = new Selection();
        final Professor ruben = new Professor(Professors.ruben());
        final Professor replacement = new Professor(Professors.replacement());
        selection.showProfessorsToReplace(professors(ruben, replacement));
        ruben.select();
        assertThat(selection.getSelectedProfessor(), is(Professors.ruben()));
    }

    private ProfessorList professors(Professor professor1, Professor professor2) {
        ProfessorList professors = new ProfessorList();
        professors.add(professor1);
        professors.add(professor2);
        return professors;
    }
}
