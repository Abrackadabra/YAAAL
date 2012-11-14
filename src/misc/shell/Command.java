package misc.shell;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 14/11/12
 * Time: 23:56
 */
public abstract class Command implements Executable {
    protected String[] arguments;

    public Command(String[] arguments) {
        this.arguments = arguments;
    }
}
