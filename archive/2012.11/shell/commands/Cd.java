package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:51
 */
public class Cd extends IOCommand {
    public Cd(String[] arguments, MovableFile movableFile) {
        super(arguments, movableFile);
    }

    @Override
    public boolean execute() {
        if (arguments.length != 1) {
            System.err.println("Incorrect syntax. You should use it like:");
            System.err.println("cd <relative path | absolute path>");
            return false;
        }

        String cd = arguments[0];
        return position.move(cd);
    }
}
