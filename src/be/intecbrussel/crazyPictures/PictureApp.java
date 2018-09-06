package be.intecbrussel.crazyPictures;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureApp {

	public static void main(String[] args) {
		String text = "INTECBRUSSEL";
		/*
		 * / A = 255 b R = 100 green G= 150 B= 200
		 */
		BufferedImage img = null;
		File f = null;
		try {
			f = new File("/home/intec/Desktop/Sansur9-940x470.jpg");
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}

		int height = img.getHeight();
		int width = img.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				int p = img.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int b = (p >> 8) & 0xff;

				p = (a << 24) | (0 << 16) | (b << 8) | 0;

				img.setRGB(x, y, p);

			}

			f = new File("/home/intec/Desktop/newImageOutPut.jpg");
			try {
				ImageIO.write(img, "jpg", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}

		}
		File output = new File("/home/intec/Desktop/newImageOutPut_with_text.jpg");
		try {
			addTextWatermark(text, "jpg", f, output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void addTextWatermark(String text, String type, File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);

        // determine image type and handle correct transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // initializes necessary graphic properties
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        w.setComposite(alphaChannel);
        w.setColor(Color.GRAY);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, w);

        // calculate center of the image
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        // add text overlay to the image
        w.drawString(text, centerX, centerY);
        ImageIO.write(watermarked, type, destination);
        w.dispose();
    }

}
