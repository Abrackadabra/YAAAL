package abrackadabra.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class MiscUtils {
    public static boolean rangeCheck(int lowerBound, int higherBound, int x) {
        return x >= lowerBound && x <= higherBound;
    }

    public static void assertMF(boolean value) { // I DARE YOU
        if (!value) {
            throw new AssertionError();
        }
    }

    public static int[][][] explodeImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Raster raster = image.getRaster();
        int size = raster.getPixel(0, 0, (int[])null).length;
        System.out.println(width + "x" + height + "x" + size);

        int[][][] result = new int[width][height][size];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                raster.getPixel(x, y, result[x][y]);
            }
        }
        return result;
    }
}
