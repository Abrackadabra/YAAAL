package misc;

import abrackadabra.util.MiscUtils;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 21/02/13
 * Time: 00:44
 */
public class AppletTest extends Applet {
    TimerTask animation = new TimerTask() {
        @Override
        public void run() {
            repaint();
        }
    };
    Timer timer = new Timer();

    static final int width = 1280;
    static final int height = 1024;

    public void init() {
        setSize(width, height);
        //timer.schedule(animation, 0L, 100L);

    }

    BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    @Override
    public void paint(Graphics g) {
        buffer.getGraphics().clearRect(0, 0, width, height);
        paintBuffer(buffer.getGraphics());
        g.drawImage(buffer, 0, 0, null);
    }

    private void paintBuffer(Graphics g) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Downloads\\Chrome\\Wikipedia-logo-en-big.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println(image.getData().getWidth() + "x" + image.getData().getHeight());

        WritableRaster raster = buffer.getRaster();
        int[] temp = new int[4];
        int c = 0;
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp[0] += random.nextInt(3);
                temp[1] += random.nextInt(3);
                temp[2] += random.nextInt(3);
                temp[3] += random.nextInt(3);
                raster.setPixel(i, j, temp);
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

}
