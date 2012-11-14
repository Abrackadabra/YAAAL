package misc.shell.commands;

import misc.shell.Command;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 14/11/12
 * Time: 23:58
 */
public class Exit extends Command {
    public Exit() {
        super(null);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
