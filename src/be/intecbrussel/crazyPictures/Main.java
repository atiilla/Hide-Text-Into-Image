package be.intecbrussel.crazyPictures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;



public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedImage img = null;
		// A = 255 b R = 100 green G= 150 B= 200
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

		try {
			img = ImageIO.read(f);
			Encoder e = new Encoder(img);
			BufferedImage bi;
			try {
				bi = e.hideString("\r\n" + "Hello Friend\r\n");
				System.out.println(e.decodeImage(bi));
				File outputFile = new File("/home/intec/Desktop/newImageOutPut_encrypted.jpg");
				ImageIO.write(bi, "jpg", outputFile);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		SplitAndMerge s = new SplitAndMerge();
		s.split("/home/intec/Desktop/newImageOutPut_encrypted.jpg", 1024);
		
		s.join("/home/intec/Desktop/newImageOutPut_encrypted.jpg");
		
		
		List<Path> names = new ArrayList<>();
		
	//	Path path = Paths.get("/home/intec/Desktop/merge/");
				
		Files.walk(Paths.get("/home/intec/Desktop/merge/"))
			.filter(Files::isRegularFile)
			.sorted()
			.forEach(entry -> names.add(entry));
		
		
		s.mergeFiles(names[)], "/home/intec/Desktop/merged.jpg");
		 
		
		
		
		
		
	}
	
	
	
	}
	

