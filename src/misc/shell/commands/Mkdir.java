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
    public void execute() {
        if (arguments.length == 0) {
            System.out.println("Incorrect syntax. You should use it like:");
            System.out.println("mkdir <directory name>");
            return;
        }

        File dir = new File(position.getFile().getAbsolutePath() + "/" + arguments[0]);
        dir.mkdirs();
    }
}
