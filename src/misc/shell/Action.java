package misc.shell;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 14/11/12
 * Time: 23:55
 */
public class Action implements Executable {
    private ArrayList<Command> commands = new ArrayList<Command>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public boolean execute() {
        for (Command command : commands) {
            if (!command.execute()) {
                return false;
            }
        }
        return true;
    }
}
