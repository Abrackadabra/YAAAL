package misc.shell.commands;

import misc.shell.Command;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:10
 */
public class UnknownCommand extends Command {
    private String name;

    public UnknownCommand(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public boolean execute() {
        System.err.println("Unknown command " + name + ".");
        return false;
    }
}
