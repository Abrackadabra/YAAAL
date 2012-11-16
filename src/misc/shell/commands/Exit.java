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
    public Exit(String[] arguments) {
        super(arguments);
    }

    @Override
    public boolean execute() {
        if (arguments.length != 0) {
            System.err.println("Incorrect syntax. You should use it like:");
            System.err.println("exit");
            return false;
        }

        System.exit(0);
        return true;
    }
}
