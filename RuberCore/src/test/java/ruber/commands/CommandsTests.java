package ruber.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ruber.model.ProfessorList;
import ruber.model.TeachingList;
import ruber.model.fake.Teachings;
import ruber.persistence.SignedTeachingsSaver;
import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

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
    public void executingStartSessionCommandShouldShowProfessorAndTeachingsForHim() {
        Command command = new StartSessionCommand(frame, new ProfessorList(),new TeachingList());
        command.execute();
        verify(frame).initSession(Mockito.any());
        verify(frame).showTeachings(Mockito.any());
    }

    @Test
    public void executingShowProfessorsCommandShouldShowAllProfessorsWithTeachingNow() {
        Command command = new ShowProfessorsCommand(frame, new TeachingList());
        command.execute();
        verify(frame).showProfessors(Mockito.any());
    }

    @Test
    public void executingShowTeachingsToReplaceCommandShouldShowAllTeachingsUnsignedForAProfessor() {
        Command command = new ShowTeachingsToReplaceCommand(frame, new TeachingList());
        command.execute();
        verify(frame).showProfessorToReplace();
        verify(frame).showTeachings(Mockito.any());
    }

    @Test
    public void executingSignTeachingsCommandShouldSignTeachingsSelected() {
        final SignedTeachingsSaver saver = mock(SignedTeachingsSaver.class);
        when(frame.getSelectedTeachings()).thenReturn(Teachings.longList());
        Command command = new SignTeachingsCommand(frame, saver);
        command.execute();
        verify(frame, atLeast(1)).getSelectedTeachings();
        verify(frame, atLeast(1)).getProfessorSelected();
        verify(frame, atLeast(1)).getProfessorFromSession();
        verify(frame, atLeast(1)).getSignature();
        verify(saver, atLeast(1)).save(Mockito.any(), Mockito.any());
    }
}
