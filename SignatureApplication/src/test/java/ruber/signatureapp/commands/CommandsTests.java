package ruber.signatureapp.commands;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ruber.core.model.TeachingList;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.signatureapp.fake.Professors;
import ruber.signatureapp.fake.Teachings;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;
import ruber.signatureapp.views.Command;

public class CommandsTests {

    private RuberFrameViewModel frame;

    @Before
    public void setUp() {
        frame = Mockito.mock(RuberFrameViewModel.class);
    }

    @Test
    public void executingSignTeachingsCommandShouldSignTeachingsSelected() {
        final SignedTeachingsSaver saver = Mockito.mock(SignedTeachingsSaver.class);
        final TeachingList teachings = Teachings.fsoList();
        final Command command = new SignTeachingsCommand(null, saver);

        Mockito.when(frame.getProfessorFromSession()).thenReturn(Professors.ruben());
        //Mockito.when(frame.getSelectedProfessor()).thenReturn(Professors.ruben());
        Mockito.when(frame.getSelectedTeachings()).thenReturn(teachings);
        command.execute();

        //Mockito.verify(saver).save(Professors.ruben(), teachings, signatureImage);
        teachings.forEach(teaching -> Assert.assertThat(teaching.getProfessorForWhomSigned(Professors.ruben()), Is.is(Professors.ruben())));
    }
}
