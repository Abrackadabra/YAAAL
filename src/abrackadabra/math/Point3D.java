package abrackadabra.math;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 03/12/12
 * Time: 20:23
 */
public abstract class Point3D {
    public static class Integer {
        int x, y, z;

        public Integer(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Integer add(int[] a) {
            if (a == null || a.length < 3)
                throw new IllegalArgumentException();
            return new Integer(x + a[0], y + a[1], z + a[2]);
        }

        @Override
        public int hashCode() {
            return x + y * 1000 + z * 1000000;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Integer)) return false;
            Integer p = (Integer) o;
            return x == p.x && y == p.y && z == p.z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + ", " + z + "]";
        }
    }

}
