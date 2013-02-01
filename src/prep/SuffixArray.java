package prep;

import abrackadabra.structure.Counter;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 09/01/13
 * Time: 17:11
 */
public class SuffixArray {
    private SuffixArray() {}

    public static int[] get(CharSequence string) {
        int n = string.length();
        int[] result = new int[n];
        int[] classes = new int[n];

        int[] count = new int[256];
        for (int i = 0; i < n; i++) {
            count[string.charAt(i)]++;
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < n; i++) {
            result[--count[string.charAt(i)]] = i;
        }
        for (int i = 1; i < n; i++) {
            classes[result[i]] = classes[result[i - 1]];
            if (string.charAt(result[i]) != string.charAt(result[i - 1])) {
                classes[result[i]]++;
            }
        }

        int[] newResult = new int[n];
        int[] newClasses = new int[n];
        for (int l = 1; l < n; l <<= 1) {
            for (int i = 0; i < n; ++i) {
                newResult[i] = (result[i] - l + n) % n;
            }
            Arrays.fill(count, 0);
            for (int i = 0; i < n; ++i) {
                count[classes[newResult[i]]]++;
            }
            for (int i = 1; i < n; ++i) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; --i) {
                result[--count[classes[newResult[i]]]] = newResult[i];
            }
            newClasses[result[0]] = 0;
            int classesCount = 1;
            for (int i = 1; i < n; ++i) {
                int mid1 = (result[i] + l) % n, mid2 = (result[i - 1] + l) % n;
                if (classes[result[i]] != classes[result[i - 1]] || classes[mid1] != classes[mid2]) {
                    ++classesCount;
                }
                newClasses[result[i]] = classesCount - 1;
            }
            classes = newClasses;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(get("abaaba")));
    }
}
