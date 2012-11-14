package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 01:32
 */
public class Cp extends IOCommand {
    public Cp(String[] arguments, MovableFile position) {
        super(arguments, position);
    }

    @Override
    public void execute() {
        if (arguments.length < 2) {
            System.out.println("Incorrect syntax. You should use it like:");
            System.out.println("cp <from> <to>");
            return;
        }
    }
}
