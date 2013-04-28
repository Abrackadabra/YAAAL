package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;
import abrackadabra.math.Point3D;

import java.util.*;

public class Timus1657 {
    boolean[] snake = new boolean[27];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        for (int i = 0; i < 27; i++) {
            snake[i] = s.charAt(i) == 'T';
        }

        go(0, new Point3D.Integer(0, 0, 0));

        if (!ok) {
            out.println("IMPOSSIBLE");
            return;
        }

        char[] res = new char[26];
        for (int i = 25; i >= 0; i--) {
            res[i] = (char) directions[stack.pop()][3];
        }
        out.println(new String(res));
    }

    boolean ok = false;

    HashSet<Point3D.Integer> used = new HashSet<Point3D.Integer>();

    Stack<Integer> stack = new Stack<Integer>();

    int minX = 0, maxX = 0, minY = 0, maxY = 0, minZ = 0, maxZ = 0;

    int[][] directions = {{1, 0, 0, 'R'}, {0, 1, 0, 'U'}, {0, 0, 1, 'F'}, {-1, 0, 0, 'L'}, {0, -1, 0, 'D'}, {0, 0, -1, 'B'},};

    long startTime = System.nanoTime();

    void go(int turn, Point3D.Integer point) {
        if (System.nanoTime() - startTime > 1.5e9) return;

        int previousDirection = -1;

        if (!stack.isEmpty()) {
            previousDirection = stack.peek();
        }

        used.add(point);

        if (turn == 26) {
            ok = true;
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (turn != 0) {
                if (snake[turn]) {
                    if (i == previousDirection) {
                        continue;
                    }
                } else {
                    if (i != previousDirection) {
                        continue;
                    }
                }
            }
            Point3D.Integer nextPoint = point.add(directions[i]);
            if (used.contains(nextPoint)) {
                continue;
            }
            int newMinX = AMath.min(nextPoint.getX(), minX);
            int newMaxX = AMath.max(nextPoint.getX(), maxX);
            int newMinY = AMath.min(nextPoint.getY(), minY);
            int newMaxY = AMath.max(nextPoint.getY(), maxY);
            int newMinZ = AMath.min(nextPoint.getZ(), minZ);
            int newMaxZ = AMath.max(nextPoint.getZ(), maxZ);

            if (AMath.max(newMaxX - newMinX, newMaxY - newMinY, newMaxZ - newMinZ) > 2) {
                continue;
            }

            int oldMinX = minX;
            minX = newMinX;
            int oldMaxX = maxX;
            maxX = newMaxX;
            int oldMinY = minY;
            minY = newMinY;
            int oldMaxY = maxY;
            maxY = newMaxY;
            int oldMinZ = minZ;
            minZ = newMinZ;
            int oldMaxZ = maxZ;
            maxZ = newMaxZ;

            stack.push(i);
            go(turn + 1, nextPoint);
            if (ok) {
                return;
            }
            stack.pop();

            minX = oldMinX;
            maxX = oldMaxX;
            minY = oldMinY;
            maxY = oldMaxY;
            minZ = oldMinZ;
            maxZ = oldMaxZ;
        }

        used.remove(point);
    }
}