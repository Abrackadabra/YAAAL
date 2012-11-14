package abrackadabra;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class NetUtils {
    public static BufferedImage downloadImage(String address) {
        try {
            return ImageIO.read(new URL(address));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void downloadFile(String from, String to) throws IOException {
        URL website = new URL(from);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(to);
        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
    }
}
