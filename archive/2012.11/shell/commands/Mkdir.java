package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 01:13
 */
public class Mkdir extends IOCommand {
    public Mkdir(String[] arguments, MovableFile position) {
        super(arguments, position);
    }

    @Override
    public boolean execute() {
        if (arguments.length != 1) {
            System.out.println("Incorrect syntax. You should use it like:");
            System.out.println("mkdir <directory name>");
            return false;
        }

        File dir = new File(position.getFile().getAbsolutePath() + File.separator + arguments[0]);
        return dir.mkdirs();
    }
}
