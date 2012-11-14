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

    private UnknownCommand(String[] arguments) {
        super(arguments);
    }

    public UnknownCommand(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Unknown command " + name);
    }
}
