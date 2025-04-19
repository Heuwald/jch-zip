package br.com.jch.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class JchUnzip {

	private Path pathFileToUnzip, pathOutputFile;

	private FileOutputStream fos;
	private ZipInputStream zipOut;

	public JchUnzip(Path pathFileToUnzip, Path pathOutputFile) {
		this.pathFileToUnzip = pathFileToUnzip;
		this.pathOutputFile = pathOutputFile;
	}

	public void unzip() throws IOException {

		String fileName = pathOutputFile.getFileName().toString();

		fileName = fileName.endsWith(".zip") ? fileName : fileName + ".zip";

		fos = new FileOutputStream(pathOutputFile.getParent().resolve(fileName).toString());
		zipOut = new ZipOutputStream(fos);

		File fileToZip = new File(pathFileToZip.toString());
		zipFile(fileToZip, fileToZip.toPath().getFileName(), zipOut);
		close();
	}

	private void close() throws IOException {
		if(zipOut != null) {
			zipOut.close();
		}

		if(zipOut != null) {
			fos.close();
		}
	}

	private void zipFile(File fileToZip, Path fileRelativePath, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		
		ZipEntry zipEntry = new ZipEntry(fileRelativePath.toString());
		
		if (fileToZip.isDirectory()) {

//			zipEntry.setMethod(ZipEntry.STORED);
//			zipEntry.setSize(0);
//			zipEntry.setCrc(0);
//			zipOut.putNextEntry(zipEntry);
//			zipOut.closeEntry();

			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileRelativePath.resolve(childFile.getName()), zipOut);
			}
			
		} else {
			
			FileInputStream fis = new FileInputStream(fileToZip);

			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
			
		}
	}
}
