package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 01:18
 */
public class Pwd extends IOCommand {
    public Pwd(String[] arguments, MovableFile position) {
        super(arguments, position);
    }

    @Override
    public boolean execute() {
        if (arguments.length != 0) {
            System.err.println("Incorrect syntax. You should use it like:");
            System.err.println("pwd");
            return false;
        }

        System.out.println(position.getFile().getAbsolutePath());
        return true;
    }
}
