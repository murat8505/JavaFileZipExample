package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestZipExample
{	
	public static void main( String[] args )
	{

		File file1= new File("C:\\programs\\java_zip_example\\sample.html");
		File file2= new File("C:\\programs\\java_zip_example\\test_slide.html");
		
		List<File> myFiles= new ArrayList<File>();
		
		myFiles.add(file1);
		myFiles.add(file2);
		
		createZip(myFiles, "C:\\programs\\java_zip_example\\test.zip");
		
		System.out.println("Done zipping the files..!");
	}

	public static File createZip(List<File> files, String filename) {
		File zipfile = new File(filename);
		// buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// create ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			// begin compressing the files
			for(int i=0; i<files.size(); i++) {
				
				FileInputStream in = new FileInputStream("C:\\programs\\java_zip_example\\" + files.get(i).getName());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				int len;
				while((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			//don't forget to close the output stream
			out.close();
			return zipfile;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}
}