package be.intecbrussel.crazyPictures;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageEncoder {

	public int i;
	private byte[] img;

	public ImageEncoder(byte[] img) {
		this.img = img;
	}

	public void changeNthBit(byte b, int n) {
		img[i] = (b == 1) ? (byte) (img[i] | (1 << n)) : (byte) (img[i] & ~(1 << n));
	}

	public byte[] getImg() {
		return this.img;
	}

	public void nextIndex() {
		this.i++;
	}

	public void addEOF() {
		for (int i = 7; i >= 0; i--) {
			changeNthBit((byte) 1, 0);
			i--;
			changeNthBit((byte) 1, 1);
			nextIndex();
		}
	}

	
}
