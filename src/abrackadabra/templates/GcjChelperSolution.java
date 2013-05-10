package abrackadabra.templates;

import abrackadabra.io.OutputWriter;
import abrackadabra.io.StringOutputWriter;

public abstract class GcjChelperSolution extends AbstractChelperSolution {
    private StringOutputWriter stringOutputWriter;
    private OutputWriter chelperOutputWriter;
    private OutputWriter fileOutputWriter;

    @Override
    public void testStarts(int testNumber) {
        if (!localMachine) return;

        chelperOutputWriter = out;

        stringOutputWriter = new StringOutputWriter();
        out = stringOutputWriter;

        out.printf("Case #%d: ", testNumber);
    }

    @Override
    public void testEnds(int testNumber) {
        if (!localMachine) return;

        String output = stringOutputWriter.getString();

        if (fileOutputWriter == null) {
            fileOutputWriter = new OutputWriter("output.txt");
        }
        fileOutputWriter.print(output);
        fileOutputWriter.flush();

        out = chelperOutputWriter;
        out.print(output);
    }
}
