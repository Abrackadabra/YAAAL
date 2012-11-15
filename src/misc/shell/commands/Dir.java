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
    public Dir(MovableFile position) {
        super(null, position);
    }

    @Override
    public boolean execute() {
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
