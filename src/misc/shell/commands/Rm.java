package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 01:21
 */
public class Rm extends IOCommand {
    public Rm(String[] arguments, MovableFile position) {
        super(arguments, position);
    }

    @Override
    public void execute() {
        if (arguments.length == 0) {
            System.out.println("Incorrect syntax. You should use it like:");
            System.out.println("rm <file | directory>");
            return;
        }

        File file = new File(arguments[0]);
        if (!file.isAbsolute()) {
            file = new File(position.getFile().getAbsolutePath() + "/" + arguments[0]);
        }

        delete(file);
    }

    private void delete(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                delete(child);
            }
        }

        file.delete();
    }
}
