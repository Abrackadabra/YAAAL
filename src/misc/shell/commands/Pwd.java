package misc.shell.commands;

import misc.shell.IOCommand;
import misc.shell.MovableFile;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 01:18
 */
public class Pwd extends IOCommand {
    public Pwd(MovableFile position) {
        super(null, position);
    }

    @Override
    public boolean execute() {
        System.out.println(position.getFile().getAbsolutePath());
        return true;
    }
}
