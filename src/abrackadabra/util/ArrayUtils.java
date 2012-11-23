package abrackadabra.util;

public class ArrayUtils {
    public static int findMax(int[] a) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException();
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

    public static int findMin(int[] a) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException();
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
        }
        return min;
    }

    public static String toSimpleString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i != a.length - 1)
                sb.append(" ");
        }
        return sb.toString();
    }
}
