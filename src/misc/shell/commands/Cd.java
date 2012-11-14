package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

import java.io.*;

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
    public void execute() {
        if (arguments.length == 0) {
            System.out.println("Incorrect syntax. You should use it like:");
            System.out.println("cd <relative path | absolute path>");
            return;
        }
        String cd = arguments[0];
        position.move(cd);
    }
}
