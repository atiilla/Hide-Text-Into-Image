package be.intecbrussel.crazyPictures;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.LinkedList;

public class SplitAndMerge {
	long leninfile = 0, leng = 0;

	public void mergeFiles(File[] files, File mergedFile) {

		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(mergedFile, true);
			out = new BufferedWriter(fstream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (File f : files) {
			System.out.println("merging: " + f.getName());
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));

				String aLine;
				while ((aLine = in.readLine()) != null) {
					out.write(aLine);
					out.newLine();
				}

				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void join(String FilePath) {
		int count = 1, data = 0;
		try {
			File filename = new File(FilePath);

			OutputStream outfile = new BufferedOutputStream(new FileOutputStream(filename));
			while (true) {
				filename = new File(FilePath + ".part" + count);
				if (filename.exists()) {

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
		int count = 1, data;
		try {
			File filename = new File(FilePath);

			InputStream infile = new BufferedInputStream(new FileInputStream(filename));
			data = infile.read();
			while (data != -1) {
				filename = new File(FilePath + ".part" + count);

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
