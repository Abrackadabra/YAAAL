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
    public boolean execute() {
        if (arguments.length == 0) {
            System.err.println("Incorrect syntax. You should use it like:");
            System.err.println("rm <file | directory>");
            return false;
        }

        File file = new File(arguments[0]);
        if (!file.isAbsolute()) {
            file = new File(position.getFile().getAbsolutePath() + File.separator + arguments[0]);
        }

        delete(file);

        return true;
    }

    private boolean delete(File file) {
        if (!file.exists())
            return false;

        boolean ok = true;

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                ok &= delete(child);
            }
        }

        return file.delete() && ok;
    }
}
