package misc.shell;

import misc.shell.commands.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:01
 */
public class CommandFactory {
    public static Action createAction(String s, MovableFile position) {
        Action action = new Action();

        String[] commandStrings = s.split(";");
        for (String commandString : commandStrings) {
            Command command = createCommand(commandString.trim(), position);
            if (command != null) {
                action.addCommand(command);
            }
        }

        return action;
    }

    public static Command createCommand(String s, MovableFile position) {
        String[] arguments = s.split(" ");

        if (arguments.length == 0) {
            return null;
        }

        String command = arguments[0];
        arguments = Arrays.copyOfRange(arguments, 1, arguments.length);

        if (command.equals("exit")) {
            return new Exit(arguments);
        }

        if (command.equals("dir")) {
            return new Dir(arguments, position);
        }

        if (command.equals("cd")) {
            return new Cd(arguments, position);
        }

        if (command.equals("mkdir")) {
            return new Mkdir(arguments, position);
        }

        if (command.equals("pwd")) {
            return new Pwd(arguments, position);
        }

        if (command.equals("rm")) {
            return new Rm(arguments, position);
        }

        if (command.equals("cp")) {
            return new Cp(arguments, position);
        }

        if (command.equals("mv")) {
            return new Mv(arguments, position);
        }

        return new UnknownCommand(command);
    }
}
