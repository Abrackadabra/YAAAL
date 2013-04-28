package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class C {
    String from = "/home/abra/Temp/runs/";
    String to = "/home/abra/Temp/runs_res/";

    TreeMap<String, TreeSet<String>> answers = new TreeMap<String, TreeSet<String>>();
    TreeMap<String, TreeSet<String>> functions = new TreeMap<String, TreeSet<String>>();

    String file;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        File fromFile = new File(from);
        String[] files = fromFile.list();
        Arrays.sort(files);

        for (String file : files) {
            this.file = file;
            if (!file.contains(".cpp")) continue;
            answers.put(file, new TreeSet<String>());
            functions.put(file, new TreeSet<String>());
            process(file);
        }

        new D().solve(answers, functions);

        for (String file : files) {
            this.file = file;
            if (!file.contains(".cpp")) continue;
            answer(file, out);
        }

        out = new OutputWriter("output.txt");
        for (Map.Entry<String, TreeSet<String>> e : answers.entrySet()) {
            if (e.getKey().contains("000086")) {
                System.out.println();
            }
            out.print(e.getKey() + " ");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.addAll(e.getValue());
            Collections.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
            out.printSeparated(arrayList);
            out.println();
        }
        out.flush();
    }

    void process(String fileName) {
        InputReader in;
        try {
            in =new InputReader(new FileInputStream(from + fileName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OutputWriter out = new OutputWriter(to + fileName);

        System.out.print(fileName + "... ");

        String contents = in.readEverything();
        out.print(modify(contents));
        out.flush();

        System.out.println("Done");
    }

    String modify(String string) {
        // comments
        StringBuilder stringBuilder = new StringBuilder();
        boolean insideLine = false;
        boolean insideBlock = false;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (insideLine) {
                if (c == '\n') {
                    insideLine = false;
                    stringBuilder.append('\n');
                }
                continue;
            }
            if (insideBlock) {
                if (c == '*' && i < string.length() - 1 && string.charAt(i + 1) == '/') {
                    insideBlock = false;
                    i++;
                }
                continue;
            }
            if (c == '/' && i < string.length() - 1 && string.charAt(i + 1) == '/') {
                insideLine = true;
                continue;
            }
            if (c == '/' && i < string.length() - 1 && string.charAt(i + 1) == '*') {
                insideBlock = true;
                continue;
            }
            stringBuilder.append(c);
        }
        string = stringBuilder.toString();

        string = string.replaceAll("/+/\\*", "//");

        string = string.replaceAll("(?ms)/\\*.*?\\*/", "");
        string = string.replaceAll("(?ms)//.*?$", "");

        string = string.replaceAll("(?m)^\".*\"};$", "};");
        string = string.replaceAll("(?m)^\".*\";$", ";");
        string = string.replaceAll("(?m)^\".*$", "");

        string = string.replaceAll("\\\\\\\\", "");
        string = string.replaceAll("\\\\\"", "");
        string = string.replaceAll("\\\\\'", "");
        string = string.replaceAll("\"[^\"]*\"", "");
        string = string.replaceAll("'[^']*'", "");

        string = string.replaceAll("\\\\\\s*", "");

        //if (true) return string;

        stringBuilder = new StringBuilder();
        int curly = 0;
        int box = 0;
        for (char c : string.toCharArray()) {
            if (c == '}') curly = Math.max(0, curly - 1);
            if (c == '[') box++;
            if (curly == 0 && box == 0) {
                stringBuilder.append(c);
            }
            if (c == ']') box = Math.max(0, box - 1);
            if (c == '{') curly++;
        }
        string = stringBuilder.toString();

        //if (true) return string;

        // language stuff
        string = string.replaceAll("#.*", "");
        string = string.replaceAll("(?ms)\\Wtypedef\\W.*?;", "");
        string = string.replaceAll("(?ms)\\Wstruct\\W*?\\{\\s*\\}\\s*;", "");
        string = string.replaceAll("(?ms)\\(struct\\W", "(");
        string = string.replaceAll("(?ms)\\Wstruct\\W", "");
        string = string.replaceAll("(?ms)\\Winline\\W", "");
        string = string.replaceAll("(?ms)\\Wtemplate\\s", "");
        string = string.replaceAll("(?ms)\\Wtemplate<", "<");
        string = string.replaceAll("(?ms)\\Wclass\\W.*?;", "");
        string = string.replaceAll("(?ms)\\Wenum\\W.*?;", "");

        string = string.replaceAll("(?ms)using.*?;", "");
        //string = string.replaceAll("(?ms)const", "");
        string = string.replaceAll(".*operator[^(]*+", "operator");
        // : ()
        string = string.replaceAll("\\):[^:{;]+", ")");
        string = string.replaceAll("[^:]:[^:{;]+", "");

        string = string.replaceAll(";", "\n");

        // empty lines
        string = string.replaceAll("(?m)^\\s*\r?\n", "");

        stringBuilder = new StringBuilder();
        int triangle = 0;
        int round = 0;
        for (char c : string.toCharArray()) {
            if (c == '<') triangle++;
            if (c == '(') round++;
            if (triangle == 0 && round == 0) {
                stringBuilder.append(c);
            }
            if (c == '>') triangle = Math.max(0, triangle - 1);
            if (c == ')') round = Math.max(0, round - 1);

        }
        string = stringBuilder.toString();

        if (file.contains("000086")) {
            System.out.println(string);
            //  System.exit(0);
        }

        string = string.replaceAll("(?dm)^const\\W.*$", "");
        string = string.replaceAll("(?dm)^.*\\Wconst\\W.*$", "");
        string = string.replaceAll("(?m)^\\s*\r?\n", "");

        // annoying style
        string = string.replaceAll("\\s*\\{\\s*", "{");

        // cleanup
        string = string.replaceAll("(?dm)\n+$", "");
        string = string.replaceAll("(?dm)\\s+$", "");
        string = string.replaceAll("(?dm)^\\s+", "");
        string = string.replaceAll("\\s\\s+", " ");
        string = string.replaceAll("\\s*,\\s*", ",");

        // ;   ;
        string = string.replaceAll(";\\s+", ";\n");

        string = string.replaceAll("(?dm)=[^,;\n]*", "");

        string = string.replaceAll("\\s*,\\s*", ",");
        string = string.replaceAll("(?m)^[a-zA-Z0-9:_{}*&&[^ ]]*?\\s", "");
        string = string.replaceAll(",", "\n");
        string = string.replaceAll("[; &*]", "");

        if (true) return string;

        return string;
    }

    void answer(String file, OutputWriter out) {
        InputReader in = new InputReader(to + "/" + file);

        TreeSet<String> variables = new TreeSet<String>();
        TreeSet<String> methods = new TreeSet<String>();

        for (String s : in) {
            boolean method = s.contains("{}") || s.contains("::");
            s = s.replaceAll("\\{\\}", "");
            if (method) {
                for (String q : s.split("::"))
                    methods.add(q);
            } else {
                variables.add(s);
            }
        }

        if (file.contains("000086")) {
            System.out.println();;
        }

        variables.removeAll(methods);

        for (String s : variables) {
            if (!answers.get(file).contains(s) && !functions.get(file).contains(s)) {
                continue;
            }
            answers.get(file).add(s);
        }
    }
}
