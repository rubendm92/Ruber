package ruber.commands;

import org.junit.Test;
import org.mockito.Mockito;
import ruber.model.ProfessorList;
import ruber.model.TeachingList;
import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandsTests {

    @Test
    public void executingClearFrameCommandShouldCallToClearFrameOnFrameViewModel() {
        FrameViewModel frame = mock(FrameViewModel.class);
        Command command = new ClearFrameCommand(frame);
        command.execute();
        verify(frame).clear();
    }

    @Test
    public void executingLoadProfessorCommandShouldShowProfessorAndTeachingsForHim() {
        FrameViewModel frame = mock(FrameViewModel.class);
        Command command = new LoadProfessorCommand(frame, new ProfessorList(),new TeachingList());
        command.execute();
        verify(frame).initSession(Mockito.any());
        verify(frame).showTeachingList(Mockito.any());
    }
}
