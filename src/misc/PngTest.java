package misc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 20/02/13
 * Time: 22:26
 */
public class PngTest {
    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Downloads\\Chrome\\Wikipedia-logo-en-big.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(image.getData().getWidth());
        System.out.println(image.getData().getHeight());

        Raster raster = image.getRaster();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                int[] a = raster.getPixel(i, j, (int[]) null);
                if (a[0] != 0) {
                    System.out.println(Arrays.toString(a));
                }
            }
        }

    }



}
