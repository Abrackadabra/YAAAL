package abrackadabra.util;

import java.util.Arrays;

public class StringUtils {
    /**
     * Calculates prefix function value at position <code>pos</code>.
     *
     * @param prefixFunction
     * @param string
     * @param pos
     * @return prefix function value
     */
    public static int getPrefixFunction(int[] prefixFunction, CharSequence string, int pos) {
        if (pos == 0) return 0;
        int j = prefixFunction[pos - 1];
        while (j > 0 && string.charAt(pos) != string.charAt(j))
            j = prefixFunction[j - 1];
        if (string.charAt(pos) == string.charAt(j)) j++;
        return j;
    }

    /**
     * Calculates Z function value at position <code>pos</code>.
     *
     * @param z
     * @param string
     * @param position
     * @param leftBorder  left border of the rightest substring equal to prefix.
     * @param rightBorder right border of the rightest substring equal to prefix.
     * @return <code>new int[]{z[pos], newLeftBorder, newRightBorder}</code>
     */
    public static int[] getZFunction(int[] z, CharSequence string, int position, int leftBorder, int rightBorder) {
        int j = 0;
        if (position >= leftBorder && position <= rightBorder) {
            // we are inside a repeating prefix
            if (z[position - leftBorder] < rightBorder - position + 1) {
                // we can simply use that value
                j = z[position - leftBorder];
                return new int[]{j, leftBorder, rightBorder};
            } else {
                // we have to run further
                j = rightBorder - position + 1;
            }
        }

        while (position + j < string.length() && string.charAt(position + j) == string.charAt(j))
            j++;

        leftBorder = position;
        rightBorder = position + j - 1;

        return new int[]{j, leftBorder, rightBorder};
    }

    /**
     * Calculates prefix function of <code>string</code>.
     *
     * @param string
     * @return
     */
    public static int[] getPrefixFunction(CharSequence string) {
        int[] pi = new int[string.length()];
        for (int i = 1; i < string.length(); i++) {
            pi[i] = getPrefixFunction(pi, string, i);
        }
        return pi;
    }

    /**
     * Calculates Z function of <code>string</code>.
     *
     * @param string
     * @return
     */
    public static int[] getZFunction(CharSequence string) {
        int[] z = new int[string.length()];

        int a = -1, b = -1;

        for (int i = 1; i < string.length(); i++) {
            int[] q = getZFunction(z, string, i, a, b);
            z[i] = q[0];
            a = q[1];
            b = q[2];
        }

        return z;
    }

    /**
     * Tries to reconstruct original string from <code>prefixFunction</code>.
     *
     * @param prefixFunction
     * @return <code>String</code> or <code>null</code>.
     */
    public static String getStringFromPrefixFunction(int[] prefixFunction) {
        int n = prefixFunction.length;

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < n; i++) {
            boolean ok = false;

            // naive approach
            for (char c = 'a'; c <= 'z'; c++) {
                s.append(c);
                if (getPrefixFunction(prefixFunction, s, i) == prefixFunction[i]) {
                    ok = true;
                    break;
                } else {
                    s.deleteCharAt(s.length() - 1);
                }
            }

            if (!ok) {
                return null;
            }
        }

        return s.toString();
    }

    /**
     * Tries to reconstruct original string from <code>ZFunction</code>.
     *
     * @param ZFunction
     * @return <code>String</code> or <code>null</code>.
     */
    public static String getStringFromZFunction(int[] ZFunction) {
        int n = ZFunction.length;

        boolean[][] possibilities = new boolean[n][26];
        for (int i = 0; i < 26 * n; i++)
            possibilities[i / 26][i % 26] = true;

        int[] string = new int[n];
        Arrays.fill(string, -1);
        string[0] = 0;
        int lastKnownChar = -1;
        for (int i = 1; i < n; i++) {
            if (ZFunction[i] == 0) {
                // this char is any not equal to string[0]
                possibilities[i][string[0]] = false;
                for (int j = 0; j < 26; j++) {
                    if (possibilities[i][j]) {
                        string[i] = j;
                        break;
                    }
                }
                if (string[i] == -1) return null;
            } else {
                // we get distinct knowledge of some chars
                for (int j = Math.max(0, lastKnownChar - i + 1); j < ZFunction[i]; j++) {
                    if (
                            (j + i >= n) ||
                                    (string[i + j] != -1 && string[i + j] != string[j]) ||
                                    (!possibilities[i + j][string[j]]))
                        return null;
                    string[i + j] = string[j];
                    lastKnownChar = i + j;
                }
                if (i + ZFunction[i] < n)
                    possibilities[i + ZFunction[i]][string[ZFunction[i]]] = false;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append((char) (string[i] + 'a'));
        }

        return res.toString();
    }

    /**
     * Tries to calculate Z function from given prefix function.
     *
     * @param zFunction
     * @return
     */
    public static int[] ZFunctionToPrefFunction(int[] zFunction) {
        String s = getStringFromZFunction(zFunction);
        return s == null ? null : getPrefixFunction(s);
    }

    /**
     * Tries to calculate prefix function from given Z function.
     *
     * @param prefixFunction
     * @return
     */
    public static int[] PrefixFunctionToZFunction(int[] prefixFunction) {
        String s = getStringFromPrefixFunction(prefixFunction);

        return s == null ? null : getZFunction(s);
    }
}
