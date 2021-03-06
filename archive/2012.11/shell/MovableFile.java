package misc.shell;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 15/11/12
 * Time: 00:57
 */
public class MovableFile {
    private File file;

    public MovableFile(String path) throws IOException {
        file = new File(path).getCanonicalFile();
    }

    public boolean move(String target) {
        try {
            File t = new File(target);
            if (t.isAbsolute()) {
                t = t.getCanonicalFile();
            } else {
                t = new File(file.getAbsolutePath() + File.separator + target).getCanonicalFile();
            }
            if (!t.exists()) {
                System.err.println("Could not find " + t.getPath() + ".");
                return false;
            }
            file = t;
        } catch (IOException e) {
            System.err.println("Severe IO error.");
            return false;
        }
        return true;
    }

    public File getFile() {
        return file;
    }
}
