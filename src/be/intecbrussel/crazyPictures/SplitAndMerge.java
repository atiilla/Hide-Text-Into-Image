package be.intecbrussel.crazyPictures;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SplitAndMerge {
	public void join(String FilePath) {
		long leninfile = 0, leng = 0;
		int count = 1, data = 0;
		try {
			File filename = new File(FilePath);
			// RandomAccessFile outfile = new RandomAccessFile(filename,"rw");

			OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
			while (true) {
				filename = new File(FilePath + count + ".sp");
				if (filename.exists()) {
					// RandomAccessFile infile = new RandomAccessFile(filename,"r");
					InputStream infile = new BufferedInputStream(new FileInputStream(filename));
					data = infile.read();
					while (data != -1) {
						outfile.write(data);
						data = infile.read();
					}
					leng++;
					infile.close();
					count++;
				} else {
					break;
				}
			}
			outfile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void split(String FilePath, long splitlen) {
		long leninfile = 0, leng = 0;
		int count = 1, data;
		try {
			File filename = new File(FilePath);

			InputStream infile = new BufferedInputStream(new FileInputStream(filename));
			data = infile.read();
			while (data != -1) {
				filename = new File(FilePath + count + ".sp");

				OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
				while (data != -1 && leng < splitlen) {
					outfile.write(data);
					leng++;
					data = infile.read();
				}
				leninfile += leng;
				leng = 0;
				outfile.close();
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
