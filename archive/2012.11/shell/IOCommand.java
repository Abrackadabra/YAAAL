package misc.shell;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:33
 */
public abstract class IOCommand extends Command {
    private IOCommand(String[] arguments) {
        super(arguments);
    }

    protected MovableFile position;

    public IOCommand(String[] arguments, MovableFile position) {
        super(arguments);
        this.position = position;
    }
}
