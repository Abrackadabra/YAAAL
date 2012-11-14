package misc.shell;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 14/11/12
 * Time: 23:31
 */
public class Shell {
    public static void main(String[] args) {
        if (args.length == 0) {
            new InteractiveShell().run();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : args) {
                stringBuilder.append(s);
            }

            Action action = CommandFactory.createAction(stringBuilder.toString(), new MovableFile("."));
            action.execute();
        }
    }
}
