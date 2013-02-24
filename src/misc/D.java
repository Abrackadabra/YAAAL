package misc;

import abrackadabra.io.OutputWriter;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.*;

public class D extends Applet {
    TimerTask animation = new TimerTask() {
        @Override
        public void run() {
            repaint();
        }
    };
    Timer timer = new Timer();

    static final int width = 1600;
    static final int height = 1024;

    public void init() {
        setSize(width, height);
        //timer.schedule(animation, 0L, 100L);

    }

    BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    @Override
    public void paint(Graphics g) {
        if (done) {
            return;
        } else {
            done = true;
        }
        buffer.getGraphics().clearRect(0, 0, width, height);
        paintBuffer(buffer.getGraphics());
        g.drawImage(buffer, 0, 0, null);
    }

    String dir = "c:\\Temp\\PROBLEMSET\\input\\D\\";

    public void update(Graphics g) {
        paint(g);
    }

    String test = "10.png";

    boolean done = false;

    int comp = 3;
    int factor = 1;

    private void paintBuffer(Graphics g) {
        System.out.println(test);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(dir + test));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Raster raster = image.getRaster();

        int imgWidth = image.getWidth() / comp;
        int imgHeight = image.getHeight() / comp;

        boolean[][] data = new boolean[imgWidth + 1][imgHeight + 1];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                data[i / comp][j / comp] |= raster.getPixel(i, j, (int[]) null)[0] < 200;
            }
        }

        buffer = new BufferedImage(imgWidth / factor, imgHeight / factor, BufferedImage.TYPE_INT_RGB);
        g = buffer.getGraphics();

        System.out.println("READ");

        ArrayList<Letter> letters = extractLetters(imgWidth, imgHeight, data);

        g.setColor(Color.YELLOW);

        for (Letter letter : letters) {
            if (letter.parse() == -1) {
                g.drawLine(0, 0, letter.x, letter.y);
                g.drawLine(1, 0, letter.x, letter.y);
                g.drawLine(0, 1, letter.x, letter.y);
            }
        }

        System.out.println("EXTRACTED");

        for (Letter letter : letters) {
            for (int i = 0; i < letter.width; i++) {
                for (int j = 0; j < letter.height; j++) {
                    if (letter.data[i][j]) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.drawLine(
                            (i + letter.x) / factor,
                            (j + letter.y) / factor,
                            (i + letter.x) / factor,
                            (j + letter.y) / factor
                    );
                }
            }
            g.drawString(String.valueOf(letter.parse()), letter.x / factor, letter.y / factor);
        }

        System.out.println("count = " + letters.size());


        OutputWriter out = null;
        try {
            out = new OutputWriter("C:\\temp\\D" + test.substring(0, test.indexOf(".")) + ".out");
        } catch (Exception e) {

        }

        int count = 1;
        ArrayList<Letter> line = new ArrayList<Letter>();
        while (!letters.isEmpty()) {
            Letter best = letters.get(0);
            for (Letter letter : letters) {
                if (letter.x * letter.x * 3 + letter.y * letter.y < best.x * best.x * 3 + best.y * best.y) {
                    best = letter;
                }
            }

            line.clear();
            line.add(best);
            int x = best.x + best.width / 2;
            int y = best.y + best.height / 2;

            g.setColor(Color.RED);

            g.drawOval(best.x / factor, best.y / factor, (best.width) / factor, (best.height) / factor);

            g.drawLine(0, 0, best.x / factor, best.y / factor);

            g.setColor(Color.BLUE);
            for (; x < imgWidth; x++) {
                int t = x - line.get(line.size() - 1).x;
                if (t > line.get(line.size() - 1).width * 4) break;
                for (Letter letter : letters) {
                    if (letter.inside(x, y) && letter != line.get(line.size() - 1)) {
                        x = letter.x + letter.width / 2;
                        y = letter.y + letter.height / 2;
                        g.drawLine(
                                (line.get(line.size() - 1).x + line.get(line.size() - 1).width / 2) / factor,
                                (line.get(line.size() - 1).y + line.get(line.size() - 1).height / 2) / factor,
                                x / factor,
                                y / factor
                        );
                        line.add(letter);
                        break;
                    }
                }
                letters.remove(line.get(line.size() - 1));
            }
            int sum = 0;
            for (Letter letter : line) {
                out.print(letter.parse());
                if (letter != line.get(line.size() - 1))
                    sum = (sum + letter.parse()) % 8;
                else
                    sum -= letter.parse();
                letters.remove(letter);
            }
            if (sum != 0) {
                System.out.println("wrong checksum " + sum);
                System.out.println(count);
            }
            out.println();
            count++;
        }

        out.flush();

        System.out.println("WRITING");

        try {
            ImageIO.write(buffer, "png", new File("C:\\temp\\" + test));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("DONE");
        System.exit(0);
    }

    private static class Letter {
        final int x, y;
        int val = -1;
        final int width, height;

        final boolean[][] data;

        private Letter(int x, int y, int width, int height, boolean[][] bigData) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            data = new boolean[width][height];
            grabData(bigData);
        }

        void grabData(boolean[][] bigData) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    data[i][j] = bigData[x + i][y + j];
                }
            }
            val = parse();
            if (val == -1) System.out.println("can't recognize");
        }

        int parse() {
            if (val != -1) return val;
            boolean lu = false;
            boolean ru = false;
            boolean ld = false;
            boolean rd = false;

            int lw = width / 6 + 1;
            int lh = height / 6 + 1;

            for (int i = 0; i < lw; i++) {
                for (int j = 0; j < lh; j++) {
                    if (data[i][j]) lu = true;
                    if (data[width - i - 1][j]) ru = true;
                    if (data[i][height - j - 1]) ld = true;
                    if (data[width - i - 1][height - j - 1]) rd = true;
                }
            }

            if (height > width * 1.5) {
                if (lu && ru && ld && !rd) return 1;
                return 1;
            }

            if (!lu && !ru && !ld && !rd) return 0;
            if (!lu && !ru && ld && rd) return 4;
            if (lu && ru && !ld && !rd) return 5;

            if (lu && ru && ld && !rd) return 2;
            if (!lu && ru && ld && rd) return 3;
            if (lu && ru && !ld && rd) return 6;
            if (lu && !ru && ld && rd) return 7;

            if (lu && !ru && !ld && !rd) return 0;
            if (!lu && ru && !ld && !rd) return 0;
            return 0;
        }

        boolean inside(int xx, int yy) {
            if (val == 0 || val == 2 || val == 3 || val == 4 || val == 5) {
                return xx >= x && yy >= y - 20 && xx - x < width && yy - y < height * 2;
            }
            return xx >= x && yy >= y - 20 && xx - x < width && yy - y < height + 20;
        }

    }

    ArrayList<Letter> extractLetters(int width, int height, boolean[][] data) {
        ArrayList<Letter> letters = new ArrayList<Letter>();

        Set<Integer> visited = new HashSet<Integer>();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int t = i + j * width;
                if (!data[i][j] || visited.contains(t)) {
                    continue;
                }
                visited.add(t);
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.add(t);

                int minX = i;
                int minY = j;
                int maxX = i;
                int maxY = j;

                while (!queue.isEmpty()) {
                    t = queue.poll();
                    int x = t % width;
                    int y = t / width;
                    for (int[] direction : directions) {
                        int xx = x + direction[0];
                        int yy = y + direction[1];
                        int tt = xx + yy * width;
                        if (xx < 0 || xx >= width || yy < 0 || yy >= height || visited.contains(tt) || !data[xx][yy]) {
                            continue;
                        }
                        visited.add(tt);
                        queue.add(tt);
                        minX = Math.min(minX, xx);
                        minY = Math.min(minY, yy);
                        maxX = Math.max(maxX, xx);
                        maxY = Math.max(maxY, yy);
                    }
                }

                for (int ii = minX; ii <= maxX; ii++) {
                    for (int jj = minY; jj <= maxY; jj++) {
                        int tt = ii + jj * width;
                        if (ii < 0 || jj < 0 || ii >= width || jj >= height || !data[ii][jj] || visited.contains(tt)) {
                            continue;
                        }
                        visited.add(tt);
                        queue.add(tt);

                        while (!queue.isEmpty()) {
                            t = queue.poll();
                            int x = t % width;
                            int y = t / width;
                            for (int[] direction : directions) {
                                int xx = x + direction[0];
                                int yy = y + direction[1];
                                tt = xx + yy * width;
                                if (xx < 0 || xx >= width || yy < 0 || yy >= height || visited.contains(tt) || !data[xx][yy]) {
                                    continue;
                                }
                                visited.add(tt);
                                queue.add(tt);
                                minX = Math.min(minX, xx);
                                minY = Math.min(minY, yy);
                                maxX = Math.max(maxX, xx);
                                maxY = Math.max(maxY, yy);
                            }
                        }
                    }
                }


                for (int ii = minX; ii <= maxX; ii++) {
                    for (int jj = minY; jj <= maxY; jj++) {
                        int tt = ii + jj * width;
                        if (ii < 0 || jj < 0 || ii >= width || jj >= height || !data[ii][jj] || visited.contains(tt)) {
                            continue;
                        }
                        visited.add(tt);
                        queue.add(tt);

                        while (!queue.isEmpty()) {
                            t = queue.poll();
                            int x = t % width;
                            int y = t / width;
                            for (int[] direction : directions) {
                                int xx = x + direction[0];
                                int yy = y + direction[1];
                                tt = xx + yy * width;
                                if (xx < 0 || xx >= width || yy < 0 || yy >= height || visited.contains(tt) || !data[xx][yy]) {
                                    continue;
                                }
                                visited.add(tt);
                                queue.add(tt);
                                minX = Math.min(minX, xx);
                                minY = Math.min(minY, yy);
                                maxX = Math.max(maxX, xx);
                                maxY = Math.max(maxY, yy);
                            }
                        }
                    }
                }

                letters.add(new Letter(minX, minY, maxX - minX + 1, maxY - minY + 1, data));
            }
        }
        return letters;
    }

    int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},

            {10, 10},
            {-10, 10},
            {-10, -10},

            {5, 0},
            {0, 5},
            {-5, 0},
            {0, -5},

            {0, -10},
            {0, 10},
            {-10, 0},
            {7, 0},
    };
}

