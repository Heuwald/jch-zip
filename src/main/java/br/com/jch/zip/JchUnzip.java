package br.com.jch.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JchUnzip {

	private FileInputStream fin;
	private ZipInputStream zipIn;

	public void unzip(Path filePathToUnzip, Path dirPathOutput) throws IOException {

		fin = new FileInputStream(filePathToUnzip.toString());
		zipIn = new ZipInputStream(fin);

		unZipFile(dirPathOutput);
		close();
	}

	private void close() throws IOException {
		if(zipIn != null) {
			zipIn.close();
		}

		if(zipIn != null) {
			fin.close();
		}
	}

	private void unZipFile(Path relativePath) throws IOException {
		ZipEntry entry = null;
		while((entry = zipIn.getNextEntry()) != null) {
			System.out.println("extraindo: " + entry.getName());

			Path filePath = relativePath.resolve(entry.getName());
			Path fileParentDir = filePath.getParent();

			File file = new File(filePath.toString());
			File parentDir = new File(fileParentDir.toString());

			parentDir.mkdirs();

			if(file.createNewFile()) {
				FileOutputStream fos = new FileOutputStream(file);

				byte[] bytes = new byte[1024];

				int nBytes = 0;

				while ((nBytes = zipIn.read(bytes)) >= 0) {
					fos.write(bytes, 0, nBytes);
				}

				fos.flush();
				fos.close();
			}

			zipIn.closeEntry();
		}
	}
}
