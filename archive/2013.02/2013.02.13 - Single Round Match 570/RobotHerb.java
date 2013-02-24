package chelper;

public class RobotHerb {
    int[][] directions = {
            {1, 0},
            {0, -1},
            {-1, 0},
            {0, 1}
    };

    public long getdist(int T, int[] a) {
        long x = 0, y = 0;
        int direction = 0;

        for (int i : a) {
            x += directions[direction][0] * i;
            y += directions[direction][1] * i;

            direction = (direction + i) % 4;
        }

        if (direction == 0) {
            x *= T;
            y *= T;
            return Math.abs(x) + Math.abs(y);
        }

        if (direction == 2) {
            T %= 2;
            if (T == 0) {
                return 0;
            } else {
                return Math.abs(x) + Math.abs(y);
            }
        }

        T %= 4;
        if (T == 0) {
            return 0;
        }
        if (T == 1 || T == 3) {
            return Math.abs(x) + Math.abs(y);
        }

        long dx = x + y;
        long dy = y - x;

        return Math.abs(dx) + Math.abs(dy);
    }
}
