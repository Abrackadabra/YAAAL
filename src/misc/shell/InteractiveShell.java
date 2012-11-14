package misc.shell;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 14/11/12
 * Time: 23:46
 */
public class InteractiveShell {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void run() {
        MovableFile position = new MovableFile(".");

        while (true) {
            System.out.print(position.getFile().getAbsolutePath() + "$ ");

            String line = null;
            try {
                line = in.readLine();
            } catch (IOException e) {
                System.err.println("Reading error. Stopping...");
                System.exit(1);
            }

            Action action = CommandFactory.createAction(line, position);
            action.execute();
        }
    }
}
