package ruber.core.commands;

import org.junit.Before;
import org.junit.Test;
import ruber.core.model.TeachingList;
import ruber.core.model.fake.Professors;
import ruber.core.model.fake.Teachings;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.core.view.Command;
import ruber.core.viewmodels.FrameViewModel;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CommandsTests {

    private FrameViewModel frame;

    @Before
    public void setUp() {
        frame = mock(FrameViewModel.class);
    }

    @Test
    public void executingCloseSessionCommandShouldCallToClearFrame() {
        Command command = new CloseSessionCommand(frame);

        command.execute();

        verify(frame).clear();
    }

    @Test
    public void executingShowTeachingsCommandShouldShowTeachingsForProfessorFromSession() {
        Command command = new ShowTeachingsCommand(frame, Teachings.longList());

        when(frame.getProfessorFromSession()).thenReturn(Professors.ruben());
        command.execute();

        verify(frame).showTeachings(Teachings.fsoList());
    }

    @Test
    public void executingShowProfessorsCommandShouldShowAllProfessorsWithTeachingNow() {
        Command command = new ShowProfessorsCommand(frame, Teachings.nowList());

        command.execute();

        verify(frame).showProfessors(Professors.rubenList());
    }

    @Test
    public void executingShowTeachingsToReplaceCommandShouldShowAllTeachingsUnsignedForAProfessor() {
        Command command = new ShowTeachingsToReplaceCommand(frame, Teachings.longList());

        when(frame.getProfessorToReplace()).thenReturn(Professors.replacement());
        command.execute();

        verify(frame).showProfessorToReplace();
        verify(frame).showTeachings(Teachings.is2List());
    }

    @Test
    public void executingSignTeachingsCommandShouldSignTeachingsSelected() {
        final SignedTeachingsSaver saver = mock(SignedTeachingsSaver.class);
        final TeachingList teachings = Teachings.fsoList();
        final Command command = new SignTeachingsCommand(frame, saver);

        when(frame.getProfessorFromSession()).thenReturn(Professors.ruben());
        when(frame.getSelectedProfessor()).thenReturn(Professors.ruben());
        when(frame.getSelectedTeachings()).thenReturn(teachings);
        command.execute();

        verify(saver).save(Professors.ruben(), teachings);
        teachings.forEach(teaching -> assertThat(teaching.getProfessorForWhomSigned(Professors.ruben()), is(Professors.ruben())));
    }
}