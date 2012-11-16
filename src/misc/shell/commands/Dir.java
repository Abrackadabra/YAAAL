package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:38
 */
public class Dir extends IOCommand {
    public Dir(String[] arguments, MovableFile position) {
        super(arguments, position);
    }

    @Override
    public boolean execute() {
        if (arguments.length != 0) {
            System.err.println("Incorrect syntax. You should use it like:");
            System.err.println("dir");
            return false;
        }

        String[] files = position.getFile().list();
        if (files == null) {
            return false;
        }
        for (String s : files) {
            System.out.println(s);
        }
        return true;
    }
}
