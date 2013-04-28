package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.TreeMap;
import java.util.TreeSet;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        in = new InputReader("/home/abra/Temp/runs_temp/doxygen.tag");

        while (true) {
            String line = in.nextLine();
            if (line == null) break;

            process(line);
        }
    }

    TreeMap<String, TreeSet<String>> answers;
    TreeMap<String, TreeSet<String>> functions;

    void solve(TreeMap<String, TreeSet<String>> answers, TreeMap<String, TreeSet<String>> functions) {
        this.answers = answers;
        this.functions = functions;
        solve(0, null, null);
    }

    boolean insideFile = false;
    boolean insideVar = false;
    boolean insideFunction = false;
    String fileName = null;
    String varName = null;
    String anchorFile = null;
    String varType = null;
    String argList = null;

    TreeSet<String> vars = new TreeSet<String>();

    void process(String line) {
        if (line.contains("7967")) {
            System.out.println();
        }

        if (line.contains("compound kind=\"file\"")) {
            fileName = null;
            insideFile = true;
            vars.clear();
            return;
        }
        if (insideFile && line.contains("member kind=\"variable\"")) {
            insideVar = true;
            return;
        }
        if (insideFile && line.contains("member kind=\"function\"")) {
            insideFunction = true;
            return;
        }
        if (!insideFile) {
            return;
        }

        if (line.contains("</compound>")) {
            insideFile = false;
            answers.get(fileName).addAll(vars);
            return;
        }
        if (insideVar && line.contains("</member>")) {
            processVar();

            insideVar = false;
            return;
        }
        if (insideFunction && line.contains("</member>")) {
            processFunction();

            insideFunction = false;
            return;
        }

        if (line.contains("<type>")) {
            line = line.replaceAll("\\s*<type>", "");
            line = line.replaceAll("</type>", "");
            line = line.replaceAll("&lt;.*&gt;", "");
            varType = line;
        }

        if (line.contains("<arglist>")) {
            line = line.replaceAll("\\s*<arglist>", "");
            line = line.replaceAll("</arglist>", "");
            argList = line;
        }

        if (line.contains("<anchorfile>")) {
            line = line.replaceAll("\\s*<anchorfile>", "");
            line = line.replaceAll("</anchorfile>", "");
            anchorFile = line;
        }

        if (line.contains("<name>")) {
            line = line.replaceAll("\\s*<name>", "");
            line = line.replaceAll("</name>", "");
            if (insideVar || insideFunction) {
                varName = line;
            } else {
                if (fileName == null) {
                    fileName = line;
                }
            }
            return;
        }
    }

    void processVar() {
        if (varName.equals("upper_buttons")) {
            System.out.println();
        }
        varType = " " + varType + " ";
        if (
                varType.matches(".*\\*.*\\W+const\\W*") ||
                varType.matches("[^*]*\\Wconst\\W[^*]*") ||
                anchorFile.contains("namespace")
                ) {
            return;
        }

        varName = varName.replaceAll("\\*", "");

        vars.add(varName);
    }

    void processFunction() {
        if (fileName.equals("000086.cpp")) {
            System.out.println();
        }

        argList = argList.replaceAll("&lt;.*&gt;", "");
        if (argList.matches("[a-zA-Z0-9_-]*")) {
            answers.get(fileName).add(varName);
        }

        varType = " " + varType + " ";
        if (
                argList.matches(".*\\Wchar\\W.*") ||
                argList.matches(".*\\Wconst\\W.*") ||
                argList.matches(".*\\Wint\\W.*") ||
                argList.matches("\\(\\)") ||
                varType.equals(" void ") ||
                (varType.equals(" bool ") && argList.contains(",")) ||
                anchorFile.contains("namespace")
                ) {
            return;
        }
        functions.get(fileName).add(varName);
    }
}
