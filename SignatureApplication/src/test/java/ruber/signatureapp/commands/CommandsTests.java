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
import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

public class CommandsTests {

    private FrameViewModel frame;

    @Before
    public void setUp() {
        frame = Mockito.mock(FrameViewModel.class);
    }

    @Test
    public void executingCloseSessionCommandShouldCallToClearFrame() {
        Command command = new CloseSessionCommand(frame);

        command.execute();

        Mockito.verify(frame).clear();
    }

    @Test
    public void executingShowTeachingsCommandShouldShowTeachingsForProfessorFromSession() {
        Command command = new ShowTeachingsCommand(frame, Teachings.longList());

        Mockito.when(frame.getProfessorFromSession()).thenReturn(Professors.ruben());
        command.execute();

        Mockito.verify(frame).showTeachings(Teachings.fsoList());
    }

    @Test
    public void executingShowProfessorsCommandShouldShowAllProfessorsWithTeachingNow() {
        Command command = new ShowProfessorsCommand(frame, Teachings.nowList());

        command.execute();

        Mockito.verify(frame).showProfessors(Professors.rubenList());
    }

    @Test
    public void executingShowTeachingsToReplaceCommandShouldShowAllTeachingsUnsignedForAProfessor() {
        Command command = new ShowTeachingsToReplaceCommand(frame, Teachings.longList());

        Mockito.when(frame.getProfessorToReplace()).thenReturn(Professors.replacement());
        command.execute();

        Mockito.verify(frame).showProfessorToReplace();
        Mockito.verify(frame).showTeachings(Teachings.is2List());
    }

    @Test
    public void executingSignTeachingsCommandShouldSignTeachingsSelected() {
        final SignedTeachingsSaver saver = Mockito.mock(SignedTeachingsSaver.class);
        final TeachingList teachings = Teachings.fsoList();
        final Command command = new SignTeachingsCommand(frame, saver);

        Mockito.when(frame.getProfessorFromSession()).thenReturn(Professors.ruben());
        Mockito.when(frame.getSelectedProfessor()).thenReturn(Professors.ruben());
        Mockito.when(frame.getSelectedTeachings()).thenReturn(teachings);
        command.execute();

        Mockito.verify(saver).save(Professors.ruben(), teachings);
        teachings.forEach(teaching -> Assert.assertThat(teaching.getProfessorForWhomSigned(Professors.ruben()), Is.is(Professors.ruben())));
    }
}
