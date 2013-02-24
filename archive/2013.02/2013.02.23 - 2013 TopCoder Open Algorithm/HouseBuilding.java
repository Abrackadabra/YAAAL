package chelper;

public class HouseBuilding {
    public int getMinimum(String[] area) {
        int n = area.length;
        int m = area[0].length();
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = area[i].charAt(j) - '0';
            }
        }

        int min = 100000;
        for (int level = 0; level < 10; level++) {
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] < level) {
                        res += level - a[i][j];
                    }
                    if (a[i][j] > level + 1) {
                        res += a[i][j] - level - 1;
                    }
                }
            }
            min = Math.min(min, res);
        }
        return min;
    }
}
